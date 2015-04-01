/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.job.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import com.moxian.ng.domain.DailyAccruedInterest;
import com.moxian.ng.domain.Product;
import com.moxian.ng.domain.PurchaseOrder;
import com.moxian.ng.model.Constants;
import com.moxian.ng.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hansy
 */
@Service
@Transactional
public class DemandProductSchedulingService {

    @Inject
    private OrderRepository orderRepository;

    public void calculateDailyInterest() {
        List<PurchaseOrder> list = orderRepository.findDemandPaidPurchaseOrder();
        for (PurchaseOrder purchaseOrder : list) {

            BigDecimal amount = purchaseOrder.getAmount();
            Product product = purchaseOrder.getProduct();
            Float rate = product.getAnnualPercentageRate() / 100;
            BigDecimal dailyAmount =
                    amount.multiply(new BigDecimal(rate)).divide(new BigDecimal(Constants.YEAR_DAYS), RoundingMode.HALF_UP);
            DailyAccruedInterest dailyAccruedInterest = new DailyAccruedInterest();
            dailyAccruedInterest.setAmount(dailyAmount);
            dailyAccruedInterest.setCurrentRate(product.getAnnualPercentageRate());
            dailyAccruedInterest.setAccruedDate(LocalDateTime.now());
            purchaseOrder.addDailyInterest(dailyAccruedInterest);

            orderRepository.save(purchaseOrder);

        }
        
        orderRepository.flush();

    }

    public void calculateSingleInterest() {

//        List<PurchaseOrder> list = orderRepository.findNewbieOrHotPaidPurchaseOrder();
//
//        for (PurchaseOrder purchaseOrder : list) {
//
//            BigDecimal amount = purchaseOrder.getAmount();
//            Product product = purchaseOrder.getProduct();
//
//            Assert.notNull(product, " product no null! ");
//
//            Bill bill = product.getBill();
//
//            Assert.notNull(bill, " bill no null! ");
//
//            Float rate = product.getAnnualPercentageRate() / 100;
//
//            LocalDate expirationDate = bill.getExpirationDate();
//
//            LocalDate placedDate = purchaseOrder.getPlacedDate().toLocalDate();
//
//            Long usance = ChronoUnit.DAYS.between(placedDate, expirationDate);
//            
//            BigDecimal accruedInterestAmount=amount.multiply(new BigDecimal(rate))
//                    .multiply(new BigDecimal(usance+1)).divide(new BigDecimal(Constants.YEAR_DAYS), RoundingMode.HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
//
//            purchaseOrder.setAccruedInterestAmount(accruedInterestAmount);
//
//            orderRepository.saveAndFlush(purchaseOrder);
//
//        }

    }
}
