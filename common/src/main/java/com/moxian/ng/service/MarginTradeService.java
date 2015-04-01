package com.moxian.ng.service;

import javax.inject.Inject;

import com.moxian.ng.domain.MarginTrade;
import com.moxian.ng.repository.MarginTradeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author wangli@flf77.com
 * @date 2015年2月13日 下午2:18:31
 */
@Service
public class MarginTradeService {

	public final static Logger log = LoggerFactory
			.getLogger(MarginTradeService.class);

	@Inject
	private MarginTradeRepository marginTradeRepository;

	/**
	 * 通过投标ID获取保证金交易信息
	 * 
	 * @param bidOrdId
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午2:07:49
	 */
	public MarginTrade getMarginTradeByBidOrdId(String bidOrdId) {
		return marginTradeRepository.findByBidOrdId(bidOrdId);
	}
}
