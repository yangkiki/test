/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.sdoc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

/**
 *
 * @author hantsy
 */
@Configuration
@EnableSwagger
// Loads the spring beans required by the framework
public class SwaggerConfig {

    private static final Logger log = LoggerFactory.getLogger(SwaggerConfig.class);

    private SpringSwaggerConfig springSwaggerConfig;

    /**
     * Required to autowire SpringSwaggerConfig
     */
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    /**
     * Every SwaggerSpringMvcPlugin bean is picked up by the swagger-mvc
     * framework - allowing for multiple swagger groups i.e. same code base
     * multiple swagger resource listings.
     */
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {

        Class userAccountClazz = null;
        Class pageableClazz = null;

        try {
            userAccountClazz = ClassUtils.forName("com.moxian.ng.domain.UserAccount", null);
        } catch (ClassNotFoundException ex) {
            log.error("ex@" + ex);
        } catch (LinkageError ex) {
            log.error("ex@" + ex);
        }
        try {
            pageableClazz = ClassUtils.forName("org.springframework.data.domain.Pageable", null);
        } catch (ClassNotFoundException ex) {
            log.error("ex@" + ex);
        } catch (LinkageError ex) {
            log.error("ex@" + ex);
        }

        List<Class> classList = new ArrayList<>();
        if (userAccountClazz == null) {
            classList.add(userAccountClazz);
        }
        if (pageableClazz == null) {
            classList.add(pageableClazz);
        }

        classList.add(HttpEntity.class);
        classList.add(ResponseEntity.class);
        classList.add(Callable.class);
        classList.add(DeferredResult.class);
        classList.add(WebAsyncTask.class);

        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiVersion("V1.0")//
                .apiInfo(apiInfo())//
                .includePatterns(".*api.*")
                .ignoredParameterTypes(classList.toArray(new Class[classList.size()]))
                .build();

    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(//
                "FengLian BILL RESTful API",//
                "FengLian BILL RESTful APIs for developers", //
                "FengLian BILL API terms of service",//
                "hantsy@gmail.com",//
                "Commericial Lisence", //
                "lisence URL");
        return apiInfo;
    }

}
