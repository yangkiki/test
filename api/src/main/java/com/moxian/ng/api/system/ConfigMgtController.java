/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.system;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.GlobalConfig;
import com.moxian.ng.exception.InvalidRequestException;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.GlobalConfigDetails;
import com.moxian.ng.model.GlobalConfigForm;
import com.moxian.ng.service.ConfigService;
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
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_API_CONF)
@RestController
public class ConfigMgtController {
    
    private static final Logger log = LoggerFactory.getLogger(ConfigMgtController.class);
    
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
    
    @RequestMapping(value = {""}, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Void> saveConfig(@RequestBody @Valid GlobalConfigForm form, BindingResult errors, UriComponentsBuilder uriComponentsBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("save config data@" + form);
        }
        
        if(errors.hasErrors()){
            throw new InvalidRequestException("validation failed.", errors);
        }
        
        configService.writeBorrowerRate(form.getBorrowerRate());
        configService.writeFee(form.getFee());
        configService.writeMaxTenderRate(form.getMaxTenderRate());
        configService.writeMerchantCustID(form.getMerchantCustId());
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
