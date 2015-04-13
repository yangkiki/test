/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.user;

import com.moxian.ng.model.ApiConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API)
@RestController
public class SignoutController {
    
    private static final Logger log = LoggerFactory.getLogger(SignoutController.class);
    
    
    @RequestMapping(value = {"/signout"}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> signout(HttpSession session) {
        if (log.isDebugEnabled()) {
            log.debug("signout@@@" );
        }
        
        session.invalidate();
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
