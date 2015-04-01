/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.common;

import com.fenglianfinance.bill.payment.model.AddBidInfoRequest;
import com.fenglianfinance.bill.payment.model.MerchantTransferRequest;
import com.fenglianfinance.bill.payment.model.TransactionQueryRequest;
import com.fenglianfinance.bill.payment.model.RepaymentRequest;
import com.fenglianfinance.bill.payment.model.TransactionLogResult;
import org.springframework.data.domain.Page;

/**
 *
 * @author hansy
 */
public interface GatewayOperations {

    public void repay(RepaymentRequest request);

    public Page<TransactionLogResult> query(TransactionQueryRequest request);

    public  void merchantTransfer(MerchantTransferRequest request);

    public  void addBidInfo(AddBidInfoRequest request);

}
