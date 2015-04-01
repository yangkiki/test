/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import com.moxian.ng.domain.GrantedPermission;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author hansy
 */
public interface GrantedPermissionRepository extends JpaRepository<GrantedPermission, Long> {

    public GrantedPermission findByRoleAndPermission(String roleName, String p);

    public List<GrantedPermission> findByRole(String role);

    @Query("select g.permission from GrantedPermission g where g.role=?1")
    public List<String> findGrantedPermisionNamesByRoleName(String role);

    @Query("delete from  GrantedPermission g where g.role=?1 and g.permission=?2")
    @Modifying
    public void removeByRoleNameAndPermission(String roleName, String p);

}
