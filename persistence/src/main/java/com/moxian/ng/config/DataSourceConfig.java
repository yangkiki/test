package com.moxian.ng.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.inject.Inject;
import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static final String DRIVER_CLASS_NAME = "jdbc.driverClassName";
    private static final String URL = "jdbc.url";
    private static final String USERNAME = "jdbc.username";
    private static final String PASSWORD = "jdbc.password";

    @Inject
    private Environment env;

    @Bean
    @Profile("dev")
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
    }

    @Bean
    @Profile("staging")
    public DataSource testDataSource() {
        BasicDataSource bds = new BasicDataSource();
        bds.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
        bds.setUrl(env.getProperty(URL));
        bds.setUsername(env.getProperty(USERNAME));
        bds.setPassword(env.getProperty(PASSWORD));
        return bds;

       
    }

    /**
     * Add jnid naming to runtime container.
     * 
     * Tomcat example(context.xml):
     * 
     * <pre>
     * &lt;?xml version="1.0" encoding="UTF-8"?>
     * &lt;Context antiJARLocking="true" path="/mx-api">
     *    &lt;Resource auth="Container"
     *    name="jdbc/flbillDS"
     *    type="javax.sql.DataSource"
     *    scope="Shareable"
     *    url="".../>
     * &lt;/Context>
     * </pre>
     * @return 
     */
    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
//        JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//        jndiObjectFactoryBean.setJndiName("jdbc/flbillDS");
//        jndiObjectFactoryBean.setCache(true);
//        jndiObjectFactoryBean.setExpectedType(DataSource.class);
//        return (DataSource) jndiObjectFactoryBean.getObject();

      BasicDataSource bds = new BasicDataSource();
      bds.setDriverClassName(env.getProperty(DRIVER_CLASS_NAME));
      bds.setUrl(env.getProperty(URL));
      bds.setUsername(env.getProperty(USERNAME));
      bds.setPassword(env.getProperty(PASSWORD));
      return bds;

//        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//        dsLookup.setResourceRef(true);
//        DataSource dataSource = dsLookup.getDataSource("jdbc/flbillDS");
//        return dataSource;
    }

}
