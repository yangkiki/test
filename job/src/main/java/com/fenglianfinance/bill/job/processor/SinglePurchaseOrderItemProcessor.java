package com.fenglianfinance.bill.job.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.Assert;

import com.fenglianfinance.bill.domain.Bill;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.model.Constants;

public class SinglePurchaseOrderItemProcessor implements ItemProcessor<PurchaseOrder, PurchaseOrder> {

    @Override
    public PurchaseOrder process(PurchaseOrder order) throws Exception {

        BigDecimal amount = order.getAmount();
        Product product = order.getProduct();

        Assert.notNull(product, " product no null! ");

        Bill bill = product.getBill();

        Assert.notNull(bill, " bill no null! ");

        Float rate = product.getAnnualPercentageRate() / 100;

        LocalDate expirationDate = bill.getExpirationDate();

        LocalDate placedDate = order.getPlacedDate().toLocalDate();

        Long usance = ChronoUnit.DAYS.between(placedDate, expirationDate);

        BigDecimal accruedInterestAmount
                = amount.multiply(new BigDecimal(rate)).multiply(new BigDecimal(usance + 1))
                .divide(new BigDecimal(Constants.YEAR_DAYS), RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);

        order.setAccruedInterestAmount(accruedInterestAmount);

        return order;
    }
}
