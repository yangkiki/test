package com.moxian.ng.model;

/**
 * 用户模块错误码定义
 * 
 * @ClassName: ErrorCode
 * @Description: 顶级CODE：1+1+17（String）
 * @author liu.xiaoyi liu.xiaoyi@moxiangroup.com
 * @Company moxian
 * @date 2015年2月5日 下午6:08:09
 *
 */
public class ErrorCode {

	/**
	 * 成功
	 */
	public final static String SUCCESS = "mx1117000";

	/**
	 * 手机号码格式错误
	 */
	public final static String PHONE_FORMAT_ERROR = "mx1117001";

	/**
	 * 设置性别异常
	 */
	public final static String SEX_TYPE_ERROR = "mx1117002";

	/**
	 * 生日可见设置异常
	 */
	public final static String BIRTHDAY_VISIBLE_ERROR = "mx1117003";

	/**
	 * 验证码为空
	 */
	public final static String CAPTCHA_EMPTY_ERROR = "mx1117004";

	/**
	 * 生日范围超出 默认为0到当前时间linux时间戳
	 */
	public final static String BIRTHDAY_ERROR = "mx1117005";

	/**
	 * 账号密码为空
	 */
	public final static String ACCOUNT_PWD_EMPTY_ERROR = "mx1117006";

	/**
	 * 手机已注册
	 */
	public final static String PHONE_REGISTED_ERROR = "mx1117007";

	/**
	 * 验证码不匹配
	 */
	public final static String CAPTCHA_NOT_MATCH_ERROR = "mx1117008";

	/**
	 * 手机号码不能为空
	 */
	public final static String PHONE_EMPTY_ERROR = "mx1117009";

	/**
	 * 发送注册验证码失败
	 */
	public final static String SEND_REG_CAPTHCA_ERROR = "mx1117010";

	/**
	 * 用户创建失败
	 */
	public final static String CREATE_USER_ERROR = "mx1117011";

	/**
	 * 昵称为空
	 */
	public final static String NICKNAME_EMPTY_ERROR = "mx1117012";

	/**
	 * 昵称字数超过上限了哦～
	 */
	public final static String NICKNAME_LENGTH_ERROR = "mx1117013";

	/**
	 * 昵称不可以有敏感词
	 */
	public final static String NICKNAME_SENSITIVE_ERROR = "mx1117014";

	/**
	 * 昵称不可以有特殊字符啦～
	 */
	public final static String NICKNAME_SPECIAL_ERROR = "mx1117015";

	/**
	 * 昵称不能以“魔线”、“moxian”、“魔線”开头哟～
	 */
	public final static String NICKNAME_MOXIAN_ERROR = "mx1117016";

	/**
	 * 每日心情不可为空
	 */
	public final static String DAILY_MOOD_EMPTY_ERROR = "mx1117017";

	/**
	 * 每日心情字数超过上限了哦～
	 */
	public final static String DAILY_MOOD_LENGTH_ERROR = "mx1117018";

	/**
	 * 每日心情 不可以包含敏感词
	 */
	public final static String DAILY_MOOD_SENSITIVE_ERROR = "mx1117019";

	/**
	 * 标签 字数超过上限了哦
	 */
	public final static String TAGS_LENGTH_ERROR = "mx1117020";

	/**
	 * 标签不可以包含敏感词
	 */
	public final static String TAGS_SENSITIVE_ERROR = "mx1117021";

	/**
	 * 出没地 字数超过上限
	 */
	public final static String FAVORITEPLACE_LENGTH_ERROR = "mx1117022";

	/**
	 * 出没地 敏感词
	 */
	public final static String FAVORITEPLACE_SENSITIVE_ERROR = "mx1117023";

	/**
	 * 学校 字数超过上限
	 */
	public final static String SCHOOL_LENGTH_ERROR = "mx1117024";

	/**
	 * 学校不能包含敏感词
	 */
	public final static String SCHOOL_SENSITIVE_ERROR = "mx1117025";

	/**
	 * 专业 字数超过上限
	 */
	public final static String EXPERTISE_LENGTH_ERROR = "mx1117026";

	/**
	 * 专业 不能包含敏感词
	 */
	public final static String EXPERTISE_SENSITIVE_ERROR = "mx1117027";

	/**
	 * 专长字数超过上限
	 */
	public final static String MAJOR_LENGTH_ERROR = "mx1117028";

	/**
	 * 专长 不能包含敏感词
	 */
	public final static String MAJOR_SENSITIVE_ERROR = "mx1117029";

	/**
	 * 参数错误
	 */
	public final static String PARAM_ERROR = "mx1117030";

	/**
	 * 找回密码发送验证码失败
	 */
	public final static String SEND_FORGET_CAPTHCA_ERROR = "mx1117031";

	/**
	 * 60秒内只能获取一次验证码
	 */
	public final static String SEND_ONE_MINUTE_LIMIT_ERROR = "mx1117032";

	/**
	 * 一天只能获取10次
	 */
	public final static String SEND_DAY_LIMIT_ERROR = "mx1117033";

	/**
	 * 电话号码不能为空
	 */
	public final static String PHONENO_CANNOT_BE_NULL = "mx1117034";

	/**
	 * 收件人不能为空
	 */
	public final static String RECIPIENT_CANNOT_BE_NULL = "mx1117035";

	/**
	 * 数据插入数据库时发生错误
	 */
	public final static String INSERT_DB_EXCEPTION = "mx1117036";
	/**
	 * 收货地址不能为空
	 */
	public final static String ADDRESS_CANNOT_BE_NULL = "mx1117037";

	/**
	 * 修改地址时用户Id不能为空
	 */
	public final static String USERID_CANNOT_BE_NULL = "mx1117038";

	/**
	 * 地址不存在
	 */
	public final static String ADDRESS_NOT_EXIST = "mx1117039";

	// login

	/**
	 * 邮箱账号不存在
	 */
	public final static String EMAIL_ACCOUNT_NOT_EXIST = "mx1117040";

	/**
	 * 手机账号不存在
	 */
	public final static String PHONE_ACCOUNT_NOT_EXIST = "mx1117041";

	/**
	 * 账号格式错误
	 */
	public final static String PHONE_ACCOUNT_FORMAT_ERROR = "mx1117042";

	/**
	 * 邮箱账号或密码错误
	 */
	public final static String EMAIL_OR＿PWD_ERROR = "mx1117043";

	/**
	 * 手机账号或者密码错误
	 */
	public final static String PHONE_OR_PWD_ERROR = "mx1117044";

	public final static String EMAIL_SEND_ERROR = "mx1117045";

	/**
	 * 邮箱格式错误
	 */
	public final static String EMAIL_FORMAT_ERROR = "mx1117046";

	/**
	 * 新密码不能为空
	 */
	public final static String NEWPASSWORD_CANNOT_BE_NULL = "mx1117047";

	/**
	 * 用户id不能为空
	 */
	public final static String USER_ID_CANNOT_BE_NULL = "mx1117048";

	/**
	 * 该用户不存在
	 */
	public final static String USER_IS_EXIST = "mx1117049";

	/**
	 * type 类型不为空
	 */
	public final static String TYPE_CANNOT_BE_NULL = "mx1117050";

	/**
	 * 收货地址长度超出150
	 */
	public final static String ADDRESS_LENGTH_ERROR = "mx1117051";

	/**
	 * 地址与持有人不匹配
	 */
	public final static String ADDRESS_HOLDER_NOT_MATCH = "mx1117052";

	/**
	 * 所在地码不能为空
	 */
	public final static String AREACODE_CANNOT_BE_NULL = "mx1117053";

	/**
	 * 所在地字符串不能为空
	 */
	public final static String AREANAME_CANNOT_BE_NULL = "mx1117054";

	/**
	 * 所在地字符串不能为空
	 */
	public final static String ADDRESS_ID_CANNOT_BE_NULL = "mx1117055";

	/**
	 * 地理位置是否可见设置超出范围
	 */
	public final static String LOCATION_VISIBLE_OVERFLOW = "mx1117056";

	/**
	 * 密码长度不对
	 */
	public final static String ACCOUNT_PWD_LENGTH_ERROR = "mx1117060";

	/**
	 * 定位服务未开启哦
	 */
	public final static String GPS_SERVICES_NOT_OPEN = "mx1117061";
	
	/**
	 * 所在地不能为空
	 */
	public final static String AREA_CODE_CANNOT_BE_NULL = "mx1117062";
	
	/**
	 * 原始密码不能为空
	 */
	public final static String PASSWORD_CANNOT_BE_NULL = "mx1117063";
	
	/**
	 * 用户密码修改失败
	 */
	public final static String PASSWORD_UPDATE_ERR = "mx1117064";
	
	/**
	 * 输入的密码太短
	 */
	public final static String PASSWORD_SHORT_ERR = "mx1117065";
	
	/**
	 * 输入的新密码太短
	 */
	public final static String NEW_PASSWORD_SHORT_ERR = "mx1117066";
	
	/**
	 * 重复输入的密码太短
	 */
	public final static String REPEAT_PASSWORD_SHORT_ERR = "mx1117067";

	/**
	 * 重复输入密码不能为空
	 */
	public final static String REPEAT_PASSWORD_CANNOT_BE_NULL = "mx1117068";
	
	/**
	 * 两次密码输入不一致
	 */
	public final static String TWICE_PASSWORD_NOT_SAME = "mx1117069";
	
	/**
	 * 原始密码输入错误
	 */
	public final static String PASSWORD_ERROR = "mx1117070";
	
	/**
	 * 邮件为空
	 */
	public final static String EMAIL_NULL_ERROR = "mx1117071";
	
	/**
	 * 验证码为空
	 */
	public final static String VALIDATECODE_NULL_ERROR = "mx1117072";
	
	/**
	 * 验证码错误
	 */
	public final static String VALIDATECODE_ERROR = "mx1117073";
	
	/**
	 * 验证码错误
	 */
	public final static String PHONENO_BANDED_ERROR = "mx1117074";
	
	/**
	 * 图片不能超过8张
	 */
	public final static String AVATAR_LIMIT_ERROR="mx1117075";

	/**
	 * 第三方授权信息id不能为空
	 */
	public final static String THIRD_PARTY_ACCREDIT_RECORD_ID_CANNOT_BE_NULL = "mx1117076";
	
	
	/**
	 * 需要删除授权信息的不存在
	 */
	public final static String  THIRD_PARTY_ACCREDIT_RECORD_NOT_EXIST = "mx1117077";
	
	public final static String PHONENO_IS_TOO_LONG = "mx1117078";
	
	public final static String TOKEN_INVALID="mx1117079";
}