package com.moxian.ng.api.config;

import java.util.List;

import javax.inject.Inject;

import com.moxian.ng.api.security.SimpleUserDetailsServiceImpl;
import com.moxian.ng.domain.Permission;
import com.moxian.ng.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.moxian.ng.domain.Permission_;
import com.moxian.ng.repository.GrantedPermissionRepository;
import com.moxian.ng.repository.PermissionRepository;
import com.moxian.ng.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Inject
    private UserRepository userRepository;

    @Inject
    private RoleRepository roleRepository;

    @Inject
    private PermissionRepository permissionRepository;

    @Inject
    private GrantedPermissionRepository grantedPermissionRepository;

//    @Inject
//    private SessionRepository<? extends ExpiringSession> sessionRepository;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(//
                "/**/*.html",//
                "/css/**",//
                "/js/**",//
                "/i18n/**",//
                "/libs/**",//
                "/img/**",//
                "/**/*.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        final SessionRepositoryFilter<? extends ExpiringSession> sessionRepositoryFilter = new SessionRepositoryFilter<>(
//            sessionRepository);
//        sessionRepositoryFilter.setHttpSessionStrategy(new HeaderHttpSessionStrategy());

        http
                //.addFilterBefore(sessionRepositoryFilter, ChannelProcessingFilter.class)
                .csrf()
                    .disable()
                .headers()
                    .frameOptions()
                    .disable();

//        http
//            .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//        http
//                .authorizeRequests()
//                .antMatchers("/api/authenticate", "/api/register", "/api/public/**")
//                .permitAll();
//
//        http.
//                authorizeRequests()
//                .antMatchers("/api/mgt/**")
//                .hasRole("ADMIN");
//
//        http.authorizeRequests()
//                .antMatchers("/api/**")
//                .authenticated();
        configureInterceptedUris(http);

        //configureInterceptedUris(http);
//        SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> securityConfigurerAdapter = new XAuthTokenConfigurer(
//                userDetailsServiceBean());
//        http.apply(securityConfigurerAdapter);
        // .and()
        // .httpBasic()
        // .and()
        // .csrf()
        // .disable();
        // .formLogin().loginPage("/login").permitAll().and().logout()
        // .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
        authManagerBuilder
                .userDetailsService(new SimpleUserDetailsServiceImpl(this.userRepository, this.permissionRepository, this.grantedPermissionRepository))
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return super.userDetailsServiceBean();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    private void configureInterceptedUris(HttpSecurity http) throws Exception {

        if (logger.isDebugEnabled()) {
            logger.debug("configuring intercepted uris... @@@@ ");
        }

        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry interceptUrlRegistry = http
                .authorizeRequests()
                .antMatchers("/api/authenticate", "/api/signup", "/api/signin","/api/signout", "/api/public/**")
                .permitAll();
//        http.
//                authorizeRequests()
//                .antMatchers("/api/mgt/**")
//                .hasRole("ADMIN")
//                .and();

        Sort sort = new JpaSort(Permission_.category, Permission_.position);
        List<Permission> perms = permissionRepository.findByActiveIsTrue(sort);

        for (Permission resource : perms) {
            interceptUrlRegistry
                    .regexMatchers(HttpMethod.valueOf(resource.getRequestMethod()), resource.getRequestUri())
                    .hasAuthority(resource.getName());
        }

        interceptUrlRegistry
                .antMatchers("/api/**")
                .authenticated();
        interceptUrlRegistry
                .antMatchers("/**")
                .permitAll();

        if (logger.isDebugEnabled()) {
            logger.debug("end of configureInterceptedUris@ ");
        }
    }

}
