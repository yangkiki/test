/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api;

import com.moxian.ng.model.GatewayResponseForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author hansy
 */
@Controller
@RequestMapping()
public class NoopController {

    private static final Logger log = LoggerFactory.getLogger(NoopController.class);

    @RequestMapping(value = "/ok",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE)
    public String ok(GatewayResponseForm form,Model model) {
        if (log.isDebugEnabled()) {
            log.debug("received.@  form {}",form);
        }

        model.addAttribute("resp",form);

        return "ok";
    }

    @RequestMapping(value = "/fail",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = MediaType.TEXT_HTML_VALUE)
    public String fail(GatewayResponseForm form,Model model) {
        if (log.isDebugEnabled()) {
            log.debug("fail.@ form {}",form);
        }

        model.addAttribute("resp",form);

        return "fail";
    }
}
