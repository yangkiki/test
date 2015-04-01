/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.UsersInvestmentReport;
import com.fenglianfinance.bill.report.model.UserReceivablesReport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author hansy
 */
public interface SummarizedStatisticsRepositoryCustom {

  public Map<String, Object> userCount(LocalDateTime start, LocalDateTime end);

  public Map<String, Object> rechargeUserCount(LocalDate start, LocalDate end);

  public Map<String, Object> rechargeAmountCount(LocalDate start, LocalDate end);

  public Map<String, Object> orderUserCountCount(LocalDateTime start, LocalDateTime end);

  public Map<String, Object> orderCount(LocalDateTime start, LocalDateTime end);

  public Map<String, Object> withdrawUserCount(LocalDate start, LocalDate end);

  public Map<String, Object> withdrawAmountCount(LocalDate start, LocalDate end);

  public Map<String, Object> userViewCount(LocalDateTime start, LocalDateTime end);

  public List<UserReceivablesReport> userReceivablesCount(LocalDate start, LocalDate end);

  public List<UsersInvestmentReport> usersInvestmentDailyCount(LocalDateTime start, LocalDateTime end) ;


}
