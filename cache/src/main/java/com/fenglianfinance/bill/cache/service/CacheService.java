/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.cache.service;

import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 *
 * @author hansy
 */
@Service
public class CacheService {

    private static final Logger log = LoggerFactory.getLogger(CacheService.class);

    private static final int DEFAULT_TIME_TO_LIVE = 24 * 3600;
    private final int timeToLive = DEFAULT_TIME_TO_LIVE;

    private final RedisSerializer<Object> objSer = new JdkSerializationRedisSerializer();

    @Inject
    private RedisTemplate<Object, Object> redisTemplate;

    public CacheService() {
    }

    public void set(Object key, final Object value, final int expire) {

        if (log.isDebugEnabled()) {
            log.debug("storing value in redis @" + key + ":" + value + ":" + expire);
        }

        redisTemplate.opsForValue().set(key, value, expire, TimeUnit.SECONDS);

//        if (value instanceof Map) {
//            if (log.isDebugEnabled()) {
//                log.debug("value is a map@");
//            }
//            redisTemplate.opsForHash().putAll(key, (Map) value);
//            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
//        } else {
//            if (log.isDebugEnabled()) {
//                log.debug("value is an object@");
//            }
//            final byte[] rawKey = rawKey(key);
//            redisTemplate.execute(new RedisCallback<Object>() {
//                @Override
//                public Object doInRedis(RedisConnection connection) throws DataAccessException {
//
//                    final byte[] serializedData = objSer.serialize(value);
//                    if (log.isDebugEnabled()) {
//                        log.debug("stored serialized object in cache@" + serializedData);
//                    }
//                    connection.set(rawKey, serializedData);
//                    connection.expire(rawKey, expire);
//
//                   
//                    return connection.closePipeline();
//                }
//            });
//        }
    }

    public void set(Object key, Object value) {
        set(key, value, timeToLive);
    }

    public Object get(String key) {
        if (log.isDebugEnabled()) {
            log.debug("retrieving value from key @" + key);
        }

        Object val = redisTemplate.opsForValue().get(key);
        if (log.isDebugEnabled()) {
            log.debug("retrieving value @" + val);
        }

        return val;

//        final byte[] rawKey = rawKey(key);
//
//        return redisTemplate.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                byte[] value = connection.get(rawKey);
//                final Object deserializedData = objSer.deserialize(value);
//
//                if (log.isDebugEnabled()) {
//                    log.debug("get cache value for key @" + key + ":" + deserializedData);
//                }
//
//                return value == null ? null : deserializedData;
//            }
//        });
    }

    public void delete(Object key) {
        redisTemplate.delete(key);
    }

//    public void flushAll() {
//        redisTemplate.execute(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                connection.flushAll();
//
//                return null;
//            }
//        });
//    }
//
//    private byte[] rawKey(String key) {
//        return redisTemplate.getStringSerializer().serialize(key);
//    }

}
