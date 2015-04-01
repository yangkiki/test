package com.moxian.ng.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.moxian.ng.account.model.RegisterAccountReq;

public class FLStringUtils {

	/**
	 * 将对象转换成的json字符串转换成传递需要的样式
	 * 
	 * @param str
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月17日 上午11:34:09
	 */
	public static String convertString(String str) {
		Pattern p = Pattern.compile("\"(\\w+)\":");
		Matcher m = p.matcher(str);
		while (m.find()) {
			String subStr = m.group(1);
			str = str.replace(subStr, change(subStr));
		}
		return str;
	}

	/**
	 * 将大写字母替换成"_小写字母"
	 * 
	 * @param str
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月17日 上午11:33:38
	 */
	public static String change(String str) {
		char[] buffer = str.toCharArray();
		for (int i = 0; i < buffer.length; i++) {
			if (Character.isUpperCase(buffer[i])) {
				String a = str.substring(i, i + 1);
				str = str.replace(a, "_" + a.toLowerCase());
				buffer = str.toCharArray();
				i++;
			}
		}
		return str;
	}

	/**
	 * 将Java类的私有成员变量按照顺序，由"&"符号依次连接键值对
	 * 
	 * @param obj
	 * @return
	 * @throws Exception
	 * @author wangli@flf77.com
	 * @date 2015年3月17日 下午3:19:53
	 */
	public static String toLinkString(Object obj) throws Exception {
		StringBuilder sb = new StringBuilder();
		Class<?> classType = obj.getClass();
		Field fields[] = classType.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			String fieldName = f.getName();
			if (null != fieldName && !"".equals(fieldName)) {
				if (!Character.isUpperCase(fieldName.charAt(0))) {
					String firstLetter = fieldName.substring(0, 1)
							.toUpperCase();
					String getMethodName = "get" + firstLetter
							+ fieldName.substring(1);
					Method getMethod = classType.getMethod(getMethodName,
							new Class[] {});
					Object valueObj = getMethod.invoke(obj, new Object[] {});
					String value = (valueObj == null ? "" : valueObj.toString());
					sb.append("&").append(change(fieldName)).append("=")
							.append(value);
				}
			}
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String str = "{\"agreementInfoReq\":{\"channelNo\":\"880001\",\"service\":\"register_account\",\"sign\":\"f26oNboPAWF2GYlBiZnaVaiwG9A32iFW5c5K5pN8/hWXR4tnwLzyHvJkjBgElmdZvUgkxvlaHl28hSuVbjsRr2RsPzulHArpWFXnIvYFvruitDy+WGb+IJWTjE7NeLCb4M5Aw2M0oO9aTK7LxtzRVM+SoLcMCHgmM78+NQ7Jyp4=\",\"signType\":\"RSA\",\"transDate\":\"2015-03-17\",\"transTime\":\"09:37:43\",\"version\":\"1.0\"},\"registerAccountReq\":{\"bankName\":\"11222\",\"borrowAmt\":\"300000\",\"borrowTimeLimit\":\"2\",\"channelSysOrdersNo\":\"00150210000001\",\"clearLine\":\"324000\",\"commission\":\"\",\"email\":\"13472443051@189.com\",\"fundAmt\":\"360000\",\"fundEndDate\":\"2015-02-12\",\"fundStartDate\":\"2015-02-10\",\"guardLineOne\":\"339000\",\"guardLineTwo\":\"330000\"}}";
		System.out.println(convertString(str));
		System.out.println(change("\"jiaUaBppHbbMm\":"));

		try {
			RegisterAccountReq r = new RegisterAccountReq();
			r.setRetUrl("jiuhjds");
			r.setUserId("111");
			System.out.println(toLinkString(r));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
