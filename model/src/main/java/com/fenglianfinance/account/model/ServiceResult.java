/**
 * Storevm.org Inc.
 * Copyright (c) 2004-2015 All Rights Reserved.
 */
package com.moxian.ng.account.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Administrator
 * @version $Id: ServiceResult.java, v 0.1 2015年1月16日 下午10:28:03 Administrator
 *          Exp $
 */
public class ServiceResult implements Serializable {
	/**
	 * UID
	 */
	private static final long serialVersionUID = 501920048049720769L;

	/* 执行是否成功 */
	private boolean success;

	/* 代码 */
	private String code;

	/* 描述 */
	private String message;

	/* 返回的值映射 */
	private Map<String, Object> attributes = new HashMap<String, Object>();

	/**
	 * 构造函数
	 */
	public ServiceResult() {
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes.putAll(attributes);
	}

	/**
	 * 返回属性值
	 * 
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getAttribute(String key) {
		return (T) attributes.get(key);
	}

	public void addAttribute(String key, Object value) {
		attributes.put(key, value);
	}

	/**
	 * Getter method for property <tt>success</tt>.
	 * 
	 * @return property value of success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * Setter method for property <tt>success</tt>.
	 * 
	 * @param success
	 *            value to be assigned to property success
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * Getter method for property <tt>code</tt>.
	 * 
	 * @return property value of code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter method for property <tt>code</tt>.
	 * 
	 * @param code
	 *            value to be assigned to property code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Getter method for property <tt>message</tt>.
	 * 
	 * @return property value of message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Setter method for property <tt>message</tt>.
	 * 
	 * @param message
	 *            value to be assigned to property message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter method for property <tt>attributes</tt>.
	 * 
	 * @return property value of attributes
	 */
	public Map<String, Object> getAttributes() {
		return attributes;
	}
}
