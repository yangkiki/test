package com.fenglianfinance.bill.api.config;

import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

@Order(100)
public class RedisHttpSessionApplicationInitializer extends AbstractHttpSessionApplicationInitializer 
{

    private static final Logger log = LoggerFactory.getLogger(RedisHttpSessionApplicationInitializer.class);

//    @Override
//    protected void beforeSessionRepositoryFilter(ServletContext servletContext) {
//        if(log.isDebugEnabled()){
//            log.debug("add filters before sessionRepositoryFilter@");
//        }
//        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", DelegatingFilterProxy.class);
//        corsFilter.addMappingForUrlPatterns(
//                EnumSet.of(
//                        DispatcherType.ERROR,
//                        DispatcherType.REQUEST,
//                        DispatcherType.FORWARD,
//                        DispatcherType.INCLUDE,
//                        DispatcherType.ASYNC),
//                false,
//                "/*"
//        );
//    }

}
