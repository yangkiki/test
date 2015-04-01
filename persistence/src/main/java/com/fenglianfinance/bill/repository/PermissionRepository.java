package com.fenglianfinance.bill.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fenglianfinance.bill.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

public interface PermissionRepository extends JpaRepository<Permission, Long>, PermissionRepositoryCustom {

    public List<Permission> findByActiveIsTrue(Sort sort);

    public List<Permission> findByActiveIsTrue();

    @Query("select p.name from Permission p where p.active=true")
    public List<String> findAllActivePermissionNames();

    public Permission findByName(String name);

    public Page<Permission> findByNameLike(String q, Pageable page);

}
