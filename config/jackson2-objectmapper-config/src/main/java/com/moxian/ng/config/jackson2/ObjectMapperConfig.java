/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.config.jackson2;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.moxian.ng.config.jackson2.ser.*;
import com.moxian.ng.config.jackson2.ser.CustomLocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.moxian.ng.config.jackson2.ser.CustomLocalDateSerializer;
import com.moxian.ng.config.jackson2.ser.CustomLocalDateTimeDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 *
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Configuration
public class ObjectMapperConfig {

    @Bean
    public ObjectMapper objectMapper() {

        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.deserializerByType(LocalDate.class, new CustomLocalDateDeserializer());
        builder.deserializerByType(LocalDateTime.class, new CustomLocalDateTimeDeserializer());
        builder.serializerByType(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        builder.serializerByType(LocalDate.class, new CustomLocalDateSerializer());
        builder.serializationInclusion(Include.NON_EMPTY);
        builder.featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        builder.featuresToEnable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        return builder.build();
    }

}
