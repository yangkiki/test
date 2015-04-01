package com.fenglianfinance.bill.api.accouting;

import com.fenglianfinance.bill.api.security.CurrentUser;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.gateway.common.GatewayService;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.TransactionLogDetails;
import com.fenglianfinance.bill.model.TransactionLogFixRequest;

import com.fenglianfinance.bill.model.TransactionReconciliationDetails;

import com.fenglianfinance.bill.payment.model.AddBidInfoForm;

import com.fenglianfinance.bill.payment.model.MerchantTransferForm;
import com.fenglianfinance.bill.service.AcctService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

import javax.inject.Inject;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_ACCT)
public class AcctMgtController {

    private static final Logger log = LoggerFactory
            .getLogger(AcctMgtController.class);

    @Inject
    private AcctService transationLogService;

    @Inject
    private GatewayService gatewayService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<TransactionLogDetails>> acctTransactionQuery(
            @RequestParam("status") String status,
            @RequestParam("type") String type,
            @RequestParam("checkDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate checkDate,
            @PageableDefault(page = 0, size = 10, sort = "transactedDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug(" get transaction logs@status=" + status + ", type=" + type + ", checkDate=" + checkDate + ", page@" + page);
        }

        Page<TransactionLogDetails> details = transationLogService.findLogsByCriteria(status, type, checkDate, page);

        if (log.isDebugEnabled()) {
            log.debug("get transaction logs @" + details.getTotalElements());
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<TransactionLogDetails>> acctTransactionTransferQuery(
            @RequestParam("fromAcctId") String fromAcctId,
            @RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @PageableDefault(page = 0, size = 10, sort = "transactedDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug(" get transaction logs@fromAcctId=" + fromAcctId + ", beginDate=" + beginDate + ", endDate=" + endDate + ", page@" + page);
        }

        Page<TransactionLogDetails> details = transationLogService.findTransferLogsByCriteria(
                fromAcctId, TransactionType.MERCHANT_TRANSFER, beginDate, endDate, page);

        if (log.isDebugEnabled()) {
            log.debug("get transaction logs @" + details.getTotalElements());
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/reconciliations", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<TransactionReconciliationDetails>> findReconciliations(
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            @RequestParam("beginDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate beginDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate,
            @PageableDefault(page = 0, size = 10, sort = "checkDate", direction = Sort.Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug(" find reconciliations logs@type=" + type
                    + ", status=" + status
                    + ", beginDate=" + beginDate
                    + ", endDate=" + endDate
                    + ", page@" + page);
        }

        Page<TransactionReconciliationDetails> details = transationLogService.findTransReconciliaitonsByCriteria(
                type, status, beginDate, endDate, page);

        if (log.isDebugEnabled()) {
            log.debug("get transaction logs @" + details.getTotalElements());
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @RequestMapping(value = "/transfer", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> transfer(@RequestBody MerchantTransferForm data) {

        if (log.isDebugEnabled()) {
            log.debug(" manual fixing @transter request object@ {} ", data);
        }

        if (log.isDebugEnabled()) {
            log.debug(" merchantTransfer info @ {} ", data);
        }
        gatewayService.sentMerchantTransferRequest(data);

      return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> manualFix(@RequestBody TransactionLogFixRequest request) {

        if (log.isDebugEnabled()) {
            log.debug(" manual fixing@TransactionLogFixRequest=" + request);
        }

        gatewayService.query(TransactionType.valueOf(request.getType()), request.getBeginDate(), request.getEndDate());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> fixTransactionLogStatus(@PathVariable("id") Long id, @CurrentUser UserAccount user) {

        if (log.isDebugEnabled()) {
            log.debug(" call fixStatus @" + id);
        }

        transationLogService.fixTransactionLogStatus(id, user.getId());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/addbidinfo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> addbidinfo(@RequestBody AddBidInfoForm data) {

        if (log.isDebugEnabled()) {
            log.debug(" addBidInfo  @ {} ", data);
        }

        if (log.isDebugEnabled()) {
            log.debug(" sentAddBidInfoRequest info @ {} ", data);
        }
        gatewayService.sentAddBidInfoRequest(data);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
