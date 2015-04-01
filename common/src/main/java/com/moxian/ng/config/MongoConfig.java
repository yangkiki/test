/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoExceptionTranslator;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StringUtils;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;

/**
 *
 * @author hantsy
 */

@Configuration
@EnableMongoRepositories(basePackages = "com.fenglianfinance.bill.repository")
public class MongoConfig extends AbstractMongoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(MongoConfig.class);

    @Inject
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongo.dbname");
    }

    @Override
    public Mongo mongo() throws Exception {
        final String mongoHost = env.getProperty("mongo.host");

        if (log.isInfoEnabled()) {
            log.info("connecting mongo @" + mongoHost);
        }
        
        MongoClientOptions options = new MongoClientOptions.Builder()
                .connectionsPerHost(env.getProperty("mongo.options.connectionsPerHost", Integer.class))
                .threadsAllowedToBlockForConnectionMultiplier(
                        env.getProperty("mongo.options.threadsAllowedToBlockForConnectionMultiplier", Integer.class))
                .connectTimeout(env.getProperty("mongo.options.connectTimeout", Integer.class))
                .maxWaitTime(env.getProperty("mongo.options.maxWaitTime", Integer.class))
                .socketKeepAlive(env.getProperty("mongo.options.socketKeepAlive", Boolean.class))
                .socketTimeout(env.getProperty("mongo.options.socketTimeout", Integer.class))
                .readPreference(ReadPreference.secondaryPreferred()).build();

       

        List<ServerAddress> addrs = new ArrayList<>();
        String[] hosts = mongoHost.split(",");

        for (String h : hosts) {
            String[] nameAndPorts = h.split(":");
            if (nameAndPorts.length == 1) {
                addrs.add(new ServerAddress(nameAndPorts[0]));
            } else if (nameAndPorts.length == 2) {
                addrs.add(new ServerAddress(nameAndPorts[0], Integer.parseInt(nameAndPorts[1])));
            }
        }

        Mongo mongo = new MongoClient(addrs, options);

        return mongo;
    }

    @Override
    protected UserCredentials getUserCredentials() {
        String username = env.getProperty("mongo.username");
        String password = env.getProperty("mongo.password");

        if (log.isInfoEnabled()) {
            log.info("using credentials@" + username + ":" + password);
        }

        if (!StringUtils.hasText(username)) {
            return null;
        }

        return new UserCredentials(username, password);
    }

    @Bean
    public GridFsTemplate gridfsTemplate() {
        try {
            return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
        } catch (Exception ex) {
            log.error("exception captured when building a gridfsTemplate instance@" + ex.getMessage());
        }
        return null;
    }

    @Bean
    public MongoExceptionTranslator exceptionTranslator() {
        return new MongoExceptionTranslator();
    }

    @Bean
    public LoggingEventListener loggingEventListener() {
        return new LoggingEventListener();
    }

}
