/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import com.moxian.ng.domain.UserAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;


    @Override
    public Page<UserAccount> findAllGroupFriends(Long userId, Pageable pageable) {

        TypedQuery<UserAccount> query = em.createQuery(" select  c.connectedUser from Connections c inner  join  c.memberUser as u where  u.id=:userId  and c.type='BILATERAL'  order by  u.name asc  " ,UserAccount.class);
        query.setParameter("userId",userId);

        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        Page<UserAccount> page = new PageImpl<UserAccount>(query.getResultList());

        return page;
    }
}
