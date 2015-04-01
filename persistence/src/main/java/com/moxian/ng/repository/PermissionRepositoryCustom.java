/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import java.util.List;

import com.moxian.ng.domain.Role;

public interface PermissionRepositoryCustom {

    public List<Role> findByActiveAndFetchResource(boolean active);
    
   
}
