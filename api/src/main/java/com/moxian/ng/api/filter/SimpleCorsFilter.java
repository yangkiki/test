package com.moxian.ng.api.filter;

import java.io.IOException;
import javax.inject.Named;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.filter.OncePerRequestFilter;

@Named("corsFilter")
public class SimpleCorsFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(SimpleCorsFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        if (log.isDebugEnabled()) {
            log.debug("call doFilter in SimpleCORSFilter @");
        }

        response.setHeader("Access-Control-Allow-Origin", "*");
 //       response.addHeader("X-FRAME-OPTIONS", "SAMEORIGIN");

        if (request.getHeader("Access-Control-Request-Method") != null && "OPTIONS".equals(request.getMethod())) {

            if (log.isDebugEnabled()) {
                log.debug("do pre flight...");
            }

            response.setHeader("Access-Control-Allow-Methods", "POST,GET,HEAD,OPTIONS,PUT,DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "x-requested-with,Content-Type,Accept,x-auth-token,x-xsrf-token,Origin,Access-Control-Request-Method,Access-Control-Request-Headers,Access-Control-Allow-Origin");
            //response.setHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin,x-auth-token");
        } else {
            filterChain.doFilter(request, response);
        }
    }

}
