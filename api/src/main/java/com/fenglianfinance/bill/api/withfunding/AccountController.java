package com.fenglianfinance.bill.api.withfunding;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.account.model.QueryAccountResp;
import com.fenglianfinance.account.model.ServiceResult;
import com.fenglianfinance.account.model.SignatureInfo;
import com.fenglianfinance.bill.api.security.CurrentUser;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BooleanValue;
import com.fenglianfinance.bill.service.WithFundingService;

@RestController
@RequestMapping(value = ApiConstants.URI_API + ApiConstants.URI_ACCT
		+ ApiConstants.URI_WITH_FUNDING)
public class AccountController {
	private static final Logger log = LoggerFactory
			.getLogger(AccountController.class);

	@Inject
	private SignatureInfo signatureInfo;

	@Inject
	private WithFundingService withFundingService;

	/**
	 * 向丰联配资账户系统发送开户消息
	 * 
	 * @param user
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月6日 下午3:26:39
	 */
	@RequestMapping(value = "/sendJSON4RegisterAccount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<BooleanValue> sendJSON4RegisterAccount(
			@CurrentUser UserAccount user,
			@RequestParam("serialNumber") String serialNumber) {
		// 操作人
		Assert.notNull(user, "UserAccount can't be null ");

		if (log.isDebugEnabled()) {
			log.debug("sendJSON4RegisterAccount 4 the person who applied a withfunding which serialNumber is @"
					+ serialNumber);
		}
		ServiceResult serviceResult;
		try {
			serviceResult = withFundingService.sendRegisterAccountReqBody(
					signatureInfo, serialNumber);
			if (null != serviceResult && serviceResult.isSuccess()) {
				return new ResponseEntity<>(new BooleanValue(true),
						HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(new BooleanValue(false),
						HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("向丰联配资账户系统发送开户指令失败  @" + e.toString());
			}
			return new ResponseEntity<>(new BooleanValue(false),
					HttpStatus.NO_CONTENT);
		}

	}

	/**
	 * 向丰联配资账户系统发送开户查询请求
	 * 
	 * @param user
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月6日 下午3:26:39
	 */
	@RequestMapping(value = "/queryAccount", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<QueryAccountResp> queryAccount(
			@CurrentUser UserAccount user,
			@RequestParam("serialNumber") String serialNumber) {
		// 操作人
		Assert.notNull(user, "UserAccount can't be null ");

		if (log.isDebugEnabled()) {
			log.debug("queryAccount 4 the person who applied a withfunding which serialNumber is @"
					+ serialNumber);
		}
		QueryAccountResp qar = new QueryAccountResp();
		try {
			qar = withFundingService.sendQueryAccountReqBodyAndGetResult(
					signatureInfo, serialNumber);
			return new ResponseEntity<>(qar, HttpStatus.OK);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("查询开户信息失败  @" + e.toString());
			}
			return new ResponseEntity<>(qar, HttpStatus.NO_CONTENT);
		}
	}
}
