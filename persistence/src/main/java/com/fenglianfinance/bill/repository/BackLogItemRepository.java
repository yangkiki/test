package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fenglianfinance.bill.domain.BackLogItem;
import com.fenglianfinance.bill.domain.SimpleBackLogAmount;

public interface BackLogItemRepository extends JpaRepository<BackLogItem, Long>,//
        JpaSpecificationExecutor<BackLogItem> {

    @Query("select b from BackLogItem b INNER  JOIN b.bill c  where c.id=:billId ")
    public BackLogItem checkBackLogItemExistTheBill(@Param("billId") Long billId);

    @Modifying
    @Query("update BackLogItem b set b.status=:status where b.id=:id")
    public void updateStatus(@Param("id") Long id, @Param("status") BackLogItem.Status status);

    @Query("select b.id from Bill b where not exists( select bb from BackLogItem bb where bb.bill=b ) ")
    public List<Long> findBillNotInBackLogItem();

    @Query(" select b from BackLogItem b INNER JOIN b.bill bi where  bi.active =true and bi.status='APPROVED' and bi.expirationDate=:expirationDate ")
    public Page<BackLogItem> findByExpirationBill(@Param("expirationDate") LocalDate expirationDate, Pageable page);

    @Query(" select new com.fenglianfinance.bill.domain.SimpleBackLogAmount(sum(b.financingAmount) as amount ,type) from BackLogItem b where b.valid='VALID' group by b.type ")
    public List<SimpleBackLogAmount> sumFinancingAmountByType();

}
