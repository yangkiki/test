package com.moxian.ng.job.writer;

import java.util.List;

import com.moxian.ng.domain.PurchaseOrder;
import com.moxian.ng.gateway.common.GatewayService;
import com.moxian.ng.job.service.ProductionTasks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;

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
