package com.fenglianfinance.bill.api.withfunding;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.account.model.AgreementInfoResp;
import com.fenglianfinance.account.model.RegisterAccountResp;
import com.fenglianfinance.account.model.SignatureInfo;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BooleanValue;
import com.fenglianfinance.bill.service.WithFundingService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_ACCT
		+ ApiConstants.URI_WITH_FUNDING)
public class AccountPublicController {
	private static final Logger log = LoggerFactory
			.getLogger(AccountController.class);

	@Inject
	private SignatureInfo signatureInfo;

	@Inject
	private WithFundingService withFundingService;

	/**
	 * 异步获取丰联配资账户系统的开户响应并进行处理
	 * 
	 * @param agreementInfoResp
	 * @param registerAccountResp
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月6日 下午4:58:49
	 */
	@RequestMapping(value = "/register_account", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<BooleanValue> registerAccountResp(
			@RequestParam("agreement_info_resp") AgreementInfoResp agreementInfoResp,
			@RequestParam("register_account_resp") RegisterAccountResp registerAccountResp) {
		if (log.isDebugEnabled()) {
			log.debug("registerAccountResp : @agreementInfoResp@"
					+ agreementInfoResp + " @registerAccountResp@"
					+ registerAccountResp);
		}
		try {
			withFundingService.registerAccountResp(agreementInfoResp,
					registerAccountResp, signatureInfo);
			return new ResponseEntity<>(new BooleanValue(true),
					HttpStatus.CREATED);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("获取丰联配资账户系统的开户响应，处理错误  @" + e.toString());
			}
			return new ResponseEntity<>(new BooleanValue(false),
					HttpStatus.NO_CONTENT);
		}

	}
}
