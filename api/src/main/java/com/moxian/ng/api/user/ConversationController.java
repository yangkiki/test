/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.user;

import com.moxian.ng.model.BooleanValue;
import com.moxian.ng.service.TokenService;
import javax.inject.Inject;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.TokenValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + "/conversations")
@RestController
public class ConversationController {

    private static final Logger logger = LoggerFactory.getLogger(ConversationController.class);

    @Inject
    private TokenService tokenService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<TokenValue> begin(@RequestParam("salt") String salt) {

        if (logger.isDebugEnabled()) {
            logger.debug(" @@ begin the conversation, generating conversation id based on {}", salt);
        }

        String token = tokenService.generateConversationId(salt);

        return new ResponseEntity<>(new TokenValue(token), HttpStatus.OK);
    }

    @RequestMapping(value = "{cid}", method = RequestMethod.GET, params = "action=CHECK_EXISTENCE")
    @ResponseBody
    public ResponseEntity<BooleanValue> exists(@PathVariable("cid") String cid) {

        if (logger.isDebugEnabled()) {
            logger.debug(" @@ check if the conversation exists: {}", cid);
        }

        boolean existed = tokenService.exists(cid);

        return new ResponseEntity<>(new BooleanValue(existed), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{cid}", method = RequestMethod.GET, params = "action=VALIDATE")
    @ResponseBody
    public ResponseEntity<BooleanValue> validate(@PathVariable("cid") String cid, @RequestParam("salt") String salt) {

        if (logger.isDebugEnabled()) {
            logger.debug(" @@ check if the conversation exists: {}", cid + ":" + salt);
        }

        boolean existed = tokenService.validate(cid, salt);

        return new ResponseEntity<>(new BooleanValue(existed), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "{cid}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> end(@PathVariable("cid") String cid) {

        if (logger.isDebugEnabled()) {
            logger.debug(" @@ end the conversation: {}", cid);
        }

        tokenService.delete(cid);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
