package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
//import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fenglianfinance.bill.domain.Product;
import com.fenglianfinance.bill.report.model.FundingDetails;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>,
        ProductRepositoryCustom {

    Product findByName(String name);

    @Modifying
    @Query("update Product product set product.active = false where product.id = :id ")
    void deactivate(@Param("id") Long id);

    Page<Product> findByActiveIsTrueAndType(Product.Type type, Pageable page);

    @Query("select product FROM Product product  where product.type = 'DEMAND' ")
    Product findProductByDemand();

    @Query("select product FROM Product product  where product.promoted = true ")
    Product findByPromoted();

    public Page<Product> findByActiveIsTrue(Pageable page);

    @Query(" select pp.id from PurchaseOrder p INNER JOIN p.product pp where p.active =true and p.status in ('REPAYMENT_COMPLETED','IN_REPAYMENT') and p.accruedEndDate=:accruedEndDate and pp.status='SOLD_OUT' group by pp.id   ")
    public List<Long> findToCompletedProduct(@Param("accruedEndDate") LocalDate accruedEndDate);

}
