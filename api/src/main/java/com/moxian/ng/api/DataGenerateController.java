/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.api;

import com.moxian.ng.model.ApiConstants;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API + "/public/generate")
public class DataGenerateController {

  private static final Logger log = LoggerFactory.getLogger(DataGenerateController.class);

  @RequestMapping(value = {""}, params = "action=GENERATE_INDICATOR", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<Map<String, String>> generateTransactionIndicator() {
    if (log.isDebugEnabled()) {
      log.debug("generating transaction log indicator...");
    }

    // String ordId = Hex.encodeHexString(KeyGenerators.secureRandom(10).generateKey());
//        String ordId = String.valueOf(System.nanoTime());
    String ordId = RandomStringUtils.random(20, false, true);

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
    String ordDate = dtf.format(LocalDateTime.now());

    Map<String, String> map = new HashMap<>();
    map.put("ordId", ordId);
    map.put("ordDate", ordDate);

    if (log.isDebugEnabled()) {
      log.debug("generated map @" + map);
    }

    return new ResponseEntity<>(map, HttpStatus.OK);
  }

}
