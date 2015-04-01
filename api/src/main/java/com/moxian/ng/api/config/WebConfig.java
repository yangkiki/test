package com.moxian.ng.api.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.moxian.ng.api.filter.UserViewIntercepter;
import com.moxian.ng.repository.UserViewStatisticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moxian.ng.Constants;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;

@Configuration
@EnableWebMvc
@ComponentScan(//
        useDefaultFilters = false,
        basePackageClasses = {Constants.class}, //
        includeFilters = { //

            @Filter(
                    type = FilterType.ANNOTATION, //
                    value = {//
                        Controller.class,//
                        RestController.class,//
                        ControllerAdvice.class//
                    }//
            ) //
        }//
//       , excludeFilters = {
//            @Filter(
//                    type = FilterType.ANNOTATION,
//                    value = {
//                        Configuration.class
//                    }
//            )
//        }
)
public class WebConfig extends SpringDataWebConfiguration {

    private static final Logger log = LoggerFactory.getLogger(WebConfig.class);

    public static final String MAXIMUM_FILE_SIZE = "8192KB";
    public static final String GZIP_COMPRESSION_MIME_TYPES
            = MediaType.APPLICATION_JSON_VALUE + "," + "application/javascript" + "," + "text/css";

    @Inject
    ObjectMapper objectMapper;

    @Inject
    UserViewStatisticRepository userViewStatisticRepository;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**", "/webjars/**")//
                .addResourceLocations("classpath:/META-INF/resources/public/", "classpath:/META-INF/resources/webjars/")//
                .setCachePeriod(0);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userViewIntercepter()).addPathPatterns("/api/**");
    }
    
    @Bean
    public UserViewIntercepter userViewIntercepter(){
        return new UserViewIntercepter(userViewStatisticRepository);
    }

//    @Bean
//    public InternalResourceViewResolver getInternalResourceViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("classpath:/resources/");
//        resolver.setSuffix(".jsp");
//        return resolver;
//    }
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(exceptionHandlerExceptionResolver());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {   
        registry.beanName();
        registry.jsp("/WEB-INF/views/", ".jsp");
        //super.configureViewResolvers(registry); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/test");
        // super.addViewControllers(registry);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false);
        configurer.favorPathExtension(false);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        List<HttpMessageConverter<?>> messageConverters = messageConverters();

        converters.addAll(messageConverters);
    }

    private List<HttpMessageConverter<?>> messageConverters() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        //jackson2
        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        jackson2Converter.setObjectMapper(objectMapper);
        messageConverters.add(jackson2Converter);

        ByteArrayHttpMessageConverter byteArrayConverter = new ByteArrayHttpMessageConverter();
        messageConverters.add(byteArrayConverter);

        // string
//        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(
//            Charset.forName("UTF-8"));
//        stringConverter.setWriteAcceptCharset(false);
//        messageConverters.add(stringConverter);
        //       messageConverters.add(new SourceHttpMessageConverter());
        FormHttpMessageConverter formConverter = new AllEncompassingFormHttpMessageConverter();
        messageConverters.add(formConverter);
        return messageConverters;
    }

    @Bean
    public ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionHandlerExceptionResolver = new ExceptionHandlerExceptionResolver();

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jackson2Converter = new MappingJackson2HttpMessageConverter();
        jackson2Converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
        jackson2Converter.setObjectMapper(objectMapper);
        messageConverters.add(jackson2Converter);

        exceptionHandlerExceptionResolver.setMessageConverters(messageConverters);

        return exceptionHandlerExceptionResolver;
    }

    @Bean
    public StandardServletMultipartResolver multipartResolver() {
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        return resolver;
    }

}
