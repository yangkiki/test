package com.moxian.ng.config;

import java.util.Properties;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.moxian.ng.domain.UserAccount;

@Configuration
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
//enable jpa repositories
@EnableJpaRepositories(basePackages = {"com.moxian.ng.repository"})
@EnableJpaAuditing(auditorAwareRef = "auditor")
@PropertySource(value = "classpath:/database.properties", ignoreResourceNotFound = true) //
public class JpaConfig implements TransactionManagementConfigurer {

    private static final Logger log = LoggerFactory.getLogger(JpaConfig.class);

    @Inject
    private Environment env;

    @Inject
    private DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setMappingResources("META-INF/orm.xml");
        emf.setPersistenceXmlLocation("classpath:META-INF/persistence.xml");
        emf.setPackagesToScan("com.moxian.ng.domain");
        emf.setPersistenceProvider(new HibernatePersistenceProvider());
        emf.setJpaProperties(jpaProperties());
        emf.setJpaDialect(new HibernateJpaDialect());
        return emf;
    }

    private Properties jpaProperties() {
        Properties extraProperties = new Properties();
        extraProperties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
        extraProperties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        extraProperties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

        if (log.isDebugEnabled()) {
            log.debug(" hibernate.dialect @" + env.getProperty("hibernate.dialect"));
        }
        if (env.getProperty("hibernate.dialect") != null) {
            extraProperties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        }

       // extraProperties.put("hibernate.hbm2ddl.import_files", "/import.sql");
        return extraProperties;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return transactionManager();
    }

    @Bean
    @Named("auditor")
    public AuditorAware<UserAccount> auditor() {
        if (log.isDebugEnabled()) {
            log.debug("get current auditor@@@@");
        }

        return new AuditorAware<UserAccount>() {

            @Override
            public UserAccount getCurrentAuditor() {

                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth == null) {
                    return null;
                }

                if (auth instanceof AnonymousAuthenticationToken) {
                    return null;
                }

                return (UserAccount) auth.getPrincipal();
            }
        };

    }

}
