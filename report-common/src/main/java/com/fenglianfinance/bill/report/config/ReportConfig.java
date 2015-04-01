/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.report.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fenglianfinance.bill.report.service.ReportService;
import com.fenglianfinance.bill.report.view.CurrentStockView;
import com.fenglianfinance.bill.report.view.FundingEnterpriseView;
import com.fenglianfinance.bill.report.view.RechargeEnterpriseView;
import com.fenglianfinance.bill.report.view.RechargeUserView;
import com.fenglianfinance.bill.report.view.RepayMentUserView;
import com.fenglianfinance.bill.report.view.RepayMentView;
import com.fenglianfinance.bill.report.view.SummarizedStatisticsView;
import com.fenglianfinance.bill.report.view.UserReceivablesView;
import com.fenglianfinance.bill.report.view.UserRegistrationView;
import com.fenglianfinance.bill.report.view.UsersInvestmentView;
import com.fenglianfinance.bill.report.view.WithdrawalsView;

/**
 *
 * @author hantsy
 */
@Configuration
public class ReportConfig {

    @Bean
    public UserRegistrationView userRegistrationView(ReportService reportService) {
        UserRegistrationView userRegistrationView = new UserRegistrationView(reportService);
        userRegistrationView.setUrl("classpath:/reports/userregistration");
        return userRegistrationView;
    }

    @Bean
    public RechargeEnterpriseView rechargeEnterpriseView(ReportService reportService) {
        RechargeEnterpriseView rechargeenterpriseView = new RechargeEnterpriseView(reportService);
        rechargeenterpriseView.setUrl("classpath:/reports/rechargeenterprise");
        return rechargeenterpriseView;
    }

    @Bean
    public FundingEnterpriseView fundingEnterpriseView(ReportService reportService, MessageSource messageSource) {
        FundingEnterpriseView fundingEnterpriseView = new FundingEnterpriseView(reportService, messageSource);
        fundingEnterpriseView.setUrl("classpath:/reports/fundingenterprise");
        return fundingEnterpriseView;
    }

    @Bean
    public SummarizedStatisticsView summarizedStatisticsView(ReportService reportService) {
        SummarizedStatisticsView summarizedStatisticsView = new SummarizedStatisticsView(reportService);
        summarizedStatisticsView.setUrl("classpath:/reports/summarizedstatistics");
        return summarizedStatisticsView;
    }

    @Bean
    public UserReceivablesView userReceivablesView(ReportService reportService) {
        UserReceivablesView userReceivablesView = new UserReceivablesView(reportService);
        userReceivablesView.setUrl("classpath:/reports/userreceivables");
        return userReceivablesView;
    }

    @Bean
    public RepayMentView repayMentView(ReportService reportService, MessageSource messageSource) {
        RepayMentView repayMentView = new RepayMentView(reportService, messageSource);
        repayMentView.setUrl("classpath:/reports/repayment");
        return repayMentView;
    }

    @Bean
    public RechargeUserView rechargeUserView(ReportService reportService) {
        RechargeUserView rechargeUserView = new RechargeUserView(reportService);
        rechargeUserView.setUrl("classpath:/reports/userrecharge");
        return rechargeUserView;
    }

    @Bean
    public UsersInvestmentView usersInvestmentView(ReportService reportService, MessageSource messageSource) {
        UsersInvestmentView usersInvestmentView = new UsersInvestmentView(reportService, messageSource);
        usersInvestmentView.setUrl("classpath:/reports/usersinvestment");
        return usersInvestmentView;
    }

    @Bean
    public RepayMentUserView repayMentUserView(ReportService reportService) {
        RepayMentUserView repayMentUserView = new RepayMentUserView(reportService);
        repayMentUserView.setUrl("classpath:/reports/repaymentuser");
        return repayMentUserView;
    }

    @Bean
    public CurrentStockView currentStockView(ReportService reportService) {
        CurrentStockView currentStockView = new CurrentStockView(reportService);
        currentStockView.setUrl("classpath:/reports/currentstock");
        return currentStockView;
    }
    
    @Bean
    public WithdrawalsView withdrawalsView(ReportService reportService) {
        WithdrawalsView withdrawalsView = new WithdrawalsView(reportService);
        withdrawalsView.setUrl("classpath:/reports/withdrawals");
        return withdrawalsView;
    }

}
