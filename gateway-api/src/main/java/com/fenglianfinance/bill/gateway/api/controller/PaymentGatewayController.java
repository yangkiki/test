/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.api.controller;

import com.fenglianfinance.bill.payment.model.TransferCallback;
import com.fenglianfinance.bill.payment.model.UserRegistrationCallback;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BooleanValue;
import com.fenglianfinance.bill.payment.model.AddBidInfoCallback;
import com.fenglianfinance.bill.payment.model.BoundBankCardCallback;
import com.fenglianfinance.bill.payment.model.CorpRegistrationCallback;
import com.fenglianfinance.bill.payment.model.PaymentCallback;
import com.fenglianfinance.bill.payment.model.RechargeCallback;
import com.fenglianfinance.bill.payment.model.RepaymentCallback;
import com.fenglianfinance.bill.payment.model.TenderCancleCallback;
import com.fenglianfinance.bill.payment.model.UnboundBankCardCallback;
import com.fenglianfinance.bill.payment.model.WithdrawCallback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hantsy
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + "/callback")
public class PaymentGatewayController {

    private final static Logger log = LoggerFactory.getLogger(PaymentGatewayController.class);

    @Inject
    private GatewayClientService gatewayService;

    @RequestMapping(value = {""}, params = "action=GENERATE_INDICATOR", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Map<String, String>> generateTransactionIndicator() {
        if (log.isDebugEnabled()) {
            log.debug("generating transaction log indicator...");
        }

        // String ordId = Hex.encodeHexString(KeyGenerators.secureRandom(10).generateKey());
//        String ordId = String.valueOf(System.nanoTime());
        String ordId = RandomStringUtils.random(20, false, true);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        String ordDate = dtf.format(LocalDateTime.now());

        Map<String, String> map = new HashMap<>();
        map.put("ordId", ordId);
        map.put("ordDate", ordDate);

        if (log.isDebugEnabled()) {
            log.debug("generated map @" + map);
        }

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_USER_REGISTRATION", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmUserRegistration(@RequestBody UserRegistrationCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("confirm user registration...@" + reg);
        }

        gatewayService.confirmUserAccountingRegistration(reg);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_CORP_REGISTRATION", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmCorpRegistration(@RequestBody CorpRegistrationCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("confirm corp registration...@" + reg);
        }

        gatewayService.confirmCorpAccountingRegistration(reg);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_BOUND_BANK_CARD", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmBoundBankCard(@RequestBody BoundBankCardCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("confirm bound bank cards...@" + reg);
        }

        gatewayService.confirmBoundBankCard(reg);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_UNBOUND_BANK_CARD", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmUnboundBankCard(@RequestBody UnboundBankCardCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("confirm unbound bank cards...@" + reg);
        }

        gatewayService.confirmUnboundBankCard(reg);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_RECHARGE", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmRecharge(@RequestBody RechargeCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("confirm recharge...@" + reg);
        }

        gatewayService.confirmRecharge(reg);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_WITHDRAW", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmWithdraw(@RequestBody WithdrawCallback reg) {
        if (log.isDebugEnabled()) {
            log.debug("confirm withdraw...@" + reg);
        }

        gatewayService.confirmWithdraw(reg);

        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_PAYMENT", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmPayment(//
            @RequestBody PaymentCallback data
    ) {

        if (log.isDebugEnabled()) {
            log.debug("confirm payment info @" + data);
        }

        gatewayService.confirmPayment(data);
        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_REPAYMENT", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmRepayment(//
            @RequestBody RepaymentCallback data
    ) {

        if (log.isDebugEnabled()) {
            log.debug("confirm repayment info @" + data);
        }

        gatewayService.confirmRepayment(data);
        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_ADD_BID_INFO", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmAddBidInfo(//
            @RequestBody AddBidInfoCallback data
    ) {

        if (log.isDebugEnabled()) {
            log.debug("confirm addBidInfo info @" + data);
        }

        gatewayService.confirmAddBidInfo(data);
        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }

    @RequestMapping(value = {""}, params = "action=CONFIRM_TRANSFER", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<BooleanValue> confirmTransfer(//
            @RequestBody TransferCallback data
    ) {

        if (log.isDebugEnabled()) {
            log.debug("confirm transfer info @" + data);
        }

        gatewayService.confirmTransfer(data);
        return new ResponseEntity<>(new BooleanValue(true), HttpStatus.CREATED);
    }
}
