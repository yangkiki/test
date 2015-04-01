/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.common;

import com.fenglianfinance.bill.SerialNumberGeneratorUtils;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionReconciliation;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.payment.model.AddBidInfoForm;
import com.fenglianfinance.bill.payment.model.AddBidInfoRequest;
import com.fenglianfinance.bill.payment.model.MerchantTransferForm;
import com.fenglianfinance.bill.payment.model.MerchantTransferRequest;
import com.fenglianfinance.bill.payment.model.RepaymentRequest;
import com.fenglianfinance.bill.payment.model.SerialNumberIndicator;
import com.fenglianfinance.bill.payment.model.TransactionLogResult;
import com.fenglianfinance.bill.payment.model.TransactionQueryRequest;
import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import com.fenglianfinance.bill.repository.TransactionReconciliationRepository;
import com.fenglianfinance.bill.repository.UserRepository;
import java.math.BigDecimal;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

/**
 * @author hansy
 */
public class GatewayService {

    private static final Logger log = LoggerFactory.getLogger(GatewayService.class);

    private GatewayOperations gatewayOperations;
    private SerialNumberGenerator serialNumberGenerator;
    private TransactionLogRepository tlogRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private SerialNumberGeneratorUtils utils;
    private TransactionReconciliationRepository reconciliationRepository;
    private ProductRepository productRepository;

    public GatewayService(GatewayOperations gatewayOperations,//
            SerialNumberGenerator serialNumberGenerator,//
            TransactionLogRepository transactionLogRepository,//
            UserRepository userRepository,//
            OrderRepository orderRepository,//
            SerialNumberGeneratorUtils utils,//
            TransactionReconciliationRepository reconciliationRepository,//
            ProductRepository productRepository) {
        this.gatewayOperations = gatewayOperations;
        this.serialNumberGenerator = serialNumberGenerator;
        this.tlogRepository = transactionLogRepository;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.utils = utils;
        this.reconciliationRepository = reconciliationRepository;
        this.productRepository = productRepository;
    }

    public void sendRepaymentRequest(PurchaseOrder order) {
        if (log.isDebugEnabled()) {
            log.debug("call repayment for order@" + order);
        }

        SerialNumberIndicator indicator = serialNumberGenerator.next();

        RepaymentRequest request = new RepaymentRequest();

        //set serial number.
        request.setOrdId(indicator.getOrdId());
        request.setOrdDate(indicator.getOrdDate());

        //set order information.
        // PurchaseOrder order=orderRepository.findBySerialNumber
        request.setSubOrdId(order.getSerialNumber());
        request.setSubOrdDate(order.getPlacedDate().format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        //UserAccount user = userRepository.findOne(order.getUser().getId());
        UserAccount user = order.getUser();
        request.setInAcctId(user.getAccountingInfo().getAcctId());
        request.setInCustId(user.getAccountingInfo().getAcctCustId());

        DecimalFormat df = new DecimalFormat("###.00");

        request.setTransAmt(df.format(order.getAmount().add(order.getAccruedInterestAmount())));
        //TODO there is no fee currently, if there are some change in future, change this.
        request.setFee("0.00");
        request.setFeeObjFlag("I");

        //Product product = productRepository.findOne(order.getProduct().getId());
        Product product = order.getProduct();
        UserAccount outUser = product.getEnterprise().getUserAccount();
        request.setOutAcctId(outUser.getAccountingInfo().getAcctId());
        request.setOutCustId(outUser.getAccountingInfo().getAcctCustId());

        gatewayOperations.repay(request);
        //
    }

    public void query(TransactionType type, LocalDate beginDate, LocalDate endDate) {
        if (log.isDebugEnabled()) {
            log.debug("transactionLog query  type@" + type + ", beginDate@" + beginDate + ", endDate@" + endDate);
        }

        //initial page number
        int page = 1;

        TransactionQueryRequest treq = new TransactionQueryRequest();
        treq.setBeginDate(beginDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        treq.setEndDate(endDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        treq.setPageNum(page + "");
        switch (type) {
            case PAYMENT:
                treq.setQueryTransType("LOANS");
                break;
            case REPAYMENT:
                treq.setQueryTransType("REPAYMENT");
                break;
            case RECHARGE:
                treq.setQueryTransType("RECHARGE");
                break;
            case WITHDRAW:
                treq.setQueryTransType("WITHDRAW");
                break;
            default:
                break;
        }

        TransactionReconciliation reconciliation = new TransactionReconciliation();
        
        reconciliation.setStatus(TransactionReconciliation.Status.SUCCESS);
        reconciliation.setType(type);
        reconciliation.setCheckDate(LocalDate.now());
        reconciliation.setSerialNumber(utils.nextReconciliationSerialNumber());
        
        recursiveQuery(treq, reconciliation);
        reconciliationRepository.save(reconciliation);

    }

    private void recursiveQuery(TransactionQueryRequest treq, TransactionReconciliation reconciliation) {
        Page<TransactionLogResult> result = gatewayOperations.query(treq);
        if (result == null) {
            return;
        }

        int totalPages = result.getTotalPages();
        long totalElements = result.getTotalElements();

        //if (reconciliation.getTotalItems() == 0) {
            reconciliation.setTotalItems(totalElements);
        //}

        List<TransactionLogResult> logs = result.getContent();

        for (TransactionLogResult tlog : logs) {
            if (log.isDebugEnabled()) {
                log.debug("@@@trsancation log details from remote service@" + tlog);
            }

            TransactionLog existed = tlogRepository.findBySerialNumber(tlog.getSerialNumber());
            final TransactionLog.Status acctStatus = convertAcctStatusToTransactioLog(tlog.getStatus());

            if (existed == null) {
                if (log.isDebugEnabled()) {
                    log.debug("can not find in local database, should insert a new log@");
                }

                TransactionLog newLog = new TransactionLog();
                newLog.setSerialNumber(tlog.getSerialNumber());
                newLog.setType(TransactionType.valueOf(tlog.getType()));
                newLog.setStatus(null);
                newLog.setAmount(tlog.getAmount());
                newLog.setFee(tlog.getFee());
                newLog.setTransactedDate(tlog.getTransactedDate());
                newLog.setCreatedDate(tlog.getCreatedDate());
                newLog.setCheckDate(LocalDate.now());

                if ("LOANS".equals(treq.getQueryTransType())
                        || "REPAYMENT".equals(treq.getQueryTransType())) {

                    newLog.setFrom(userRepository.findByAccountingInfoAcctCustId(tlog.getFromCustId()));
                    newLog.setOrder(orderRepository.findBySerialNumber(tlog.getSerialNumber()));
                }

                newLog.setTo(userRepository.findByAccountingInfoAcctCustId(tlog.getToCustId()));
                newLog.setFromCustId(tlog.getFromCustId());
                newLog.setToCustId(tlog.getToCustId());

                //
                newLog.setAcctStatus(acctStatus);
                newLog.setQueryResult(TransactionLog.QueryResult.LOCAL_DATA_MISSING);

                //
                //newLog.setFixed(Boolean.TRUE);
                //newLog.setFixedDate(LocalDateTime.now());
                tlogRepository.save(newLog);

                //increaseTotalItems(reconciliation);
                increaseMismatchedItems(reconciliation);

                increaseTotalAmount(reconciliation, tlog.getAmount());
                increaseMismatchedAmount(reconciliation, tlog.getAmount());

                updateReconciliationStatusFailed(reconciliation);
            } else {
                existed.setAcctStatus(acctStatus);
                existed.setCheckDate(LocalDate.now());
                if (existed.getStatus() == acctStatus) {
                    existed.setQueryResult(TransactionLog.QueryResult.STATUS_MATCHED);
                } else {

                    if (existed.getStatus() == TransactionLog.Status.SUCCESS
                            && existed.getAcctStatus() == TransactionLog.Status.FAILED) {
                        existed.setQueryResult(TransactionLog.QueryResult.LOCAL_SUCCESS_BUT_REMOTE_FAILED);
                    } else if (existed.getStatus() == TransactionLog.Status.FAILED
                            && existed.getAcctStatus() == TransactionLog.Status.SUCCESS) {
                        existed.setQueryResult(TransactionLog.QueryResult.LOCAL_FAILED_BUT_REMOTE_SUCCESS);
                    } else {
                        existed.setQueryResult(TransactionLog.QueryResult.STATUS_MISMATCHED);
                    }

                    increaseMismatchedItems(reconciliation);
                    increaseMismatchedAmount(reconciliation, tlog.getAmount());
                    updateReconciliationStatusFailed(reconciliation);
                }

                tlogRepository.save(existed);

                //increaseTotalItems(reconciliation);
                increaseTotalAmount(reconciliation, tlog.getAmount());
            }
        }

        if (Integer.parseInt(treq.getPageNum()) < totalPages) {
            treq.setPageNum((Integer.parseInt(treq.getPageNum()) + 1) + "");
            recursiveQuery(treq, reconciliation);
        }
    }

    private void updateReconciliationStatusFailed(TransactionReconciliation recon) {
        if (recon.getStatus() != null && recon.getStatus() != TransactionReconciliation.Status.FAILED) {
            recon.setStatus(TransactionReconciliation.Status.FAILED);
        }
    }

    private void increaseTotalItems(TransactionReconciliation recon) {
        recon.setTotalItems(recon.getTotalItems() + 1);
    }

    private void increaseMismatchedItems(TransactionReconciliation recon) {
        recon.setMismatchedItems(recon.getMismatchedItems() + 1);
    }

    private void increaseTotalAmount(TransactionReconciliation recon, BigDecimal amt) {
        recon.setTotalAmount(recon.getTotalAmount().add(amt));
    }

    private void increaseMismatchedAmount(TransactionReconciliation recon, BigDecimal amt) {
        recon.setMismatchedAmount(recon.getMismatchedAmount().add(amt));
    }

    private TransactionLog.Status convertAcctStatusToTransactioLog(String acctStatus) {
        TransactionLog.Status status = TransactionLog.Status.SUCCESS;

        switch (acctStatus) {
            case "S":
                status = TransactionLog.Status.SUCCESS;
                break;
            case "F":
                status = TransactionLog.Status.FAILED;
                break;
            case "I":
                status = TransactionLog.Status.IN_PROGRESS;
                break;
            case "P":
                status = TransactionLog.Status.SUCCESS;
                break;
            case "H":
                status = TransactionLog.Status.IN_PROGRESS;
                break;
            case "R":
                status = TransactionLog.Status.FAILED;
                break;
        }

        if (log.isDebugEnabled()) {
            log.debug("convert accounting status @" + acctStatus + "->" + status);
        }

        return status;
    }

    public void sentMerchantTransferRequest(MerchantTransferForm form) {

        if (log.isDebugEnabled()) {
            log.debug("call MerchantTransferRequest  @ {} ", form);
        }

        MerchantTransferRequest request = new MerchantTransferRequest();
        request.setInAcctId(form.getInAcctId());
        request.setInCustId(form.getInCustId());
        request.setOrdId(form.getOrdId());
        request.setOutAcctId(form.getOutAcctId());
        request.setOutCustId(form.getOutCustId());

        DecimalFormat df = new DecimalFormat("###.00");

        request.setTransAmt(df.format(form.getTransAmt()));

        gatewayOperations.merchantTransfer(request);

    }

    public void sentAddBidInfoRequest(AddBidInfoForm form) {
        if (log.isDebugEnabled()) {
            log.debug("call sentAddBidInfoRequest  @ {} ", form);
        }
        Product product = productRepository.findOne(form.getProductId());

        if (null == product) {
            log.error(" can find procut by id :{}", form.getProductId());
            return;
        }

        DecimalFormat df = new DecimalFormat("###.00");

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        AddBidInfoRequest request = new AddBidInfoRequest();
        request.setProId(form.getProductId() + "");
        request.setBorrCustId(product.getEnterprise().getUserAccount().getAccountingInfo().getAcctCustId());
        request.setBorrTotAmt(df.format(product.getTotalAmount()));
        request.setYearRate(df.format(product.getAnnualPercentageRate()));
        request.setRetType(form.getRetType());
        request.setBidStartDate(dateTimeFormatter.format(product.getOnSaleDate()));
        request.setBidEndDate(dateFormatter.format(product.getCompletedDate()) + "000000");

        BigDecimal retAmt = product.getTotalAmount().add(
                product.getTotalAmount().multiply(new BigDecimal(product.getAnnualPercentageRate() / 100)));

        request.setRetAmt(df.format(retAmt));
        request.setRetDate(dateFormatter.format(product.getRepaymentDeadline()));
        request.setProArea(form.getProArea());

        gatewayOperations.addBidInfo(request);

    }
}
