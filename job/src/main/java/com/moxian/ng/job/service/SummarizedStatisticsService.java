/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.job.service;

import com.moxian.ng.domain.SummarizedStatistics;
import com.moxian.ng.repository.SummarizedStatisticsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author gao
 */
@Service
@Transactional
public class SummarizedStatisticsService {

  public final static Logger log = LoggerFactory.getLogger(SummarizedStatisticsService.class);


  @Inject
  private SummarizedStatisticsRepository summarizedStatisticsRepository;


  public void saveSummarizedStatistics(LocalDate start, LocalDate end) {

    Map
        userCount =
        summarizedStatisticsRepository
            .userCount(start.atStartOfDay(), end.plus(1, ChronoUnit.DAYS).atStartOfDay());

    Map
        rechargeUserCount =
        summarizedStatisticsRepository.rechargeUserCount(start, end);
    Map
        rechargeAmountCount =
        summarizedStatisticsRepository.rechargeAmountCount(start, end);
    Map
        orderUserCountCount =
        summarizedStatisticsRepository
            .orderUserCountCount(start.atStartOfDay(), end.plus(1, ChronoUnit.DAYS).atStartOfDay());
    Map
        orderCount =
        summarizedStatisticsRepository
            .orderCount(start.atStartOfDay(), end.plus(1, ChronoUnit.DAYS).atStartOfDay());
    Map
        withdrawUserCountMap =
        summarizedStatisticsRepository.withdrawUserCount(start, end);
    Map
        withdrawAmountCount =
        summarizedStatisticsRepository.withdrawAmountCount(start, end);
    Map
        userViewCountMap =
        summarizedStatisticsRepository
            .userViewCount(start.atStartOfDay(), end.plus(1, ChronoUnit.DAYS).atStartOfDay());

    int userRegisterationCount = 0;

    int idCardVerificationCount = 0;

    int inchargeUserCount = 0;

    BigDecimal totalInchargeAmount = BigDecimal.ZERO;

    BigDecimal avgInchargeAmount = BigDecimal.ZERO;

    int paidUserCount = 0;

    int paymentLogCount = 0;

    BigDecimal totalPaymentAmount = BigDecimal.ZERO;

    BigDecimal avgPaymentAmount = BigDecimal.ZERO;

    float avgPaymentCount = 0F;

    int withdrawUserCount = 0;

    BigDecimal totalWithdrawAmount = BigDecimal.ZERO;

    BigDecimal avgWithdrawAmount = BigDecimal.ZERO;

    long userViewCount = 0L;

    long pageViewCount = 0L;

    if (userCount != null) {
      userRegisterationCount = ((Long) userCount.get("regCount")).intValue();
      idCardVerificationCount = ((Long) userCount.get("authCount")).intValue();
    }
    if (rechargeUserCount != null) {
      inchargeUserCount = ((Long) rechargeUserCount.get("rechargeUserCount")).intValue();
    }
    if (rechargeAmountCount != null) {
      totalInchargeAmount = (BigDecimal) rechargeAmountCount.get("rechargeAmountCount");
      if (inchargeUserCount > 0) {
        avgInchargeAmount = totalInchargeAmount.multiply(new BigDecimal(inchargeUserCount));
      }
    }
    if (orderUserCountCount != null) {
      paidUserCount = ((Long) orderUserCountCount.get("orderUserCountCount")).intValue();
    }
    if (orderCount != null) {
      paymentLogCount = ((Long) orderCount.get("orderCount")).intValue();
      totalPaymentAmount = (BigDecimal) orderCount.get("orderAmountCount");
      if (paidUserCount > 0) {
        avgPaymentAmount = totalPaymentAmount.multiply(new BigDecimal(paidUserCount));
        avgPaymentCount = paymentLogCount / paidUserCount;
      }
    }

    if (withdrawUserCountMap != null) {
      withdrawUserCount = ((Long) withdrawUserCountMap.get("withdrawUserCount")).intValue();
    }
    if (withdrawAmountCount != null) {
      totalWithdrawAmount = (BigDecimal) withdrawAmountCount.get("withdrawAmountCount");
      if (withdrawUserCount > 0) {
        avgWithdrawAmount = totalWithdrawAmount.multiply(new BigDecimal(withdrawUserCount));
      }
    }
    if (userViewCountMap != null) {
      userViewCount = (long) userViewCountMap.get("uv");
      pageViewCount = (long) userViewCountMap.get("pv");
    }

    SummarizedStatistics summarizedStatistics = new SummarizedStatistics();
    summarizedStatistics.setSummarizedDate(LocalDate.now().plus(-1, ChronoUnit.DAYS));
    summarizedStatistics.setUserRegisterationCount(userRegisterationCount);
    summarizedStatistics.setIdCardVerificationCount(idCardVerificationCount);
    summarizedStatistics.setInchargeUserCount(inchargeUserCount);
    summarizedStatistics.setTotalInchargeAmount(totalInchargeAmount);
    summarizedStatistics.setAvgInchargeAmount(avgInchargeAmount);
    summarizedStatistics.setPaidUserCount(paidUserCount);
    summarizedStatistics.setPaymentLogCount(paymentLogCount);
    summarizedStatistics.setTotalPaymentAmount(totalPaymentAmount);
    summarizedStatistics.setAvgPaymentAmount(avgPaymentAmount);
    summarizedStatistics.setAvgPaymentCount(avgPaymentCount);
    summarizedStatistics.setWithdrawUserCount(withdrawUserCount);
    summarizedStatistics.setTotalWithdrawAmount(totalWithdrawAmount);
    summarizedStatistics.setAvgWithdrawAmount(avgWithdrawAmount);
    summarizedStatistics.setPageViewCount(pageViewCount);
    summarizedStatistics.setUserViewCount(userViewCount);

    summarizedStatisticsRepository.save(summarizedStatistics);


  }


}
