/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.config.jackson2.ser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hansy
 */
public class CustomLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

    private static final Logger log = LoggerFactory.getLogger(CustomLocalDateTimeDeserializer.class);

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if(log.isDebugEnabled()){
            log.debug("deserializing...@ "+jp.getCurrentName()+", text@"+jp.getValueAsString());
        }
        
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String str = jp.getText().trim();
            return LocalDateTime.from(dtf.parse(str));
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            Instant ins = Instant.ofEpochMilli(jp.getLongValue());
            return LocalDateTime.ofInstant(ins, ZoneId.systemDefault());
        }
        throw ctxt.mappingException(handledType());
    }

}
