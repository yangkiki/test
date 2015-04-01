/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.AppUpdate;
import com.moxian.ng.domain.AppUpdate_;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.AppUpdateDetails;
import com.moxian.ng.model.AppUpdateForm;
import com.moxian.ng.repository.AppUpdateRepository;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
@Service
@Transactional
public class AppUpdateService {

    private static final Logger log = LoggerFactory.getLogger(AppUpdateService.class);

    @Inject
    private AppUpdateRepository appUpdateRepository;

    public AppUpdateDetails getLatestUpdate() {
        if (log.isDebugEnabled()) {
            log.debug("app update @");
        }

        PageRequest req = new PageRequest(0, 1, new JpaSort(Sort.Direction.DESC, AppUpdate_.createdDate));

        Page<AppUpdate> result = appUpdateRepository.findAll(req);

        if (result.getTotalElements() == 0) {
            return null;
        }

        return DTOUtils.map(result.getContent().get(0), AppUpdateDetails.class);
    }

    public Page<AppUpdateDetails> getAllUpdates(Pageable p) {
        if (log.isDebugEnabled()) {
            log.debug("app update @" + p);
        }

        Page<AppUpdate> result = appUpdateRepository.findAll(p);

        if (result.getTotalElements() == 0) {
            return null;
        }

        return DTOUtils.mapPage(result, AppUpdateDetails.class);
    }

    public AppUpdateDetails saveUpdate(AppUpdateForm form) {
        if (log.isDebugEnabled()) {
            log.debug("app update @" + form);
        }

        AppUpdate data = DTOUtils.map(form, AppUpdate.class);

        AppUpdate result = appUpdateRepository.save(data);

        if (log.isDebugEnabled()) {
            log.debug("result @" + result);
        }

        return DTOUtils.map(result, AppUpdateDetails.class);
    }

    public void updateUpdate(Long id, AppUpdateForm form) {
        if (log.isDebugEnabled()) {
            log.debug("app update @" + form);
        }

        AppUpdate data = appUpdateRepository.findOne(id);

        if (data == null) {
            throw new ResourceNotFoundException(id);
        }

        DTOUtils.mapTo(form, data);
        appUpdateRepository.save(data);
    }

    public void deleteUpdate(Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete update @" + id);
        }

        appUpdateRepository.delete(id);
    }

    public AppUpdateDetails findUpdateById(Long id) {
        Assert.notNull(id, "id can not be null");
        if (log.isDebugEnabled()) {
            log.debug("app update @" + id);
        }

        AppUpdate result = appUpdateRepository.findOne(id);

        if (result == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(result, AppUpdateDetails.class);
    }

}
