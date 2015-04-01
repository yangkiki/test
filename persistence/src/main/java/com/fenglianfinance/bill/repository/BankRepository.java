/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.Bank;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author hansy
 */
public interface BankRepository extends JpaRepository<Bank, Long>, //
        JpaSpecificationExecutor<Bank>,
        BankRepositoryCustom {

    @Query("update Bank set active=?2 where id=?1")
    @Modifying
    public void updateStatus(Long id, Boolean status);

    public Bank findByName(String name);

//    public List<Bank> filterByKeyword(String q);
}
