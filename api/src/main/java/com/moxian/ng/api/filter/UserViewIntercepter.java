/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.moxian.ng.domain.UserViewStatistic;
import com.moxian.ng.repository.UserViewStatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class UserViewIntercepter extends HandlerInterceptorAdapter {

    private static final Logger log = LoggerFactory.getLogger(UserViewIntercepter.class);


    private UserViewStatisticRepository userViewStatisticRepository;

    public UserViewIntercepter(UserViewStatisticRepository userViewStatisticRepository) {
        this.userViewStatisticRepository = userViewStatisticRepository;
    }

    public UserViewIntercepter() {
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("call preHandle in UserViewIntercepter @");
        }

        String ipAddr = request.getRemoteAddr();
        String user = request.getRemoteUser();
        String url=request.getRequestURI();

        // TODO 
        // 1. create an Entity class UserViewStatistic
        // 2. save to database.
        UserViewStatistic uv=new UserViewStatistic();
        uv.setIpAddr(ipAddr);
        uv.setUser(user);
        uv.setUrl(url);
        userViewStatisticRepository.save(uv);

        //save to database.
        //return super.preHandle(request, response, handler);
    }

}
