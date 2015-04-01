package com.moxian.ng.api.config;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.session.web.context.AbstractHttpSessionApplicationInitializer;

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
