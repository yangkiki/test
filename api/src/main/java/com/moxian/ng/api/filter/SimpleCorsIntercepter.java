/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class SimpleCorsIntercepter extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(SimpleCorsIntercepter.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("call preHandle in SimpleCorsIntercepter @");
        }

        response.setHeader("Access-Control-Allow-Origin", "*");

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {

            if (log.isDebugEnabled()) {
                log.debug("do pre flight...");
            }

            response.setHeader("Access-Control-Allow-Methods", "POST,GET,HEAD,OPTIONS,PUT,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,Accept,x-auth-token,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Access-Control-Allow-Origin");
            //response.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,x-auth-token");
        }
        
       return super.preHandle(request, response, handler); //To change body of generated methods, choose Tools | Templates.
    }



}
