/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.job.service;

import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

/**
 * @author hantsy
 */
public interface SchedulingTasks {

  public void dailyCountJob();

  public void dailyInterestJob() throws JobExecutionAlreadyRunningException, JobRestartException,
                                        JobInstanceAlreadyCompleteException,
                                        JobParametersInvalidException, NoSuchJobException;

  public void expirationBillJob() throws JobExecutionAlreadyRunningException, JobRestartException,
                                         JobInstanceAlreadyCompleteException,
                                         JobParametersInvalidException, NoSuchJobException;

  public void userRepaymentJob() throws JobExecutionAlreadyRunningException, JobRestartException,
                                        JobInstanceAlreadyCompleteException,
                                        JobParametersInvalidException, NoSuchJobException;


  public void paymentQuery();

  public void repaymentQuery();

  public void rechargeQuery();

  public void withdrawQuery();

  public void expiredWithFundingInfosJob()
      throws JobExecutionAlreadyRunningException, JobRestartException,
             JobInstanceAlreadyCompleteException, JobParametersInvalidException, NoSuchJobException;

}
