package com.fenglianfinance.bill.job.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.repository.OrderRepository;

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
