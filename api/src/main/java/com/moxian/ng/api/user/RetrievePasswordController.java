/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.user;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.NewPasswordForm;
import com.moxian.ng.service.UserService;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API_PUBLIC)
@RestController
public class RetrievePasswordController {

    private static final Logger logger = LoggerFactory.getLogger(RetrievePasswordController.class);

    @Inject
    private UserService userService;

//    public ResponseEntity<Void> sendRetrieveRequest(@RequestParam("mobile") String mobile) {
//        //call smsService directly
//        return null;
//    }
//
//    public ResponseEntity<SmsCaptchaResult> validateRetrieveSmsCode(@RequestBody SmsCaptchaRequest form) {
//        //call smsService directly
//
//        return null;  
//    }
    @RequestMapping(value = {"/retrievePassword"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> updatePassword(@RequestBody NewPasswordForm form) {

        if (logger.isDebugEnabled()) {
            logger.debug(" @@ NewPasswordForm is {}", form);
        }

        userService.retrievePasswordByMobileNumber(form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
