package com.moxian.ng.api.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

@Order(0)
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final Logger log = LoggerFactory.getLogger(AppInitializer.class);

    public static final String MAXIMUM_FILE_SIZE = "8192KB";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
//        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", DelegatingFilterProxy.class);
//        corsFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.ASYNC), false, "/*");

        servletContext.addListener(new RequestContextListener());

        super.onStartup(servletContext);
    }

    @Override
    protected void customizeRegistration(Dynamic registration) {
        super.customizeRegistration(registration);
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");

        // enable servlet 3 upload support.
        registration.setMultipartConfig(new MultipartConfigElement("", 8192 * 1024, 8192 * 1024, 0));
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ //
            AppConfig.class, //
            //DataSourceConfig.class, //
            //JpaConfig.class, //
            // MongoConfig.class,//
            RabbitMessagingConfig.class, //
            RedisHttpSessionConfig.class,//
            SecurityConfig.class,
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        Class swaggerConfigClass = null;

        try {
            swaggerConfigClass = ClassUtils.forName("com.moxian.ng.sdoc.config.SwaggerConfig", null);
        } catch (ClassNotFoundException ex) {
            swaggerConfigClass = null;
            log.error("exception is thrown when loading SwaggerConfig class [SwaggerConfig]...@" + ex);
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

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("UTF-8");
        encodingFilter.setForceEncoding(true);

        return new Filter[]{encodingFilter};
    }

//    class SimpleCORSFilter implements Filter {
//
//        @Override
//        public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
//            if (log.isDebugEnabled()) {
//                log.debug("@@@corsfilter...@@@");
//            }
//            HttpServletResponse response = (HttpServletResponse) res;
//            response.setHeader("Access-Control-Allow-Origin", "*");
//            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
//            response.setHeader("Access-Control-Max-Age", "3600");
//            //response.setHeader("Access-Control-Allow-Headers", "Authorization, x-auth-token, x-requested-with");
//            response.setHeader("Access-Control-Allow-Headers", "*");
//            chain.doFilter(req, res);
//        }
//
//        @Override
//        public void init(FilterConfig filterConfig) {
//        }
//
//        @Override
//        public void destroy() {
//        }
//    }
}
