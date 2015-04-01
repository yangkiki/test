package com.fenglianfinance.bill.job.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.gateway.common.GatewayService;

public class UserRepaymentProcessor implements ItemProcessor<PurchaseOrder, PurchaseOrder> {

    private static final Logger log = LoggerFactory.getLogger(UserRepaymentProcessor.class);

    private GatewayService gatewayService;

    public UserRepaymentProcessor(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Override
    public PurchaseOrder process(PurchaseOrder order) throws Exception {

        //call  payment gateway to execute repayment action.
//        gatewayService.sendRepaymentRequest(order);
        
        order.setStatus(PurchaseOrder.Status.IN_REPAYMENT);
        return order;
    }

}