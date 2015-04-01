package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.model.WithdrawalsDetails;
import com.fenglianfinance.bill.report.model.CurrentStockDetails;
import com.fenglianfinance.bill.report.model.RechargeDetails;
import com.fenglianfinance.bill.report.model.UserDetails;
import com.fenglianfinance.bill.report.model.UserRepayMentDetails;

public class TransactionLogRepositoryImpl implements TransactionLogRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<RechargeDetails> findTransactionLogOrderByTransactedDate(LocalDate d) {
        return em
                .createQuery(
                        "select new com.fenglianfinance.bill.report.model.RechargeDetails(e.userAccount.accountingInfo.acctCustId,e.name,l.amount,l.fee,l.transactedDate) from Enterprise e , TransactionLog l where e.userAccount.type=:type and  e.userAccount.accountingInfo.acctId  = l.to.accountingInfo.acctId And l.createdDate BETWEEN :startDate and :endDate  order by l.transactedDate,l.createdDate desc",//createdDate
                        RechargeDetails.class).setParameter("startDate", d.atStartOfDay())
                .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay())
                .setParameter("type", com.fenglianfinance.bill.domain.UserAccount.Type.ENTERPRISE).getResultList();
    }

    
    @Override
    public List<Product> findTransactionLogOrderByPlacedDateAndUser(LocalDate d) {
        return em
                .createQuery(
                        "select p from Product p,TransactionLog l where p.enterprise.userAccount.accountingInfo.acctId  = l.from.accountingInfo.acctId And l.createdDate BETWEEN :startDate and :endDate order by l.transactedDate,l.createdDate desc",//createdDate
                        Product.class).setParameter("startDate", d.atStartOfDay())
                .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay()).getResultList();
    }

    @Override
    public List<UserDetails> findRechargeUserLogOrderByTransactedDate(LocalDate d) {
        return em
                .createQuery(
                        "select new com.fenglianfinance.bill.report.model.UserDetails(u.username,u.name,u.mobileNumber,l.amount,l.fee,l.transactedDate,u.idCardVerification.cardNumber) from UserAccount u , TransactionLog l  where u.type =:type and u.accountingInfo.acctId  = l.to.accountingInfo.acctId And l.createdDate BETWEEN :startDate and :endDate  order by l.transactedDate,l.createdDate desc",//createdDate
                        UserDetails.class).setParameter("startDate", d.atStartOfDay())
                .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay())
                .setParameter("type", com.fenglianfinance.bill.domain.UserAccount.Type.USER).getResultList();
    }

    @Override
    public List<UserRepayMentDetails> findRepayMentUserLogOrderByTransactedDate(LocalDate d) {
        return em
                .createQuery(
                        "select new com.fenglianfinance.bill.report.model.UserRepayMentDetails(u.username,u.name,u.mobileNumber,l.amount,l.fee,l.transactedDate,u.idCardVerification.cardNumber) from UserAccount u , TransactionLog l where u.type =:type And u.accountingInfo.acctId  = l.from.accountingInfo.acctId And l.createdDate BETWEEN :startDate and :endDate  order by l.transactedDate,l.createdDate desc",//createdDate
                        UserRepayMentDetails.class).setParameter("startDate", d.atStartOfDay())
                .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay())
                .setParameter("type", com.fenglianfinance.bill.domain.UserAccount.Type.USER).getResultList();
    }

    @Override
    public List<CurrentStockDetails> findCurrentStockOrderByEnterprise(LocalDate d) {

        return em
                .createQuery(
                        "select new com.fenglianfinance.bill.report.model.CurrentStockDetails(p.enterprise.name,p.totalAmount) from Product p  where p.type = :type AND p.createdDate BETWEEN :startDate and :endDate",//createdDate
                        CurrentStockDetails.class).setParameter("startDate", d.atStartOfDay())
                .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay())
                .setParameter("type", com.fenglianfinance.bill.domain.Product.Type.DEMAND).getResultList();
    }

    @Override
    public List<WithdrawalsDetails> findWithdrawalsViewByDate(LocalDate d) {
        return em
                .createQuery(
                        "select new com.fenglianfinance.bill.model.WithdrawalsDetails(e.userAccount.username,e.name,l.amount,l.fee,l.transactedDate) from Enterprise e , TransactionLog l where e.userAccount.accountingInfo.acctId  = l.to.accountingInfo.acctId And e.userAccount.type = :type And l.createdDate BETWEEN :startDate and :endDate  order by l.transactedDate,l.createdDate desc",//createdDate
                        WithdrawalsDetails.class).setParameter("startDate", d.atStartOfDay())
                .setParameter("endDate", d.plus(1, ChronoUnit.DAYS).atStartOfDay())
                .setParameter("type", com.fenglianfinance.bill.domain.UserAccount.Type.ENTERPRISE).getResultList();
    }

}
