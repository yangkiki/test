package com.moxian.ng.config.signature;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import com.moxian.ng.account.model.SignatureInfo;

@Configuration
@PropertySource("classpath:/signature.properties")
public class SignatureConfig {

	private static final Logger log = LoggerFactory
			.getLogger(SignatureConfig.class);

	@Inject
	private Environment env;

	@Bean
	public SignatureInfo initSignatureInfo() throws Exception {
		if (log.isInfoEnabled()) {
			log.info("init SignatureInfo  @@" + " --algorthm:"
					+ env.getProperty("signature.algorthm") + " --pubKey:"
					+ env.getProperty("signature.pubKey") + " --priKey:"
					+ env.getProperty("signature.priKey") + " --channelNo:"
					+ env.getProperty("signature.channelNo") + " --reqUrl:"
					+ env.getProperty("signature.reqUrl") + " --prefixRetUrl:"
					+ env.getProperty("signature.prefixRetUrl"));
		}

		SignatureInfo signatureInfo = new SignatureInfo();
		if (StringUtils.isEmpty(env.getProperty("signature.algorthm"))
				|| StringUtils.isEmpty(env.getProperty("signature.pubKey"))
				|| StringUtils.isEmpty(env.getProperty("signature.priKey"))
				|| StringUtils.isEmpty(env.getProperty("signature.channelNo"))
				|| StringUtils.isEmpty(env.getProperty("signature.reqUrl"))
				|| StringUtils.isEmpty(env
						.getProperty("signature.prefixRetUrl"))) {
			if (log.isErrorEnabled()) {
				log.error("signature.algorthm or signature.pubKey or signature.priKey or signature.channelNo or signature.reqUrl or signature.prefixRetUrl does not exit!");
			}
			return null;
		}

		signatureInfo.setAlgorthm(env.getProperty("signature.algorthm"));
		signatureInfo.setPubKey(env.getProperty("signature.pubKey"));
		signatureInfo.setPriKey(env.getProperty("signature.priKey"));
		signatureInfo.setChannelNo(env.getProperty("signature.channelNo"));
		signatureInfo.setReqUrl(env.getProperty("signature.reqUrl"));
		signatureInfo
				.setPrefixRetUrl(env.getProperty("signature.prefixRetUrl"));
		return signatureInfo;
	}

}
