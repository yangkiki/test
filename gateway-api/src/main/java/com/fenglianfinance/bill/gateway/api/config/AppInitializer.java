package com.fenglianfinance.bill.gateway.api.config;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.core.annotation.Order;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

@Order(0)
public class AppInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    private static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", DelegatingFilterProxy.class);
        corsFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC), false, "/*");

        servletContext.addListener(new RequestContextListener());

        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ //
            //
            AppConfig.class,//
            RabbitMessagingClientConfig.class,//
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class swaggerConfigClass = null;

        try {
            swaggerConfigClass = ClassUtils.forName("com.fenglianfinance.bill.sdoc.config.SwaggerConfig", null);
        } catch (Exception ex) {
            swaggerConfigClass = null;
            log.error("exception is thrown when loading SwaggerConfig class [com.fenglianfinance.bill.sdoc.config.SwaggerConfig]...@" + ex);
        }

        List<Class> classes = new ArrayList<>();

        classes.add(WebConfig.class);

        if (swaggerConfigClass != null) {
            classes.add(swaggerConfigClass);
        }

        return classes.toArray(new Class[classes.size()]);
    }


    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
