/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import com.moxian.ng.domain.Role;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public class PermissionRepositoryImpl implements PermissionRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Role> findByActiveAndFetchResource(boolean active) {

        return em.createQuery("select ro from Role ro left join fetch ro.resources re where ro.active=:active  ")
                .setParameter("active", active)
                .getResultList();
    }

}
