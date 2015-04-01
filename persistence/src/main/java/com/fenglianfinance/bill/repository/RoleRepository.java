package com.fenglianfinance.bill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.fenglianfinance.bill.domain.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    public Role findByName(String code);

    public List<Role> findByActiveIsTrue();

    
    @Query("update Role set active=?2 where id=?1")
    @Modifying
    public void updateActiveStatus(Long id, boolean b);

}
