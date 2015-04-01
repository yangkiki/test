/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.system;

import com.moxian.ng.model.AppUpdateDetails;
import com.moxian.ng.service.AppUpdateService;
import javax.inject.Inject;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.AppUpdateForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_API_APPUPDATE)
@RestController
public class AppUpdateMgtController {

    private static final Logger log = LoggerFactory.getLogger(AppUpdateMgtController.class);

    @Inject
    private AppUpdateService appUpdateService;


    @RequestMapping(value = {""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<AppUpdateDetails>> getAllUpdates(
            @PageableDefault(sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get all updates@");
        }

        Page<AppUpdateDetails> details = appUpdateService.getAllUpdates(page);

        if (log.isDebugEnabled()) {
            log.debug("app version value @" + details);
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<AppUpdateDetails> saveUpdate(@RequestBody AppUpdateForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save update@");
        }

        AppUpdateDetails details = appUpdateService.saveUpdate(form);

        if (log.isDebugEnabled()) {
            log.debug("app version value @" + details);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AppUpdateDetails> getAppUpdate(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("get app update data@"+ id);
        }

        AppUpdateDetails details = appUpdateService.findUpdateById(id);

        if (log.isDebugEnabled()) {
            log.debug("app version value @" + details);
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<AppUpdateDetails> updateAppUpdate(@PathVariable("id") Long id, @RequestBody AppUpdateForm form) {
        if (log.isDebugEnabled()) {
            log.debug("update AppUpdate@" + id);
        }

        appUpdateService.updateUpdate(id, form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<AppUpdateDetails> deleteAppUpdate(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete AppUpdate@" + id);
        }

        appUpdateService.deleteUpdate(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
