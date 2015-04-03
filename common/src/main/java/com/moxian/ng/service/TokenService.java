/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import com.moxian.ng.cache.service.CacheService;
import java.util.UUID;
import javax.inject.Inject;

import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Service
public class TokenService {

    private static final Logger log = LoggerFactory.getLogger(TokenService.class);

    private static final String PREFIX = "TOKEN_SERVICE:";

    private static final int EXPIRES_IN_SECONDS = 60;

    @Inject
    private CacheService cacheService;

    public String generateConversationId(String salt) {
        Assert.notNull(salt, "conversation generation key can not not null");

        if (log.isDebugEnabled()) {
            log.debug("salt key@" + salt);
        }

        String uuid = UUID.randomUUID().toString();

        String value = DigestUtils.md5DigestAsHex((uuid + ":" + salt).getBytes());

        cacheService.set(PREFIX + uuid, value, EXPIRES_IN_SECONDS);

        return uuid;
    }

    public boolean exists(String cid) {
        if (log.isDebugEnabled()) {
            log.debug("check if conversation exists@" + cid);
        }

        String existing = (String) cacheService.get(PREFIX + cid);

        if (log.isDebugEnabled()) {
            log.debug("fetching generated code from cache @" + existing);
        }

        return (existing != null);

    }

    public boolean validate(String cid, String salt) {
        if (log.isDebugEnabled()) {
            log.debug("check if conversation exists@" + cid);
        }

        String existing = (String) cacheService.get(PREFIX + cid);

        if (log.isDebugEnabled()) {
            log.debug("fetching generated code from cache @" + existing);
        }

        return existing.equals(DigestUtils.md5DigestAsHex((cid + ":" + salt).getBytes()));

    }

    public void delete(String cid) {
        if (log.isDebugEnabled()) {
            log.debug("del conversation@" + cid);
        }

        cacheService.delete(PREFIX + cid);
    }

}
