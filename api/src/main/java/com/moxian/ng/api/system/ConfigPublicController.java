/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.system;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.GlobalConfig;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.GlobalConfigDetails;
import com.moxian.ng.service.ConfigService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_API_CONF)
//@RestController
public class ConfigPublicController {
    
    private static final Logger log = LoggerFactory.getLogger(ConfigPublicController.class);
    
    @Inject
    private ConfigService configService;
    
    @RequestMapping(value = {""}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<GlobalConfigDetails> getConfig() {
        if (log.isDebugEnabled()) {
            log.debug("get config data@");
        }
        
        GlobalConfig cfg = new GlobalConfig();
        cfg.setBorrowerRate(configService.readBorrowerRate());
        cfg.setFee(configService.readFee());
        cfg.setMaxTenderRate(configService.readMaxTenderRate());
        cfg.setMerchantCustId(configService.readMerchantCustID());
          
        if (log.isDebugEnabled()) {
            log.debug("config value @" + cfg);
        }
        
        return new ResponseEntity<>(DTOUtils.map(cfg, GlobalConfigDetails.class), HttpStatus.OK);
    }
    
}
