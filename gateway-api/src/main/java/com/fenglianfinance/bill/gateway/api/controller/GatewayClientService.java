/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.api.controller;

import com.fenglianfinance.bill.domain.AccountingInfo;
import com.fenglianfinance.bill.domain.BankCardInfo;
import com.fenglianfinance.bill.domain.IdCardVerification;
import com.fenglianfinance.bill.domain.MobileNumberVerification;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.WithFundingInfos;
import com.fenglianfinance.bill.gateway.api.OutOfStockException;
import com.fenglianfinance.bill.gateway.api.PaymentGatewayException;
import com.fenglianfinance.bill.gateway.api.ResourceNotFoundException;
import com.fenglianfinance.bill.messaging.api.MessagingConstants;
import com.fenglianfinance.bill.payment.model.AddBidInfoCallback;
import com.fenglianfinance.bill.payment.model.BoundBankCardCallback;
import com.fenglianfinance.bill.payment.model.CorpRegistrationCallback;
import com.fenglianfinance.bill.payment.model.FreezeFundsCallback;
import com.fenglianfinance.bill.payment.model.PaymentCallback;
import com.fenglianfinance.bill.payment.model.PaymentMessage;
import com.fenglianfinance.bill.payment.model.RechargeCallback;
import com.fenglianfinance.bill.payment.model.RepaymentCallback;
import com.fenglianfinance.bill.payment.model.RepaymentMessage;
import com.fenglianfinance.bill.payment.model.TransferCallback;
import com.fenglianfinance.bill.payment.model.UnboundBankCardCallback;
import com.fenglianfinance.bill.payment.model.UserRegistrationCallback;
import com.fenglianfinance.bill.payment.model.WithdrawCallback;
import com.fenglianfinance.bill.repository.BankCardInfoRepository;
import com.fenglianfinance.bill.repository.EnterpriseRepository;
import com.fenglianfinance.bill.repository.MarginTradeRepository;
import com.fenglianfinance.bill.repository.OrderRepository;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import com.fenglianfinance.bill.repository.UserRepository;
import com.fenglianfinance.bill.repository.WithFundingInfosRepository;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.inject.Inject;

import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 *
 * @author hansy
 */
@Service
@Transactional
public class GatewayClientService {

    private static final Logger log = LoggerFactory.getLogger(GatewayClientService.class);

    //dtf
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DecimalFormat NUMBER_FORMAT = new DecimalFormat("###.00");

    private static final String CMD_CORP_REGISTER = "CorpRegister";
    private static final String CMD_USER_REGISTER = "UserRegister";
    private static final String CMD_BIND_BANK_CARD = "UserBindCard";
    private static final String CMD_UNBIND_BANK_CARD = "DelCard";
    private static final String CMD_RECHARGE = "NetSave";
    private static final String CMD_WITHDRAW = "Cash";
    private static final String CMD_PAYMENT = "Loans";
    private static final String CMD_REPAYMENT = "Repayment";
    private static final String CMD_TRANSFER = "Transfer";
    private static final String CMD_ADDBIDINFO = "AddBidInfo";
    private static final String CMD_TENDER_CANCLE = "TenderCancle";

    @Inject
    private UserRepository userRepository;

    @Inject
    private EnterpriseRepository enterpriseRepository;

    @Inject
    private OrderRepository orderRepository;

    @Inject
    private RabbitTemplate rabbitTemplate;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private TransactionLogRepository transactionLogRepository;

    @Inject
    private BankCardInfoRepository bankCardInfoRepository;

    @Inject
    private WithFundingInfosRepository withFundingInfosRepository;
    
    @Inject
    private MarginTradeRepository marginTradeRepository;
    
    ////////////////////////////////////////////////////////////////////////////
    // confirm accounting registeration
    ////////////////////////////////////////////////////////////////////////////
    public void confirmUserAccountingRegistration(UserRegistrationCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmAccountingRegiteration");
        }

        if (!CMD_USER_REGISTER.equals(reg.getCmdId()) || !"000".equals(reg.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway error!");
        }

        UserAccount userAccount = userRepository.findOne(Long.parseLong(reg.getSysId()));

        if (userAccount == null) {
            throw new ResourceNotFoundException("");
        }

        if (userAccount.getAccountingInfo() != null) {
            if (log.isDebugEnabled()) {
                log.debug("user accounting info existed.");
            }
            return;
        }

        AccountingInfo accounting = new AccountingInfo();
        accounting.setAcctId(reg.getUsrId());
        accounting.setAcctCustId(reg.getUsrCustId());
        userAccount.setAccountingInfo(accounting);

        userAccount.setEmail(URLDecoder.decode(reg.getUsrEmail()));
        userAccount.setIdCardVerification(new IdCardVerification(reg.getUsrName(), reg.getIdNo()));
        userAccount.setMobileNumberVerification(new MobileNumberVerification(reg.getUsrMp()));

        //userAccount.setMobileNumber(reg.getUsrMp());
        userAccount.setName(reg.getUsrName());

        userRepository.save(userAccount);

    }

    public void confirmCorpAccountingRegistration(CorpRegistrationCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmCorpAccountingRegiteration");
        }

        if (!CMD_CORP_REGISTER.equals(reg.getCmdId()) || !"000".equals(reg.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway error!");
        }

        UserAccount user = userRepository.findOne(Long.parseLong(reg.getSysId()));

        AccountingInfo accounting = user.getAccountingInfo();

        if (accounting != null) {
            if (log.isDebugEnabled()) {
                log.debug("corp accounting info existed, updating it.");
            }

            accounting.setAcctId(reg.getUsrId());
            accounting.setAcctCustId(reg.getUsrCustId());

        } else {
            accounting = new AccountingInfo();
        }

        accounting.setAcctId(reg.getUsrId());
        accounting.setAcctCustId(reg.getUsrCustId());
        accounting.setAuditDesc(reg.getAuditDesc());
        accounting.setAuditStatus(reg.getAuditStat());
        // user.setBankCode(reg.getOpenBankId());

        user.setAccountingInfo(accounting);
        user = userRepository.save(user);

        BankCardInfo card = new BankCardInfo();
        card.setCardId(reg.getCardId());
        card.setBankId(reg.getOpenBankId());
        card.setUser(user);

        bankCardInfoRepository.save(card);

    }

    public void confirmUnboundBankCard(UnboundBankCardCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmUnboundBankCard");
        }

        if (!CMD_UNBIND_BANK_CARD.equals(reg.getCmdId()) || !"000".equals(reg.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway error!");
        }

        UserAccount userAccount = userRepository.findByAccountingInfoAcctCustId(reg.getUsrCustId());

        if (userAccount == null) {
            throw new ResourceNotFoundException("");
        }
        List<BankCardInfo> cards = bankCardInfoRepository.findByUserId(userAccount.getId());

        for (BankCardInfo card : cards) {
            if (card.getCardId().equals(reg.getCardId())) {
                bankCardInfoRepository.delete(card);
            }
        }
    }

    public void confirmBoundBankCard(BoundBankCardCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmBoundBankCard");
        }

        if (!CMD_BIND_BANK_CARD.equals(reg.getCmdId()) || !"000".equals(reg.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway error!");
        }

        UserAccount userAccount = userRepository.findByAccountingInfoAcctCustId(reg.getUsrCustId());

        if (userAccount == null) {
            throw new ResourceNotFoundException("");
        }

        BankCardInfo card = bankCardInfoRepository.findByUserIdAndCardId(userAccount.getId(), reg.getOpenAcctId());

        if (card == null) {
            card = new BankCardInfo(reg.getOpenAcctId(), reg.getOpenBankId());
            card.setUser(userAccount);
            bankCardInfoRepository.save(card);
        }
    }

    public void confirmPayment(PaymentCallback data) {
        Assert.notNull(data.getOutCustId(), "outCustId should not be null");
        Assert.notNull(data.getInCustId(), "inCustId should not be null");
        Assert.notNull(data.getTransAmt(), "getTransAmt should not be null");

        if (!CMD_PAYMENT.equals(data.getCmdId()) || !"000".equals(data.getRespCode())) {
            throw new PaymentGatewayException("payment gateway error!");
        }

        PurchaseOrder order = orderRepository.findBySerialNumber(data.getSubOrdId());

//        if (order == null) {
//            throw new ResourceNotFoundException("");
//        }
// 00 开头的 ordId 号为 配资申请的支付保证金
		if (data.getOrdId().startsWith("00", 0)) {
			log.info("confirm withfundinginfo @" + data);
			// 获取serialNumber
			String serialNumber = data.getOrdId();
			Assert.notNull(serialNumber, "ordId should not be null");

			WithFundingInfos withFundingInfos = withFundingInfosRepository
					.findBySerialNumberAndActiveIsTrue(serialNumber);
			if (null == withFundingInfos) {
				throw new ResourceNotFoundException("");
			}
			if (withFundingInfos.getStatus().equals(
					WithFundingInfos.Status.PENDING)) {
				updateWithFundingInfos(serialNumber,
						WithFundingInfos.Status.CONFIRMING,
						data.getSubOrdId(), data.getSubOrdDate());
			}
		} else {
			// 投标支付
			log.info("confirm PaymentCallback info @" + data);
			PaymentMessage msg = new PaymentMessage();
			try {
				msg.setAmount(new BigDecimal(NUMBER_FORMAT.parse(
						data.getTransAmt()).doubleValue()));
				msg.setFee(new BigDecimal(NUMBER_FORMAT.parse(data.getFee())
						.doubleValue()));
			} catch (ParseException ex) {
				log.debug("number format parse exception.@" + ex);
			}

			msg.setOrdId(data.getOrdId());
			msg.setOrdDate(LocalDate.parse(data.getOrdDate(), DATE_FORMATTER));
			msg.setOrderSN(data.getSubOrdId());
			msg.setFromCustId(data.getOutCustId());
			msg.setToCustId(data.getInCustId());


        rabbitTemplate.convertAndSend(
                MessagingConstants.EXCHANGE_PAYMENT,
                MessagingConstants.ROUTING_PAYMENT,
                msg
        );
		}

    }

    /**
     * 更新配资信息状态
     *
     * @param data
     * @author wangli@flf77.com
     * @date 2015年2月2日 下午4:10:46
     */
    public void confirmFreezeFunds(FreezeFundsCallback data) {
        Assert.notNull(data.getOrdId(), "ordId should not be null");
        Assert.notNull(data.getUsrCustId(), "usrCustId should not be null");
        Assert.notNull(data.getTransAmt(), "getTransAmt should not be null");

        if (!CMD_PAYMENT.equals(data.getCmdId())
                || !"000".equals(data.getRespCode())) {
            throw new PaymentGatewayException("payment gateway error!");
        }
        withFundingInfosRepository.updateStatus(data.getOrdId(),
                WithFundingInfos.Status.CONFIRMING);
    }

    private void checkProductStock(PurchaseOrder orderForm) throws OutOfStockException {
        final Product product = productRepository.findOne(orderForm.getProduct().getId());

        int boughtCount = orderForm.getAmount().divide(product.getUnitPrice()).intValue();

        if (boughtCount >= product.getLeftCount()) {
            throw new OutOfStockException(orderForm.getProduct().getId());
        }
    }

    public void confirmRecharge(RechargeCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmRecharge");
        }

        if (!CMD_RECHARGE.equals(reg.getCmdId()) || !"000".equals(reg.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway cmd error!");
        }

        UserAccount user = userRepository.findByAccountingInfoAcctCustId(reg.getUsrCustId());
        //  PurchaseOrder order = orderRepository.findOne(Long.parseLong(reg.getOrdId()));

        TransactionLog tlog = transactionLogRepository.findBySerialNumber(reg.getOrdId());
        if (tlog != null) {
            return;
        }

        tlog = new TransactionLog();
        tlog.setTo(user);
        if (StringUtils.hasText(reg.getOrdDate())) {
            tlog.setTransactedDate(LocalDate.parse(reg.getOrdDate(), DATE_FORMATTER));
        }
        tlog.setSerialNumber(reg.getOrdId());
        tlog.setType(TransactionType.RECHARGE);

        try {
            tlog.setAmount(new BigDecimal(NUMBER_FORMAT.parse(reg.getTransAmt()).doubleValue()));
            tlog.setFee(new BigDecimal(NUMBER_FORMAT.parse(reg.getFeeAmt()).doubleValue()));
        } catch (ParseException ex) {
            log.error("number parsing #" + ex.getMessage());
        }

        transactionLogRepository.save(tlog);
    }

    public void confirmWithdraw(WithdrawCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmWithdraw");
        }

        if ((!CMD_WITHDRAW.equals(reg.getCmdId()) && !CMD_WITHDRAW.equals(reg.getRespType()))) {
            throw new PaymentGatewayException("paymnet gateway cmd error!");
        }

        UserAccount user = userRepository.findByAccountingInfoAcctCustId(reg.getUsrCustId());
        //PurchaseOrder order = orderRepository.findOne(Long.parseLong(reg.getOrdId()));
        DecimalFormat nf = new DecimalFormat("###.00");

        TransactionLog tlog = transactionLogRepository.findBySerialNumber(reg.getOrdId());
        if (tlog == null) {
            tlog = new TransactionLog();

            tlog.setTo(user);

            //if (StringUtils.hasText(reg.getOrdDate())) {
                //tlog.setTransactedDate(LocalDate.parse(reg.getOrdDate(), DATE_FORMATTER));
            //}
           	tlog.setTransactedDate(LocalDate.now());

            tlog.setSerialNumber(reg.getOrdId());
            tlog.setType(TransactionType.WITHDRAW);

            try {
                tlog.setAmount(new BigDecimal(nf.parse(reg.getTransAmt()).doubleValue()));
                tlog.setFee(new BigDecimal(nf.parse(reg.getFeeAmt()).doubleValue()));
            } catch (ParseException ex) {
                log.error("number parsing #" + ex.getMessage());
            }

        }

        if ("000".equals(reg.getRespCode())) {
            tlog.setStatus(TransactionLog.Status.SUCCESS);
        } else if ("444".equals(reg.getRespCode())) {
            tlog.setStatus(TransactionLog.Status.FAILED);
        } else if ("999".equals(reg.getRespCode())) {
            tlog.setStatus(TransactionLog.Status.IN_PROGRESS);
        }

        transactionLogRepository.save(tlog);
    }

    void confirmRepayment(RepaymentCallback data) {
        Assert.notNull(data.getOutCustId(), "outCustId should not be null");
        Assert.notNull(data.getInCustId(), "inCustId should not be null");
        Assert.notNull(data.getTransAmt(), "getTransAmt should not be null");

        if (!CMD_REPAYMENT.equals(data.getCmdId())) {
            throw new PaymentGatewayException("paymnet gateway error!");
        }

        PurchaseOrder order = orderRepository.findBySerialNumber(data.getSubOrdId());

//        if (order == null) {
//            throw new ResourceNotFoundException("");
//        }
if (data.getSubOrdId().startsWith("00", 0)) {
			log.info("confirm withfundinginfo RepaymentCallback @" + data);
			// 驳回配资申请，将保证金还款给申请人
			String serialNumber = data.getSubOrdId();
			WithFundingInfos withFundingInfos = withFundingInfosRepository
					.findBySerialNumberAndActiveIsTrue(serialNumber);

			if (null == withFundingInfos) {
				throw new ResourceNotFoundException("");
			}
			if (withFundingInfos.getStatus().equals(
					WithFundingInfos.Status.CONFIRMING)
					|| withFundingInfos.getStatus().equals(
							WithFundingInfos.Status.UNPUBLISHED)
					|| withFundingInfos.getStatus().equals(
							WithFundingInfos.Status.PUBLISHED)
					|| withFundingInfos.getStatus().equals(
							WithFundingInfos.Status.EXPIRED)) {
				// 驳回
				updateWithFundingInfos(serialNumber,
						WithFundingInfos.Status.DISMISSED,
						data.getOrdId());
			}
		} else {
			RepaymentMessage msg = new RepaymentMessage();
			try {
				msg.setAmount(new BigDecimal(NUMBER_FORMAT.parse(
						data.getTransAmt()).doubleValue()));
				msg.setFee(new BigDecimal(NUMBER_FORMAT.parse(data.getFee())
						.doubleValue()));
			} catch (ParseException ex) {
				log.debug("number format parse exception.@" + ex);
			}

			msg.setOrdId(data.getOrdId());
			msg.setOrdDate(LocalDate.parse(data.getOrdDate(), DATE_FORMATTER));
			msg.setOrderSN(data.getSubOrdId());
			msg.setFromCustId(data.getOutCustId());
			msg.setToCustId(data.getInCustId());
 			msg.setRespCode(data.getRespCode());

			rabbitTemplate.convertAndSend(MessagingConstants.EXCHANGE_PAYMENT,
					MessagingConstants.ROUTING_PAYMENT, msg);
		}
    }

    public void confirmAddBidInfo(AddBidInfoCallback data) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmRecharge");
        }

        if (!CMD_ADDBIDINFO.equals(data.getCmdId()) || !"000".equals(data.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway cmd error!");
        }

        String prodId = data.getProId();

        Product prod = productRepository.findOne(Long.parseLong(prodId));

        prod.setPublishedToAcct(true);
        prod.setPublishedToAcctDate(LocalDateTime.now());

        productRepository.save(prod);
    }

    public void confirmTransfer(TransferCallback data) {
        if (log.isDebugEnabled()) {
            log.debug("call confirmRecharge");
        }

        if (!CMD_TRANSFER.equals(data.getCmdId()) || !"000".equals(data.getRespCode())) {
            throw new PaymentGatewayException("paymnet gateway cmd error!");
        }

        UserAccount from = userRepository.findByAccountingInfoAcctCustId(data.getOutCustId());
        UserAccount to = userRepository.findByAccountingInfoAcctCustId(data.getInCustId());

        TransactionLog tlog = transactionLogRepository.findBySerialNumber(data.getOrdId());
        if (tlog != null) {
            return;
        }

        tlog = new TransactionLog();
         tlog.setSerialNumber(data.getOrdId());

        if (StringUtils.hasText(data.getOrdDate())) {
            tlog.setTransactedDate(LocalDate.parse(data.getOrdDate(), DATE_FORMATTER));
        } else {
            tlog.setTransactedDate(LocalDate.now());
        }
        
        

        tlog.setFromCustId(data.getOutCustId());
        tlog.setFromAcctId(data.getOutAcctId());

        if (from != null) {
            tlog.setFrom(from);
        }
        
        tlog.setToCustId(data.getInCustId());
        tlog.setToAcctId(data.getInAcctId());

        if (to != null) {
            tlog.setTo(to);
        }
       
        tlog.setType(TransactionType.MERCHANT_TRANSFER);

        try {
            tlog.setAmount(new BigDecimal(NUMBER_FORMAT.parse(data.getTransAmt()).doubleValue()));

        } catch (ParseException ex) {
            log.error("number parsing #" + ex.getMessage());
        }

        transactionLogRepository.save(tlog);
    }


  /**
	 * 事务修改配资申请状态并保存流水
	 * 
	 * @param serialNumber
	 * @param status
	 * @param subOrdId
	 * @param subOrdDate
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午12:29:39
	 */
	private void updateWithFundingInfos(String serialNumber,
			WithFundingInfos.Status status, String subOrdId, String subOrdDate) {
		withFundingInfosRepository.updateStatus(serialNumber, status);
		marginTradeRepository.updateSubOrdIdAndSubOrdDate(subOrdId, subOrdDate,
				serialNumber);
	}

	/**
	 * 事务修改配资申请状态并保存流水
	 * 
	 * @param serialNumber
	 * @param status
	 * @param repaymentOrdId
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午12:29:44
	 */
	private void updateWithFundingInfos(String serialNumber,
			WithFundingInfos.Status status, String repaymentOrdId) {
		withFundingInfosRepository.updateStatus(serialNumber, status);
		marginTradeRepository
				.updateRepaymentOrdId(repaymentOrdId, serialNumber);
	}
}
