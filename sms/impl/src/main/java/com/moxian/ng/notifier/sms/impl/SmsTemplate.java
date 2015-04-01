package com.moxian.ng.notifier.sms.impl;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.moxian.ng.notifier.sms.api.SmsConfiguration;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.moxian.ng.notifier.sms.api.SmsOperations;
import com.moxian.ng.notifier.sms.core.SmsMessage;

public class SmsTemplate implements SmsOperations {

    private static final Logger log = LoggerFactory
            .getLogger(SmsTemplate.class);

    private static final String DEST_MOBILE = "phone";

    private static final String MSG_TEXT = "message";

    private final SmsConfiguration config;

    private final RestTemplate restTemplate;

    public SmsTemplate(SmsConfiguration config) {
        this.config = config;

        CloseableHttpClient httpClient = HttpClients.createMinimal();
        ClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        this.restTemplate = new RestTemplate(clientHttpRequestFactory);

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new FormHttpMessageConverter());
        messageConverters.add(new StringHttpMessageConverter(Charset
                .forName("UTF-8")));

        this.restTemplate.setMessageConverters(messageConverters);
    }

    @Override
    public String send(String mobile, String message) {
        if (log.isDebugEnabled()) {
            log.debug("send message to mobile@" + mobile + ", massage@"
                    + message);
        }

        String url = this.config.getApiUrl();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

        // SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
        params.add("cdkey", this.config.getAccount());
        params.add("password", this.config.getPassword());
        params.add(DEST_MOBILE, mobile);
        params.add(MSG_TEXT, message);
        // params.add("sendDateTime", sdf.format(new Date()));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        headers.setAcceptCharset(Arrays.asList(Charset.forName("UTF-8")));
        ResponseEntity<String> result = null;
        try {
            result = restTemplate.exchange(url, HttpMethod.POST,
                    new HttpEntity<>(params, headers), String.class);
        } catch (RestClientException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (result != null) {
            if (log.isDebugEnabled()) {
                log.debug("return from sms@" + result.getBody());
            }
            return result.toString();
        } else {
            if (log.isDebugEnabled()) {
                log.debug("return from sms@null");
            }
            return "";
        }
    }

    @Override
    public String send(SmsMessage msg) {
        return send(msg.getMobileNumber(), msg.getContent());
    }

}
