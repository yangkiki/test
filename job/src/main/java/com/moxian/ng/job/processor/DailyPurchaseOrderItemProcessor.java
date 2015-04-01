package com.moxian.ng.job.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

import com.moxian.ng.domain.PurchaseOrder;
import com.moxian.ng.model.Constants;
import org.springframework.batch.item.ItemProcessor;

import com.moxian.ng.domain.DailyAccruedInterest;
import com.moxian.ng.domain.Product;

public class DailyPurchaseOrderItemProcessor implements ItemProcessor<PurchaseOrder, PurchaseOrder> {
    @Override
    public PurchaseOrder process(PurchaseOrder order) throws Exception {

        BigDecimal amount = order.getAmount();
        Product product = order.getProduct();
        Float rate = product.getAnnualPercentageRate() / 100;
        BigDecimal dailyAmount =
                amount.multiply(new BigDecimal(rate)).divide(new BigDecimal(Constants.YEAR_DAYS), RoundingMode.HALF_UP);
        
        dailyAmount=dailyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        
        DailyAccruedInterest dailyAccruedInterest = new DailyAccruedInterest();
        dailyAccruedInterest.setAmount(dailyAmount);
        dailyAccruedInterest.setCurrentRate(product.getAnnualPercentageRate());
        dailyAccruedInterest.setAccruedDate(LocalDateTime.now());
        dailyAccruedInterest.setOrder(order);

        //PurchaseOrder transformedPurchaseOrder = DTOUtils.map(order, PurchaseOrder.class);

        order.addDailyInterest(dailyAccruedInterest);
        
        order.setAccruedInterestAmount(order.getAccruedInterestAmount().setScale(2, BigDecimal.ROUND_HALF_UP));
        

        return order;
    }
}
