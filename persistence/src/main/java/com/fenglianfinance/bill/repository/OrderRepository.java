/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.fenglianfinance.bill.domain.PurchaseOrder;
import com.fenglianfinance.bill.domain.SimpleOrderAmount;

 /*
 * @author hansy
 */
public interface OrderRepository extends 
        OrderRepositoryCustom,//
        JpaRepository<PurchaseOrder, Long>, //
        JpaSpecificationExecutor<PurchaseOrder> ,//
        PagingAndSortingRepository<PurchaseOrder,Long>{

    @Query("update PurchaseOrder set status=?2 where id=?1")
    @Modifying
    public void updateStatus(Long id, PurchaseOrder.Status status);

    public PurchaseOrder findBySerialNumber(String posn);

    @Query("update PurchaseOrder set active=?2 where id=?1")
    @Modifying
    public void updateActiveStatus(Long id, boolean b);

    public long countByUserIdAndStatus(Long userId, PurchaseOrder.Status status);

    public Page<PurchaseOrder> findByUserId(Long userId, Pageable page);
    
    @Query(" select p from PurchaseOrder p INNER JOIN p.product pp where  p.active =true and p.status='PAID' and pp.type='DEMAND' ")
    public Page<PurchaseOrder> findDemandPaidPurchaseOrder(Pageable pageable);
    
    @Query(" select p from PurchaseOrder p INNER JOIN p.product pp where  p.active =true and p.status='PAID' and pp.type='DEMAND' ")
    public List<PurchaseOrder> findDemandPaidPurchaseOrder();
    
    @Query(" select p from PurchaseOrder p INNER JOIN p.product pp where  p.active =true and p.status='PAID' and pp.type in ('HOT','NEWBIE') ")
    public Page<PurchaseOrder> findNewbieOrHotPaidPurchaseOrder(Pageable pageable);
    
    @Query(" select p from PurchaseOrder p INNER JOIN p.product pp where  p.active =true and p.status='PAID' and pp.type <> 'DEMAND' and p.accruedEndDate=:accruedEndDate ")
    public Page<PurchaseOrder> findNotDemandPaid(@Param("accruedEndDate") LocalDate accruedEndDate,Pageable page);

	public Page<PurchaseOrder> findByActiveIsTrueAndProductId(Long productId,
			Pageable page);
	
	@Query(" select new com.fenglianfinance.bill.domain.SimpleOrderAmount(sum(p.amount) as amount ,pp.type) from PurchaseOrder p INNER JOIN p.product pp where  p.active =true and p.status not in ('CANCELED','PAYMENT_FAILED','PENDING_PAYMENT') group by pp.type ")
	public List<SimpleOrderAmount> sumOrderAmountByType();
	
	/**
	 * 通过username获取该用户最近已支付的num条爆款订单
	 * 
	 * @param username
	 * @return
	 * @author wangli@flf77.com
	 * @updateTime 2015-01-26 16:36
	 */
	@Query(" select p from PurchaseOrder p INNER JOIN p.product pp where p.active =true and p.status='PAID' and pp.type='HOT' and p.user.username = ?1 order by p.paidDate desc ")
	public Page<PurchaseOrder> findHotPaidPurchaseOrdersPaidDateDesByUsername(
			String username, Pageable page);
	
	/**
	 * 通过username获取该用户最近即将到款的num条爆款订单
	 * 
	 * @param username
	 * @return
	 * @author wangli@flf77.com
	 * @updateTime 2015-01-26 16:59
	 */
	@Query(" select p from PurchaseOrder p INNER JOIN p.product pp where p.active =true and p.status='PAID' and pp.type='HOT' and p.user.username = ?1 order by p.completedDate desc ")
	public Page<PurchaseOrder> findHotPaidPurchaseOrdersCompletedDateDesByUsername(
			String username, Pageable page);
	
	/**
	 * 查找针对某个产品投资成功的订单
	 * 
	 * @param productId
	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月11日 下午2:32:26
	 */
	@Query(" select p from PurchaseOrder p INNER JOIN p.product pp where p.active =true and p.status='PAID' and pp.id = ?1 order by p.paidDate desc ")
	public Page<PurchaseOrder> findPaidOrders(Long productId, Pageable page);
	
}
