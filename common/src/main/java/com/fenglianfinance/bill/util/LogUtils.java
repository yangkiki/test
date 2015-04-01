package com.fenglianfinance.bill.util;

import java.text.MessageFormat;

import org.slf4j.Logger;

/**
 * 日志工具
 * 
 * @author Administrator
 * @version $Id: LogUtils.java, v 0.1 2013-1-30 上午11:05:21 Administrator Exp $
 */
public class LogUtils {
	/**
	 * info日志
	 * 
	 * @param logger
	 * @param message
	 * @param args
	 */
	public static void info(Logger logger, String message, Object... args) {
		if (logger != null) {
			if (logger.isInfoEnabled()) {
				logger.info(format(message, args));
			}
		}
	}

	/**
	 * debug日志
	 * 
	 * @param logger
	 * @param message
	 * @param args
	 */
	public static void debug(Logger logger, String message, Object... args) {
		if (logger != null) {
			if (logger.isDebugEnabled()) {
				logger.debug(format(message, args));
			}
		}
	}

	/**
	 * error日志
	 * 
	 * @param logger
	 * @param message
	 * @param args
	 */
	public static void error(Logger logger, String message, Object... args) {
		if (logger != null) {
			if (logger.isErrorEnabled()) {
				logger.error(format(message, args));
			}
		}
	}

	/**
	 * error日志
	 * 
	 * @param logger
	 * @param message
	 * @param ex
	 * @param args
	 */
	public static void error(Logger logger, String message, Throwable ex,
			Object... args) {
		if (logger != null) {
			logger.error(format(message, args), ex);
		}
	}

	/**
	 * warn日志
	 * 
	 * @param logger
	 * @param message
	 * @param args
	 */
	public static void warn(Logger logger, String message, Object... args) {
		if (logger != null) {
			logger.warn(format(message, args));
		}
	}

	/**
	 * 格式化消息
	 * 
	 * @param message
	 * @param args
	 * @return
	 */
	protected static String format(String message, Object... args) {
		if (args != null && args.length > 0) {
			return new MessageFormat(message).format(args);
		}
		return message;
	}
}
