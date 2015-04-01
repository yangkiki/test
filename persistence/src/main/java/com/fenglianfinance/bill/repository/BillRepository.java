package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fenglianfinance.bill.domain.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>,//
        JpaSpecificationExecutor<Bill> {
    
    @Modifying
    @Query("update Bill bill set bill.status=:status where bill.id=:id")
    public void updateBillStatus(@Param("id")Long id,@Param("status")Bill.Status status);
    
    @Modifying
    @Query("update Bill bill set bill.status=:status,bill.rejectionCause=:rejectionCause where bill.id=:id")
    public void updateBillStatusAndRejectionCause(@Param("id")Long id,@Param("status")Bill.Status status,@Param("rejectionCause")String rejectionCause);
    
    public Bill findBySerialNumber(String serialNumber);
    
    @Modifying
    @Query("update Bill bill set bill.active=:active where bill.id=:id")
    public void updateActiveStatus(@Param("id") Long id, @Param("active") boolean active);

}
