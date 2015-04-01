package com.fenglianfinance.bill.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fenglianfinance.bill.domain.Enterprise;

public interface EnterpriseRepository extends JpaRepository<Enterprise, Long>, JpaSpecificationExecutor<Enterprise> {


    public Enterprise findByName(String name);

    @Modifying
    @Query("update Enterprise enterprise set enterprise.active =:active where enterprise.id=:id")
    void deactivate(@Param("id")Long id,@Param("active")boolean active);

    @Modifying
    @Query("update Enterprise enterprise set enterprise.verifyState = :verifyState  where enterprise.id=:id")
    void pass(@Param("id") Long id, @Param("verifyState") Enterprise.VerifyStatus verifyState);

    @Modifying
    @Query("update Enterprise enterprise set enterprise.verifyState = :verifyState, enterprise.verifyCause = :verifyCause  where enterprise.id=:id")
    void reject(@Param("id") Long id, @Param("verifyCause") String verifyCause,
            @Param("verifyState") Enterprise.VerifyStatus verifyState);

    public Enterprise findByUserAccountId(Object object);

	public List<Enterprise> findByActiveIsTrue();

}
