package com.fenglianfinance.bill.api.withfunding;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.WithFundingInfoView;
import com.fenglianfinance.bill.service.WithFundingService;

/**
 * console下配资模块
 * 
 * @author wangli@flf77.com
 * @date 2015年1月29日 下午7:39:55
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT
		+ ApiConstants.URI_WITH_FUNDING)
public class WithFundingMgtController {

	private static final Logger log = LoggerFactory
			.getLogger(WithFundingMgtController.class);

	@Inject
	private WithFundingService withFundingService;

	@RequestMapping(value = { "" }, method = RequestMethod.GET)
	@ResponseBody
	public Page<WithFundingInfoView> allWithFundingInfos(
			@RequestParam("mode") String mode,
			@RequestParam("gearing") Long gearing,
			@RequestParam("status") String status,
			@PageableDefault(value = 10) Pageable page) {
		if (log.isDebugEnabled()) {
			log.debug("fetch all WithFundingInfos...@ mode:" + mode
					+ ", gearing:" + gearing + ", status:" + status + ", page:"
					+ page);
		}

		Page<WithFundingInfoView> withFundingInfos = withFundingService
				.findWithFundingInfoViews(mode, gearing, status, page);

		if (log.isDebugEnabled()) {
			log.debug("count of users @" + withFundingInfos.getTotalElements());
			log.debug(" users @" + withFundingInfos.getContent());
		}

		return withFundingInfos;
	}

}
