package com.moxian.ng.service;

import java.io.IOException;
import java.io.InputStream;

import com.moxian.ng.model.Constants;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.EntityEnclosingMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.fenglianfinance.account.model.ResultProcessor;
import com.fenglianfinance.account.model.ServiceResult;
import com.moxian.ng.util.LogUtils;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class HttpClientService {

	public final static Logger log = LoggerFactory
			.getLogger(HttpClientService.class);

	static final String RESULT_BYTES = "RESULT_BYTES";
	static final String RESULT_STRING = "RESULT_STRING";

	/* 服务URL */
	private String serviceUrl;

	/* content-type */
	private String contentType = "application/x-www-form-urlencoded;charset=utf-8";

	/* 结果处理器 */
	private ResultProcessor processor;

	/* http连接超时 */
	private int connectionTimeout = 1000 * 60 * 60;

	/* socket连接的超时时间 */
	private int socketTimeout = 1000 * 60 * 60;

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setProcessor(ResultProcessor processor) {
		this.processor = processor;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public void setSocketTimeout(int socketTimeout) {
		this.socketTimeout = socketTimeout;
	}

	public ServiceResult get(NameValuePair[] pair) {
		ServiceResult result = new ServiceResult();
		if (StringUtils.isNotBlank(serviceUrl)) {
			LogUtils.info(log, "调用HttpClinet服务URL, url={0}", serviceUrl);

			HttpClient httpClient = new HttpClient();
			HttpConnectionManager manager = httpClient
					.getHttpConnectionManager();
			manager.getParams().setConnectionTimeout(connectionTimeout); // 建立可用连接的超时时间
			manager.getParams().setSoTimeout(socketTimeout); // socket连接的超时时间
			// 实例化一个POST对象
			GetMethod getMethod = new GetMethod(serviceUrl);
			getMethod.addRequestHeader("Content-type", contentType);
			getMethod.setRequestHeader("Connection", "close");

			try {
				getMethod.setQueryString(pair);
				httpClient.executeMethod(getMethod);
				if (getMethod.getStatusCode() == HttpStatus.SC_OK) {
					result.setSuccess(true);
					InputStream in = getMethod.getResponseBodyAsStream();
					// 处理结果集
					if (processor != null) {
						processor.process(in);
					} else {
						// 将结果放入返回对象中
						result.addAttribute(RESULT_BYTES, toBytes(in));
					}
					LogUtils.info(log, "调用HTTPClient服务完成, url={0}", serviceUrl);
				}
			} catch (HttpException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} catch (IOException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} finally {
				getMethod.releaseConnection();
			}
		}
		return result;
	}

	public ServiceResult post(NameValuePair[] pair) {
		ServiceResult result = new ServiceResult();
		if (StringUtils.isNotBlank(serviceUrl)) {
			LogUtils.info(log, "调用HttpClinet服务URL, url={0}", serviceUrl);

			HttpClient httpClient = new HttpClient();
			HttpConnectionManager manager = httpClient
					.getHttpConnectionManager();
			manager.getParams().setConnectionTimeout(connectionTimeout); // 建立可用连接的超时时间
			manager.getParams().setSoTimeout(socketTimeout); // socket连接的超时时间
			// 实例化一个POST对象
			PostMethod postMethod = new PostMethod(serviceUrl);
			postMethod.addRequestHeader("Content-type", contentType);
			postMethod.setRequestHeader("Connection", "close");

			try {
				postMethod.setQueryString(pair);
				httpClient.executeMethod(postMethod);
				if (postMethod.getStatusCode() == HttpStatus.SC_OK
						|| postMethod.getStatusCode() == HttpStatus.SC_CREATED
						|| postMethod.getStatusCode() == HttpStatus.SC_ACCEPTED) {
					result.setSuccess(true);
					InputStream in = postMethod.getResponseBodyAsStream();
					// 处理结果集
					if (processor != null) {
						processor.process(in);
					} else {
						// 将结果放入返回对象中
						result.addAttribute(RESULT_BYTES, toBytes(in));
					}
					LogUtils.info(log, "调用HTTPClient服务完成, url={0}", serviceUrl);
				}
			} catch (HttpException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} catch (IOException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} finally {
				postMethod.releaseConnection();
			}
		}
		return result;
	}

	public ServiceResult post(NameValuePair[] pair, Part[] parts) {
		ServiceResult result = new ServiceResult();
		if (StringUtils.isNotBlank(serviceUrl)) {
			LogUtils.info(log, "调用HttpClinet服务URL, url={0}", serviceUrl);

			HttpClient httpClient = new HttpClient();
			HttpConnectionManager manager = httpClient
					.getHttpConnectionManager();
			manager.getParams().setConnectionTimeout(connectionTimeout); // 建立可用连接的超时时间
			manager.getParams().setSoTimeout(socketTimeout); // socket连接的超时时间
			// 实例化一个POST对象
			PostMethod postMethod = new PostMethod(serviceUrl);
			// postMethod.addRequestHeader("Content-type", contentType);
			postMethod.setRequestHeader("Connection", "close");

			try {
				if (pair != null && pair.length > 0) {
					postMethod.setQueryString(pair);
				}
				postMethod.setRequestEntity(new MultipartRequestEntity(parts,
						postMethod.getParams()));
				httpClient.executeMethod(postMethod);
				if (postMethod.getStatusCode() == HttpStatus.SC_OK
						|| postMethod.getStatusCode() == HttpStatus.SC_CREATED
						|| postMethod.getStatusCode() == HttpStatus.SC_ACCEPTED) {
					result.setSuccess(true);
					InputStream in = postMethod.getResponseBodyAsStream();
					// 处理结果集
					if (processor != null) {
						processor.process(in);
					} else {
						// 将结果放入返回对象中
						result.addAttribute(RESULT_BYTES, toBytes(in));
					}
					LogUtils.info(log, "调用HTTPClient服务完成, url={0}", serviceUrl);
				}
			} catch (HttpException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} catch (IOException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} finally {
				postMethod.releaseConnection();
			}
		}
		return result;
	}

	public ServiceResult post(RequestEntity entity, NameValuePair[] pair) {
		ServiceResult result = new ServiceResult();
		if (StringUtils.isNotBlank(serviceUrl)) {
			LogUtils.info(log, "调用HttpClinet服务URL, url={0}", serviceUrl);

			HttpClient httpClient = new HttpClient();
			HttpConnectionManager manager = httpClient
					.getHttpConnectionManager();
			manager.getParams().setConnectionTimeout(connectionTimeout); // 建立可用连接的超时时间
			manager.getParams().setSoTimeout(socketTimeout); // socket连接的超时时间
			// 实例化一个POST对象
			PostMethod postMethod = new PostMethod(serviceUrl);
			postMethod.addRequestHeader("Content-type", contentType);
			postMethod.setRequestHeader("Connection", "close");

			try {
				if (pair != null && pair.length > 0) {
					postMethod.setQueryString(pair);
				}
				postMethod.setRequestEntity(entity);
				httpClient.executeMethod(postMethod);
				if (postMethod.getStatusCode() == HttpStatus.SC_OK
						|| postMethod.getStatusCode() == HttpStatus.SC_CREATED
						|| postMethod.getStatusCode() == HttpStatus.SC_ACCEPTED) {
					result.setSuccess(true);
					InputStream in = postMethod.getResponseBodyAsStream();
					// 处理结果集
					if (processor != null) {
						processor.process(in);
					} else {
						// 将结果放入返回对象中
						result.addAttribute(RESULT_BYTES, toBytes(in));
					}
					LogUtils.info(log, "调用HTTPClient服务完成, url={0}", serviceUrl);
				}
			} catch (HttpException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} catch (IOException ex) {
				LogUtils.error(log, "调用HttpClient时发生异常", ex);
			} finally {
				postMethod.releaseConnection();
			}
		}
		return result;
	}

	/**
	 * http post json字符串
	 * 
	 * @param json
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月9日 下午4:51:56
	 */
	public ServiceResult postJson(String json) {
		LogUtils.info(log, "调用HTTPClient服务, json={0}", json);
		ServiceResult result = new ServiceResult();

		HttpClient httpClient = new HttpClient();
		HttpConnectionManager manager = httpClient.getHttpConnectionManager();
		manager.getParams().setConnectionTimeout(connectionTimeout); // 建立可用连接的超时时间
		manager.getParams().setSoTimeout(socketTimeout); // socket连接的超时时间

		byte b[] = json.getBytes();// 把字符串转换为二进制数据
		RequestEntity requestEntity = new ByteArrayRequestEntity(b);

		EntityEnclosingMethod postMethod = new PostMethod(serviceUrl);
		postMethod.setRequestEntity(requestEntity);// 设置数据
		postMethod.setRequestHeader("Content-Type", Constants.APPLICATION_JSON);// 设置请求头编码

		try {
			httpClient.executeMethod(postMethod);// 发送请求
			if (postMethod.getStatusCode() == HttpStatus.SC_OK) {
				result.setSuccess(true);
				InputStream in = postMethod.getResponseBodyAsStream();// 获取返回值
				LogUtils.info(log, "调用HTTPClient服务同步返回, json={0}", toString(in));
				// 将结果放入返回对象中
				result.addAttribute(RESULT_STRING, toString(in));
				LogUtils.info(log, "调用HTTPClient服务完成, url={0}", serviceUrl);
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			postMethod.releaseConnection();// 释放连接
		}
		return result;
	}

	/**
	 * 将响应转换成字符串
	 * 
	 * @param in
	 * @return
	 */
	protected String toString(InputStream in) {
		try {
			String str = IOUtils.toString(in, Constants.CHARSET_UTF_8);
			return str;
		} catch (IOException ex) {
			LogUtils.error(log, "转换响应时发生异常", ex);
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 将输入流转换成字节数组
	 * 
	 * @param in
	 * @return
	 */
	protected byte[] toBytes(InputStream in) throws IOException {
		return IOUtils.toByteArray(in);
	}

}
