package com.moxian.ng.api.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

@Order(200)
public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    private static final Logger log = LoggerFactory.getLogger(SecurityInitializer.class);

    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {

        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", DelegatingFilterProxy.class);
        corsFilter.addMappingForUrlPatterns(
                EnumSet.of(
                        DispatcherType.ERROR,
                        DispatcherType.REQUEST,
                        DispatcherType.FORWARD,
                        DispatcherType.INCLUDE,
                        DispatcherType.ASYNC),
                false,
                "/*"
        );

//        FilterRegistration.Dynamic springSessionRepositoryFilter = servletContext.addFilter("springSessionRepositoryFilter", DelegatingFilterProxy.class);
//        springSessionRepositoryFilter.addMappingForUrlPatterns(
//                EnumSet.of(
//                        DispatcherType.ERROR,
//                        DispatcherType.REQUEST,
//                        DispatcherType.FORWARD,
//                        DispatcherType.INCLUDE,
//                        DispatcherType.ASYNC),
//                false,
//                "/*"
//        );

    }

}
