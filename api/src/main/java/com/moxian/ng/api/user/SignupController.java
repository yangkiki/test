/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.user;

import com.moxian.ng.exception.InvalidRequestException;
import com.moxian.ng.model.*;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.service.UserService;
import javax.inject.Inject;
import javax.validation.Valid;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ApiErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
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
 * @author hantsy<hantsy@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API)
@RestController
public class SignupController {
    
    private static final Logger log = LoggerFactory.getLogger(SignupController.class);
    
    @Inject
    private UserService userService;
    
    @RequestMapping(value = {"/signup"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> signup(@RequestBody @Valid SignupForm form, BindingResult errors, UriComponentsBuilder uriComponentsBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("signup data@" + form);
        }
        
        if (errors.hasErrors()) {
            throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
        }
        
        UserAccountDetails saved = userService.registerUser(form);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_USERS + "/{id}")
                .buildAndExpand(saved.getId()).toUri());
        
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
