/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author hantsy<hantsy@gmail.com><hantsy<hantsy@gmail.com>@gmail.com>
 */
public class SerialNumberUtils {

    private static final Logger log = LoggerFactory.getLogger(SerialNumberUtils.class);

    public static String next() {
         String ordId =RandomStringUtils.random(20, false, true);
        if (log.isDebugEnabled()) {
            log.debug("generated serial number @" + ordId);
        }

        return ordId;
    }
}
