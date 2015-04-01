package com.fenglianfinance.bill.api.withfunding;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.WithFundingConfs;
import com.fenglianfinance.bill.service.WithFundingService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC
		+ ApiConstants.URI_WITH_FUNDING)
public class WithFundingPublicController {

	private static final Logger log = LoggerFactory
			.getLogger(WithFundingPublicController.class);

	@Inject
	private WithFundingService withFundingService;

	/**
	 * 获取配资配置信息
	 * 
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月30日 下午2:37:48
	 */
	@RequestMapping(value = "/getConfs", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<WithFundingConfs> getWithfundingConfs() {
		// TODO 暂缺配资企业过滤
		WithFundingConfs withFundingConfs = withFundingService
				.getWithFundingConfs();
		return new ResponseEntity<>(withFundingConfs, HttpStatus.OK);
	}
}
