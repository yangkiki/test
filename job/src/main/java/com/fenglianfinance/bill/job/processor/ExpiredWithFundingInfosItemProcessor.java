package com.fenglianfinance.bill.job.processor;

import java.time.LocalDate;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.util.Assert;

import com.fenglianfinance.bill.domain.WithFundingInfos;

public class ExpiredWithFundingInfosItemProcessor implements
		ItemProcessor<WithFundingInfos, WithFundingInfos> {

	@Override
	public WithFundingInfos process(WithFundingInfos withFundingInfos)
			throws Exception {
		// 配资申请审核时间+申请的期限时间 > now
		Assert.notNull(withFundingInfos.getConfirmedDate(),
				"withFundingInfos' id @" + withFundingInfos.getId()
						+ " confirmedDate can not be null! ");
		LocalDate confirmedDate = withFundingInfos.getConfirmedDate()
				.toLocalDate();
		LocalDate now = LocalDate.now();
		Integer terms = withFundingInfos.getTerms();

		if (withFundingInfos.getMode().equals(WithFundingInfos.Mode.DAY)) {
			if (confirmedDate.plusDays(terms).isBefore(now)) {
				withFundingInfos.setStatus(WithFundingInfos.Status.EXPIRED);
			}
		} else if (withFundingInfos.getMode().equals(
				WithFundingInfos.Mode.MONTH)) {
			if (confirmedDate.plusMonths(terms).isBefore(now)) {
				withFundingInfos.setStatus(WithFundingInfos.Status.EXPIRED);
			}
		}

		return withFundingInfos;
	}
}
