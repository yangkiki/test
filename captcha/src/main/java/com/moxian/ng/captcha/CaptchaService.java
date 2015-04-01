/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.captcha;

import com.moxian.ng.cache.service.CacheService;
import com.moxian.ng.model.ImgCaptchaRequest;
import com.moxian.ng.model.ImgCaptchaResult;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 *
 * @author hansy
 */
@Service
public class CaptchaService {

    private static final Logger log = LoggerFactory.getLogger(CaptchaService.class);

    @Inject
    private CacheService cacheService;

    private KaptchaDelegate kaptchaImg;

    public CaptchaService() {
    }

    @PostConstruct
    public void onPostConstruct() {
        if (log.isDebugEnabled()) {
            log.debug("call  @PostConstruct in CaptchaService... ");
        }
        this.kaptchaImg = new KaptchaDelegate(cacheService);
    }

    public ImgCaptchaResult generateCaptcha() {
        return kaptchaImg.generateCaptcha();
    }

    public boolean verifyImgCaptcha(ImgCaptchaRequest res) {
        Assert.notNull(res, "captcha request can not be null");
        return res.getResponseValue().equals(kaptchaImg.getGeneratedCaptcha(res.getKey()));
    }

}
