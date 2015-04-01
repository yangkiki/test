/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.job.service;

import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.gateway.common.GatewayService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.inject.Inject;

/**
 * @author hansy
 */
@Service
@Profile(value = {"prod"})
public class ProductionTasks implements SchedulingTasks {

  private static final Logger log = LoggerFactory.getLogger(MockTasks.class);

  @Inject
  private DemandProductSchedulingService demandProductSchedulingService;

  @Inject
  private JobLauncher jobLauncher;


  @Inject
  private JobRegistry jobRegistry;

  @Inject
  private SummarizedStatisticsService summarizedStatisticsService;

  @Inject
  private GatewayService gatewayService;

  //@Scheduled(cron = "0 10 0 * * *")
  @Override
  public void dailyInterestJob() throws JobExecutionAlreadyRunningException, JobRestartException,
                                        JobInstanceAlreadyCompleteException,
                                        JobParametersInvalidException, NoSuchJobException {
    if (log.isDebugEnabled()) {
      log.debug("============================= dailyInterestJob ========================");
    }

    if (log.isDebugEnabled()) {
      log.debug("begin of dailyInterestJob...@" + (LocalDateTime.now()));
    }

    JobExecution
        dailyExecution =
        jobLauncher.run(jobRegistry.getJob("dailyInterestJob"), new JobParameters());

    if (log.isDebugEnabled()) {
      log.debug("end of dailyInterestJob...@" + (LocalDateTime.now()));
    }
  }

 //@Scheduled(cron = "0 30 0 * * *")
  @Override
  public void expirationBillJob() throws JobExecutionAlreadyRunningException, JobRestartException,
                                         JobInstanceAlreadyCompleteException,
                                         JobParametersInvalidException, NoSuchJobException {
    if (log.isDebugEnabled()) {
      log.debug("============================= expirationBillJob ========================");
    }

    if (log.isDebugEnabled()) {
      log.debug("begin of ExpirationBillJob...@" + (LocalDateTime.now()));
    }

    JobExecution
        expirationBillExecution =
        jobLauncher.run(jobRegistry.getJob("expirationBillJob"), new JobParameters());

    if (log.isDebugEnabled()) {
      log.debug("end of ExpirationBillJob...@" + (LocalDateTime.now()));
    }
  }

  @Scheduled(cron = "0 50 0 * * *")
  @Override
  public void userRepaymentJob() throws JobExecutionAlreadyRunningException, JobRestartException,
                                        JobInstanceAlreadyCompleteException,
                                        JobParametersInvalidException, NoSuchJobException {
    if (log.isDebugEnabled()) {
      log.debug("============================= userRepaymentJob ========================");
    }

    if (log.isDebugEnabled()) {
      log.debug("begin of UserRepaymentJob...@" + (LocalDateTime.now()));
    }

    JobExecution
        userRepaymentExecution =
        jobLauncher.run(jobRegistry.getJob("userRepaymentJob"), new JobParameters());

    if (log.isDebugEnabled()) {
      log.debug("end of UserRepaymentJob...@" + (LocalDateTime.now()));
    }
  }

  @Scheduled(cron = "0 50 1 * * *")
  @Override
  public void paymentQuery() {

    gatewayService.query(TransactionType.PAYMENT, LocalDate.now().plus(-1, ChronoUnit.DAYS),
                         LocalDate.now().plus(-1, ChronoUnit.DAYS));

  }

  @Scheduled(cron = "0 40 1 * * *")
  @Override
  public void repaymentQuery() {
    gatewayService.query(TransactionType.REPAYMENT, LocalDate.now().plus(-1, ChronoUnit.DAYS),
                         LocalDate.now().plus(-1, ChronoUnit.DAYS));
  }

  @Scheduled(cron = "0 30 1 * * *")
  @Override
  public void rechargeQuery() {
    gatewayService.query(TransactionType.RECHARGE, LocalDate.now().plus(-1, ChronoUnit.DAYS),
                         LocalDate.now().plus(-1, ChronoUnit.DAYS));
  }

  @Scheduled(cron = "0 20 1 * * *")
  @Override
  public void withdrawQuery() {
    gatewayService.query(TransactionType.WITHDRAW, LocalDate.now().plus(-1, ChronoUnit.DAYS),
                         LocalDate.now().plus(-1, ChronoUnit.DAYS));
  }

  @Scheduled(cron = "0 10 1 * * *")
  @Override
  public void dailyCountJob() {
    if (log.isDebugEnabled()) {
      log.debug("============================= dailyCountJob ========================");
    }

    if (log.isDebugEnabled()) {
      log.debug("begin of dailyCountJob...@" + (LocalDateTime.now()));
    }

    summarizedStatisticsService.saveSummarizedStatistics(LocalDate.now().plus(-1, ChronoUnit.DAYS),
                                                         LocalDate.now().plus(-1, ChronoUnit.DAYS));

    if (log.isDebugEnabled()) {
      log.debug("end of dailyCountJob...@" + (LocalDateTime.now()));
    }
  }
  
@Scheduled(cron = "0 30 1 * * ?")
@Override
    public void expiredWithFundingInfosJob() throws JobExecutionAlreadyRunningException, JobRestartException,
            JobInstanceAlreadyCompleteException, JobParametersInvalidException, NoSuchJobException {
        if (log.isDebugEnabled()) {
            log.debug("============================= expiredWithFundingInfosJob ========================");
        }

        if (log.isDebugEnabled()) {
            log.debug("begin of expiredWithFundingInfosJob...@" + (LocalDateTime.now()));
        }

        JobExecution dailyExecution = jobLauncher.run(jobRegistry.getJob("expiredWithFundingInfosJob"), new JobParameters());

        if (log.isDebugEnabled()) {
            log.debug("end of expiredWithFundingInfosJob...@" + (LocalDateTime.now()));
        }
    }

}
