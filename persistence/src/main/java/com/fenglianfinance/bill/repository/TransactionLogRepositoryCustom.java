package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.util.List;

import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.report.model.CurrentStockDetails;
import com.fenglianfinance.bill.report.model.RechargeDetails;
import com.fenglianfinance.bill.report.model.UserDetails;
import com.fenglianfinance.bill.report.model.UserRepayMentDetails;
import com.fenglianfinance.bill.model.WithdrawalsDetails;

public interface TransactionLogRepositoryCustom {

    public List<RechargeDetails> findTransactionLogOrderByTransactedDate(LocalDate transactedDate);

    public List<Product> findTransactionLogOrderByPlacedDateAndUser(LocalDate transactedDate);
    
    public List<UserDetails> findRechargeUserLogOrderByTransactedDate(LocalDate d);
    
    public List<UserRepayMentDetails> findRepayMentUserLogOrderByTransactedDate(LocalDate d);
    
    public List<CurrentStockDetails> findCurrentStockOrderByEnterprise(LocalDate d);
    
    public List<WithdrawalsDetails> findWithdrawalsViewByDate(LocalDate d);
}
