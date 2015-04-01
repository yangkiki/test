package com.moxian.ng.broker.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(0)
public class AppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ //
            AppConfig.class,//
            RabbitMessagingConsumerConfig.class,//       
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
