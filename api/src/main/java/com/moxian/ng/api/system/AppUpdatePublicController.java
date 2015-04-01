/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.system;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.AppUpdateDetails;
import com.moxian.ng.service.AppUpdateService;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_API_APPUPDATE)
@RestController
public class AppUpdatePublicController {

    private static final Logger log = LoggerFactory.getLogger(AppUpdatePublicController.class);

    @Inject
    private AppUpdateService appUpdateService;

    @RequestMapping(value = {""}, method = RequestMethod.GET , params = "f=LATEST")
    @ResponseBody
    public ResponseEntity<AppUpdateDetails> getLatestUpdate() {
        if (log.isDebugEnabled()) {
            log.debug("get app update data@");
        }

        AppUpdateDetails details = appUpdateService.getLatestUpdate();

        if (log.isDebugEnabled()) {
            log.debug("app version value @" + details);
        }

        return new ResponseEntity<>(details, HttpStatus.OK);
    }

}
