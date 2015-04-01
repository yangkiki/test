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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author hansy
 */
public class SummarizedStatisticsRepositoryImpl implements SummarizedStatisticsRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Map<String, Object> userCount(LocalDateTime start, LocalDateTime end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( count(u.id) as regCount,count(u.accountingInfo.acctCustId) as authCount, DATE_FORMAT(u.createdDate,'%Y-%m-%d') as date ) from UserAccount u where u.type='USER' and u.createdDate>=:start and u.createdDate<:end   group by DATE_FORMAT (u.createdDate,'%Y-%m-%d') ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> rechargeUserCount(LocalDate start, LocalDate end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( count(t.to) as rechargeUserCount,t.transactedDate as date ) from TransactionLog t INNER JOIN t.to user  where user.type='USER' and t.type='RECHARGE' and   t.transactedDate>=:start and t.transactedDate<=:end   group by t.transactedDate,t.to ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> rechargeAmountCount(LocalDate start, LocalDate end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( sum(t.amount) as rechargeAmountCount,t.transactedDate as date ) from TransactionLog t INNER JOIN t.to user  where user.type='USER' and t.type='RECHARGE' and t.status='SUCCESS' and  t.transactedDate>=:start and t.transactedDate<=:end   group by t.transactedDate ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> orderUserCountCount(LocalDateTime start, LocalDateTime end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( count(p.user) as orderUserCountCount,DATE_FORMAT ( p.paidDate,'%Y-%m-%d') as date ) from PurchaseOrder p INNER JOIN p.user user  where user.type='USER' and p.active=true and p.status not in ('CANCELED','PAYMENT_FAILED','PENDING_PAYMENT') and  p.paidDate>=:start and p.paidDate<:end   group by p.user,DATE_FORMAT ( p.paidDate,'%Y-%m-%d')  ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> orderCount(LocalDateTime start, LocalDateTime end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( count(p.id) as orderCount,sum(p.amount) as orderAmountCount,DATE_FORMAT ( p.paidDate,'%Y-%m-%d') as date ) from PurchaseOrder p INNER JOIN p.user user  where user.type='USER' and p.active=true and p.status not in ('CANCELED','PAYMENT_FAILED','PENDING_PAYMENT') and  p.paidDate>=:start and p.paidDate<:end   group by DATE_FORMAT ( p.paidDate,'%Y-%m-%d')  ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> withdrawUserCount(LocalDate start, LocalDate end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( count(t.to) as withdrawUserCount,t.transactedDate as date ) from TransactionLog t INNER JOIN t.to user  where user.type='USER' and t.type='WITHDRAW' and   t.transactedDate>=:start and t.transactedDate<=:end   group by t.transactedDate,t.to ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> withdrawAmountCount(LocalDate start, LocalDate end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( sum(t.amount) as withdrawAmountCount,t.transactedDate as date ) from TransactionLog t INNER JOIN t.to user  where user.type='USER' and t.type='WITHDRAW' and t.status='SUCCESS' and  t.transactedDate>=:start and t.transactedDate<=:end   group by t.transactedDate ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public Map<String, Object> userViewCount(LocalDateTime start, LocalDateTime end) {

        Map<String, Object> map = null;

        List<Map> result = em
                .createQuery(
                        "select new map( count(u.ipAddr) as uv,count(u) as pv ) from UserViewStatistic u  where  u.createdDate>=:start and u.createdDate<:end   ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        if (result != null && !result.isEmpty()) {
            map = result.get(0);
        }

        return map;
    }

    @Override
    public List<UserReceivablesReport> userReceivablesCount(LocalDate start, LocalDate end) {

        List<UserReceivablesReport> result = em
                .createQuery(
                        "select new com.fenglianfinance.bill.report.model.UserReceivablesReport( product.name as productName ,p.accruedEndDate as accruedEndDate,user.name as userName,sum(p.amount) as amount,sum(p.accruedInterestAmount) as accruedInterestAmount,product.annualPercentageRate as annualPercentageRate  ) from PurchaseOrder p  INNER JOIN  p.product product inner  join p.user user where p.active=true and p.status in ('PAID','INTEREST_ACCRUED','IN_REPAYMENT','REPAYMENT_FAILED') and  p.accruedEndDate>=:start and p.accruedEndDate<=:end  group by  user.name ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        return result;
    }

    @Override
    public List<UsersInvestmentReport> usersInvestmentDailyCount(LocalDateTime start, LocalDateTime end) {

        List<UsersInvestmentReport> result = em
                .createQuery(
                        "select new com.fenglianfinance.bill.domain.UsersInvestmentReport ( product.name as productName,product.type as type,product.duration as duration ,user.name as userName ,user.idCardVerification.cardNumber as cardNumber ,user.mobileNumber as mobileNumber ,user.id as userId,p.amount as amount ,p.paidDate as paidDate ) from PurchaseOrder p  INNER JOIN  p.product product inner  join p.user user where p.active=true and p.status not in ('PENDING_PAYMENT','PAYMENT_FAILED','CANCELED') and  p.paidDate>=:start and p.paidDate<:end  ")
                .setParameter("start", start).setParameter("end", end).getResultList();

        return result;
    }

}
