package com.fenglianfinance.bill;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author ronghui.ou
 * @date 2015年3月5日  13:57:55
 *
 */
public class FuzzyInfoUtils {

	/**
	 * 模糊用户名,模糊规则：保留第一个字符，其他替换为'*'
	 * 
	 * @param value
	 *            <pre>
	 * 	FuzzyInfoUtils.fuzzyUserName("张三丰") = 张**
	 * </pre>
	 * @return
	 */
	public static String fuzzyUserName(String value) {
		int valueLen = value != null ? value.length() : 0;

		if (valueLen > 1) {
			value = value.substring(0, 1)
					+ StringUtils.repeat("*", valueLen - 1);
		}
		return value;
	}

	/**
	 * 模糊手机号码,模糊规则：保留前后三个字符，其他替换为'*'
	 * 
	 * @param value
	 *            <pre>
	 * 	FuzzyInfoUtils.fuzzyMobilePhone("13472860655") = 134*****655
	 * </pre>
	 * @return
	 */
	public static String fuzzyMobilePhone(String value) {
		int valueLen = value != null ? value.length() : 0;

		if (valueLen > 6) {
			value = value.substring(0, 3)
					+ StringUtils.repeat("*", valueLen - 1)
					+ value.substring(valueLen - 4);
		}
		return value;
	}

	/**
	 * 模糊银行卡号，模糊规则：保留前后四位卡号数字，中间替换为'*'
	 * 
	 * <pre>
	 * 	FuzzyInfoUtils.fuzzyBankCard("6227021548665859655") = 6227***********9655
	 * </pre>
	 * 
	 * @param cardNo
	 *            卡号
	 * @return
	 */
	public static String fuzzyBankCard(String cardNo) {
		int cardNoLen = cardNo != null ? cardNo.length() : 0;

		if (cardNoLen > 8) {
			cardNo = cardNo.substring(0, 4)
					+ StringUtils.repeat("*", cardNoLen - 8)
					+ cardNo.substring(cardNoLen - 4);
		}
		return cardNo;
	}
}
