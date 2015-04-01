/**
 * Storevm.org Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.fenglianfinance.account.model;

import java.io.InputStream;

/**
 * 
 * @author Administrator
 * @version $Id: ResultProcessor.java, v 0.1 2015年1月16日 下午10:32:32 Administrator
 *          Exp $
 */
public interface ResultProcessor {
	/**
	 * 处理器
	 * 
	 * @param input
	 */
	void process(InputStream input);
}
