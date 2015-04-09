/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import com.moxian.ng.domain.UserAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public interface UserRepositoryCustom {

    Page<UserAccount> findAllGroupFriends( Long userId, Pageable page);
}
