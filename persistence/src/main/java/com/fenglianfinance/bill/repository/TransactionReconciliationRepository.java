package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fenglianfinance.bill.domain.TransactionReconciliation;

public interface TransactionReconciliationRepository
        extends JpaRepository<TransactionReconciliation, Long>,
        JpaSpecificationExecutor<TransactionReconciliation> {

}
