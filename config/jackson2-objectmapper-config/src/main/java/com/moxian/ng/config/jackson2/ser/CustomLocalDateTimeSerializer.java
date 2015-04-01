/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.config.jackson2.ser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
public class CustomLocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    private static final Logger log = LoggerFactory.getLogger(CustomLocalDateTimeSerializer.class);

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void serialize(LocalDateTime value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {
        if (log.isDebugEnabled()) {
            log.debug("serializing...@ " + value.toString());
        }
        jgen.writeString(value.format(dtf));
    }

}
