package com.fenglianfinance.bill.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.report.model.CurrentStockDetails;
import com.fenglianfinance.bill.report.model.UserRepayMentDetails;
import com.fenglianfinance.bill.model.WithdrawalsDetails;

public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long>,
        JpaSpecificationExecutor<TransactionLog>, TransactionLogRepositoryCustom {

    //public TransactionLog findByUserAndOrderAndType(UserAccount user, PurchaseOrder order, TransactionLog.Type type);

    public TransactionLog findBySerialNumber(String ordId);

    //public Page<TransactionLog> findByUserId(Long id, Pageable page);

    public Page<TransactionLog> findByToUsernameAndType(String username, TransactionType type, Pageable page);

    @Query("select sum(t.amount) from TransactionLog t where t.to.username=?1 and t.type=?2 ")
    public BigDecimal sumTransactionLogAmount(String username, String type);

    @Query("select count(t.amount) from TransactionLog t where t.to.username=?1 and t.type=?2 ")
    public int countTransactionLog(String username, String type);
	@Query("select sum(t.amount) from TransactionLog t where t.to.username=?1 and t.type=?2 ")
	public BigDecimal sumTransactionLogAmount(String username, TransactionType type);

   

	@Query("select count(t.amount) from TransactionLog t where t.to.username=?1 and t.type=?2 ")
	public int countTransactionLog(String username, TransactionType type);
}
