package com.moxian.ng.job.service;

import java.util.List;

import com.moxian.ng.domain.PurchaseOrder;
import com.moxian.ng.repository.OrderRepository;
import org.springframework.batch.item.ItemWriter;

public class PurchaseOrderItemWriter implements ItemWriter<PurchaseOrder> {

    private OrderRepository orderRepository;

    public PurchaseOrderItemWriter() {}

    public PurchaseOrderItemWriter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void write(List<? extends PurchaseOrder> items) throws Exception {
        for (PurchaseOrder purchaseOrder : items) {
            orderRepository.saveAndFlush(purchaseOrder);
        }
    }

}
