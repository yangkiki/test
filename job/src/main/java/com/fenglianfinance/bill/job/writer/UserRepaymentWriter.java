package com.fenglianfinance.bill.job.writer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;

import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.gateway.common.GatewayService;
import com.fenglianfinance.bill.job.service.ProductionTasks;

/**
 * Created by admin on 2015/2/5.
 */
public class UserRepaymentWriter extends RepositoryItemWriter<PurchaseOrder> {

    private static final Logger log = LoggerFactory.getLogger(ProductionTasks.class);

    private GatewayService gatewayService;

    public UserRepaymentWriter(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Override
    protected void doWrite(List<? extends PurchaseOrder> items) throws Exception {
        // TODO Auto-generated method stub
        super.doWrite(items);

        for (PurchaseOrder purchaseOrder : items) {
            gatewayService.sendRepaymentRequest(purchaseOrder);
        }
    }

}
