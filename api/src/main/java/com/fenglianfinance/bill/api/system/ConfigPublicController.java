/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.api.system;

import com.fenglianfinance.bill.DTOUtils;
import com.fenglianfinance.bill.domain.GlobalConfig;
import com.fenglianfinance.bill.exception.InvalidRequestException;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.GlobalConfigDetails;
import com.fenglianfinance.bill.model.GlobalConfigForm;
import com.fenglianfinance.bill.service.ConfigService;
import javax.inject.Inject;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author hansy
 */
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_API_CONF)
@RestController
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
