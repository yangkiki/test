/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import com.moxian.ng.domain.AppUpdate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
public interface AppUpdateRepository extends JpaRepository<AppUpdate, Long>, //
        JpaSpecificationExecutor<AppUpdate> {

}
