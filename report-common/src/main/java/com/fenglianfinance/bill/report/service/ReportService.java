/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.report.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenglianfinance.bill.DTOUtils;
import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.domain.SummarizedStatistics;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.UsersInvestmentReport;
import com.fenglianfinance.bill.report.model.CurrentStockDetails;
import com.fenglianfinance.bill.report.model.FundingDetails;
import com.fenglianfinance.bill.report.model.RechargeDetails;
import com.fenglianfinance.bill.report.model.RepayMentDetails;
import com.fenglianfinance.bill.report.model.SummarizedStatisticsDetails;
import com.fenglianfinance.bill.model.UserAccountDetails;
import com.fenglianfinance.bill.report.model.UserDetails;
import com.fenglianfinance.bill.report.model.UserReceivablesReport;
import com.fenglianfinance.bill.report.model.UserRepayMentDetails;
import com.fenglianfinance.bill.model.WithdrawalsDetails;
import com.fenglianfinance.bill.repository.ProductRepository;
import com.fenglianfinance.bill.repository.SummarizedStatisticsRepository;
import com.fenglianfinance.bill.repository.TransactionLogRepository;
import com.fenglianfinance.bill.repository.UserRepository;
import com.fenglianfinance.bill.repository.UserSpecifications;

/**
 * @author hantsy
 */
@Service
@Transactional
public class ReportService {

    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private TransactionLogRepository transactionLogRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private SummarizedStatisticsRepository summarizedStatisticsRepository;

    public List<UserAccountDetails> findResigeredUsersByDate(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find users by registed datetime@" + d);
        }

        List<UserAccount> users = userRepository.findAll(UserSpecifications.filterUserAccountsByDatetimeRange(
                d.atStartOfDay(), d.plus(1, ChronoUnit.DAYS).atStartOfDay()), new Sort(Sort.Direction.DESC,
                "createdDate"));

        return DTOUtils.mapList(users, UserAccountDetails.class);
    }

    public List<RechargeDetails> findRechargeEnterpriseByDate(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find RechargeDetails by recharge datetime@" + d);
        }
        List<RechargeDetails> rechargeDetails = transactionLogRepository.findTransactionLogOrderByTransactedDate(
            d);
        return rechargeDetails;
    }

    public List<FundingDetails> findFundingEnterpriseByDate(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find FundingDetails by recharge datetime@" + d);
        }
        List<Product> products = productRepository.findProductOrderByEnterpriseAndType(d);
        return DTOUtils.mapList(products, FundingDetails.class);
    }

    public List<SummarizedStatisticsDetails> findSummarizedStatisticsDetailsList(LocalDate start, LocalDate end) {
        if (log.isDebugEnabled()) {
            log.debug("find findSummarizedStatisticsDetailsList by start {},end {}", start, end);
        }

        List<SummarizedStatistics> summarizedStatisticses = summarizedStatisticsRepository.findBySummarizedDate(
            start,
            end);

        return DTOUtils.mapList(summarizedStatisticses, SummarizedStatisticsDetails.class);
    }

    public List<RepayMentDetails> findRepayMent(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find FundingDetails by recharge datetime@" + d);
        }
        List<Product> products = this.transactionLogRepository.findTransactionLogOrderByPlacedDateAndUser(
            d);
        return DTOUtils.mapList(products, RepayMentDetails.class);
    }

    public List<UserReceivablesReport> findUserReceivablesReportList(LocalDate start, LocalDate end) {
        if (log.isDebugEnabled()) {
            log.debug("find findSummarizedStatisticsDetailsList by start {},end {}", start, end);
        }

        List<UserReceivablesReport> list = summarizedStatisticsRepository.userReceivablesCount(
            start, end);

        return list;
    }

    public List<UserDetails> findRechargeUserViewByDate(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find UserDetails by recharge datetime@" + d);
        }
        List<UserDetails> userDetails = transactionLogRepository.findRechargeUserLogOrderByTransactedDate(
            d);
        return userDetails;
    }
    
    public List<UserRepayMentDetails> findRepayMentUserViewByDate(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find UserRepayMentDetails by recharge datetime@" + d);
        }
        List<UserRepayMentDetails> userDetails = transactionLogRepository.findRepayMentUserLogOrderByTransactedDate(d);
        return userDetails;
    }

    public List<UsersInvestmentReport> findUsersInvestmentDailyCount(LocalDateTime start,LocalDateTime end) {
        if (log.isDebugEnabled()) {
            log.debug("find findUsersInvestmentDailyCount start {} end {}" ,start,end);
        }
      List<UsersInvestmentReport> list=summarizedStatisticsRepository.usersInvestmentDailyCount(start,
                                                                                             end);
      return  list;
    }
    
    public List<CurrentStockDetails> findCurrentStockOrderByEnterprise(LocalDate d) {
        if (log.isDebugEnabled()) {
            log.debug("find UserRepayMentDetails by recharge datetime@" + d);
        }
        List<CurrentStockDetails> list = transactionLogRepository.findCurrentStockOrderByEnterprise(d);
        return list;
    }

    public List<WithdrawalsDetails> findWithdrawalsViewByDate(LocalDate d) {
        List<WithdrawalsDetails> withdrawalsDetails = transactionLogRepository.findWithdrawalsViewByDate(d);
            return withdrawalsDetails;
    }

}
