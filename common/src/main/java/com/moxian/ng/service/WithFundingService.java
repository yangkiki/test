package com.moxian.ng.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.SerialNumberGeneratorUtils;
import com.moxian.ng.util.FLBase64Utils;
import com.moxian.ng.util.FLRSAUtils;
import com.moxian.ng.util.FLStringUtils;
import com.moxian.ng.util.LogUtils;
import net.sf.json.JSONObject;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.fenglianfinance.account.model.AgreementInfoReq;
import com.fenglianfinance.account.model.AgreementInfoResp;
import com.fenglianfinance.account.model.QueryAccountReq;
import com.fenglianfinance.account.model.QueryAccountReqBody;
import com.fenglianfinance.account.model.QueryAccountResp;
import com.fenglianfinance.account.model.RegisterAccountReq;
import com.fenglianfinance.account.model.RegisterAccountReqBody;
import com.fenglianfinance.account.model.RegisterAccountResp;
import com.fenglianfinance.account.model.ServiceResult;
import com.fenglianfinance.account.model.SignatureInfo;
import com.moxian.ng.domain.BankCardInfo;
import com.moxian.ng.domain.MarginTrade;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.domain.WithFundingAccount;
import com.moxian.ng.domain.WithFundingAccountLog;
import com.moxian.ng.domain.WithFundingBrokerageConfs;
import com.moxian.ng.domain.WithFundingLinesConfs;
import com.moxian.ng.model.Constants;
import com.moxian.ng.model.WithFundingBrokerageConfModel;
import com.moxian.ng.model.WithFundingConfs;
import com.moxian.ng.model.WithFundingInfoForm;
import com.moxian.ng.model.WithFundingInfoSearchCriteria;
import com.moxian.ng.model.WithFundingInfoView;
import com.moxian.ng.model.WithFundingInfoView4Admin;
import com.moxian.ng.model.WithFundingLinesConfModel;
import com.moxian.ng.repository.BankCardInfoRepository;
import com.moxian.ng.repository.MarginTradeRepository;
import com.moxian.ng.repository.WithFundingAccountLogRepository;
import com.moxian.ng.repository.WithFundingAccountRepository;
import com.moxian.ng.repository.WithFundingBrokerageConfsRepository;
import com.moxian.ng.repository.WithFundingInfosRepository;
import com.moxian.ng.repository.WithFundingInfosSpecifications;
import com.moxian.ng.repository.WithFundingLinesConfsRepository;

/**
 * @author wangli@flf77.com
 * @date 2015年1月29日 下午7:40:24
 */
@Service
@Transactional
public class WithFundingService {

	public final static Logger log = LoggerFactory
			.getLogger(WithFundingService.class);

	@Inject
	private WithFundingInfosRepository withFundingInfosRepository;

	@Inject
	private WithFundingLinesConfsRepository withFundingLinesConfsRepository;

	@Inject
	private WithFundingBrokerageConfsRepository withFundingBrokerageConfsRepository;

	@Inject
	private SerialNumberGeneratorUtils orderSerialNumberGenerator;

	@Inject
	private MarginTradeRepository marginTradeRepository;

	@Inject
	private BankCardInfoRepository bankCardInfoRepository;

	@Inject
	private WithFundingAccountRepository withFundingAccountRepository;

	@Inject
	private WithFundingAccountLogRepository withFundingAccountLogRepository;

	@Inject
	private HttpClientService httpClientService;

	@Inject
	private RabbitTemplate rabbitTemplate;

	/**
	 * 通过配资类型、杠杆比例、订单状态分页查询配资记录
	 * 
	 * @param mode
	 * @param gearing
	 * @param status
	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月29日 下午7:40:34
	 */
	public Page<WithFundingInfoView> findWithFundingInfoViews(String mode,
			Long gearing, String status, Pageable page) {

		if (log.isDebugEnabled()) {
			log.debug("findWithFundingInfos by mode@" + mode + ", gearing@:"
					+ gearing + ", status@" + status + ", page@" + page);
		}

		Page<WithFundingInfos> withFundingInfos = withFundingInfosRepository
				.findAll(WithFundingInfosSpecifications.filterByCriteria(
						WithFundingInfos.Mode.valueOf(mode), gearing,
						WithFundingInfos.Status.valueOf(status)), page);

		if (log.isDebugEnabled()) {
			log.debug("total elements@" + withFundingInfos.getTotalElements());
		}

		return DTOUtils.mapPage(withFundingInfos, WithFundingInfoView.class);
	}

	/**
	 * 查询所有有效的配资配置信息
	 * 
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月1日 下午5:34:13
	 */
	public WithFundingConfs getWithFundingConfs() {
		WithFundingConfs withFundingConfs = new WithFundingConfs();

		withFundingConfs
				.setWithFundingLinesConfModelList(getWithFundingLinesConfModelList());

		withFundingConfs
				.setWithFundingBrokerageConfModelList(getWithFundingBrokerageConfModelList());

		return withFundingConfs;
	}

	/**
	 * 获取有效的预警线和平仓线配置信息
	 * 
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月1日 下午5:34:22
	 */
	public List<WithFundingLinesConfModel> getWithFundingLinesConfModelList() {
		List<WithFundingLinesConfs> withFundingLinesConfsList = withFundingLinesConfsRepository
				.findByActiveIsTrue();
		if (log.isDebugEnabled()) {
			log.debug("get active withFundingLinesConfs.. size@"
					+ withFundingLinesConfsList.size());
		}
		return DTOUtils.mapList(withFundingLinesConfsList,
				WithFundingLinesConfModel.class);
	}

	/**
	 * 获取有效的佣金配置信息
	 * 
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月1日 下午5:35:11
	 */
	public List<WithFundingBrokerageConfModel> getWithFundingBrokerageConfModelList() {
		List<WithFundingBrokerageConfs> withFundingInterestConfsList = withFundingBrokerageConfsRepository
				.findByActiveIsTrue();
		if (log.isDebugEnabled()) {
			log.debug("get active withFundingInterestConfs.. size@"
					+ withFundingInterestConfsList.size());
		}
		return DTOUtils.mapList(withFundingInterestConfsList,
				WithFundingBrokerageConfModel.class);
	}

	/**
	 * 保存配资信息
	 * 
	 * @param user
	 * @param form
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月10日 上午11:10:49
	 */
	public WithFundingInfoView createWithFundingInfo(UserAccount user,
			WithFundingInfoForm form) {
		Assert.notNull(user, "UserAccount can not be null");
		Assert.notNull(form, "WithFundingInfoForm can not be null");
		for (int i = 0; i < WithFundingInfos.Status.values().length; i++) {
			if (WithFundingInfos.Status.values()[i].toString().equals(
					form.getStatus())) {
				break;
			}
			if (i == (WithFundingInfos.Status.values().length - 1)) {
				log.error("WithFundingInfos' status doesn't contains@"
						+ form.getStatus());
				return null;
			}
			continue;
		}

		if (log.isDebugEnabled()) {
			log.debug("saving WithFundingInfos @" + form);
		}
		form = calculateWithFundingInfoMoney(form);
		WithFundingInfos wfi = DTOUtils.map(form, WithFundingInfos.class);

		// TODO 配资流水号暂时采用头两位为00开始
		if (form.getStatus().equals(WithFundingInfos.Status.PENDING.toString())) {
			String serialNumber = "00"
					+ orderSerialNumberGenerator.nextOrderSerialNumber()
							.substring(2);
			wfi.setSerialNumber(serialNumber);
		}
		wfi.setStatus(WithFundingInfos.Status.valueOf(form.getStatus()));
		wfi.setUser(user);
		wfi.setCreatedBy(user);
		wfi.setLastModifiedBy(user);
		WithFundingInfos saved = withFundingInfosRepository.save(wfi);

		// 生成投标ID 格式为0000XXXXXXXXXXXX
		// StringBuffer bidOrdDate = new StringBuffer();

		// bidOrdId.append(RandomStringUtils.random(16, false, true));
		MarginTrade marginTrade = new MarginTrade();
		marginTrade.setBidOrdId(wfi.getSerialNumber());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		marginTrade.setBidOrdDate(sdf.format(new Date()));
		// 保存保证金交易信息
		marginTrade = marginTradeRepository.save(marginTrade);

		if (log.isDebugEnabled()) {
			log.debug("saved WithFundingInfos @" + saved);
		}

		return DTOUtils.map(saved, WithFundingInfoView.class);
	}

	/**
	 * 计算保证金、预警线、平仓线和管理费（佣金）
	 * 
	 * @param form
	 * @return
	 */
	private WithFundingInfoForm calculateWithFundingInfoMoney(
			WithFundingInfoForm form) {
		Assert.notNull(form.getGearing(), "Gearing can not be null");
		Assert.notNull(form.getFunding(), "Funding can not be null");
		Assert.notNull(form.getMode(), "Mode can not be null");
		Assert.notNull(form.getTerms(), "Terms can not be null");
		WithFundingInfoForm result = form;

		Long gearing = form.getGearing();
		BigDecimal funding = form.getFunding();
		String mode = form.getMode();
		Integer terms = form.getTerms();
		// 保证金
		result.setDeposit(funding.divide(new BigDecimal(gearing), 2,
				BigDecimal.ROUND_DOWN));

		// 预警线、平仓线
		WithFundingLinesConfs withFundingLinesConfs = withFundingLinesConfsRepository
				.findByGearingAndActiveIsTrue(gearing);

		BigDecimal positionspreadingLineRate = withFundingLinesConfs
				.getPositionspreadingLineRate();
		BigDecimal warningLineRateOne = withFundingLinesConfs
				.getWarningLineRateOne();
		BigDecimal warningLineRateTwo = withFundingLinesConfs
				.getWarningLineRateTwo();
		// 判断操作实盘金额大小，如果为10W以下，预警线110%，平仓线106%
		if (funding.compareTo(new BigDecimal("100000")) < 0) {
			positionspreadingLineRate = new BigDecimal(106.00);
			warningLineRateOne = warningLineRateTwo = new BigDecimal(110.00);
		}

		result.setPositionspreadingLine(positionspreadingLineRate
				.multiply(funding).movePointLeft(2)
				.setScale(2, BigDecimal.ROUND_DOWN));
		result.setWarningLineOne(warningLineRateOne.multiply(funding)
				.movePointLeft(2).setScale(2, BigDecimal.ROUND_DOWN));
		result.setWarningLineTwo(warningLineRateTwo.multiply(funding)
				.movePointLeft(2).setScale(2, BigDecimal.ROUND_DOWN));

		// 管理费（利息）
		WithFundingBrokerageConfs withFundingBrokerageConfs = withFundingBrokerageConfsRepository
				.find4WithFundingApply(funding, gearing);

		if (WithFundingInfos.Mode.DAY.toString().equals(mode)) {
			BigDecimal dailyPercentageRate = withFundingBrokerageConfs
					.getDailyPercentageRate();
			// 判断操作实盘金额大小，如果为10W以下，利息1.5分
			if (funding.compareTo(new BigDecimal("100000")) < 0) {
				dailyPercentageRate = new BigDecimal("15").movePointLeft(2)
						.setScale(2, BigDecimal.ROUND_DOWN);
			}
			result.setBrokerage(dailyPercentageRate.multiply(funding)
					.movePointLeft(2).setScale(2, BigDecimal.ROUND_DOWN));

			terms = (terms > 30 ? 30 : terms);
			terms = (terms < 2 ? 2 : terms);
			result.setTerms(terms);
		} else if (WithFundingInfos.Mode.MONTH.toString().equals(mode)) {
			// TODO
			BigDecimal annualPercentageRate = withFundingBrokerageConfs
					.getAnnualPercentageRate();

			result.setBrokerage(annualPercentageRate.multiply(funding)
					.movePointLeft(2).setScale(2, BigDecimal.ROUND_DOWN));
		}
		return result;
	}

	/**
	 * 查询条件下的配资申请信息（所有人）【方法具有泄露用户信息的风险】
	 *
	 * @param searchCriteria
	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午1:41:38
	 */
	public Page<WithFundingInfoView4Admin> getWithFundingInfosBySearchCriteria(
			WithFundingInfoSearchCriteria searchCriteria, Pageable page) {
		if (log.isDebugEnabled()) {
			log.debug("find active withFundingInfoView@");
		}
		Page<WithFundingInfos> withFundingInfos = withFundingInfosRepository
				.findAll(WithFundingInfosSpecifications
						.searchWithFundingInfos(searchCriteria), page);

		return DTOUtils.mapPage(withFundingInfos,
				WithFundingInfoView4Admin.class);
	}

	/**
	 * 根据条件查询某用户的配资申请信息
	 * 
	 * @param user
	 * @param searchCriteria
	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月9日 下午6:52:08
	 */
	public Page<WithFundingInfoView> getWithFundingInfosBySearchCriteria(
			UserAccount user, WithFundingInfoSearchCriteria searchCriteria,
			Pageable page) {
		Assert.notNull(user, "user can not be null");
		if (log.isDebugEnabled()) {
			log.debug("find active withFundingInfoView for someone@");
		}
		Page<WithFundingInfos> withFundingInfos = withFundingInfosRepository
				.findAll(WithFundingInfosSpecifications.searchWithFundingInfos(
						user, searchCriteria), page);

		return DTOUtils.mapPage(withFundingInfos, WithFundingInfoView.class);
	}

	public void updateStatus(String serialNumber, WithFundingInfos.Status status) {
		withFundingInfosRepository.updateStatus(serialNumber, status);
		if (status.equals(WithFundingInfos.Status.UNPUBLISHED)) {
			withFundingInfosRepository.updateStatus(serialNumber,
					LocalDateTime.now());
		}
	}

	/**
	 * 生成RegisterAccountReqBody
	 * 
	 * @param signatureInfo
	 * @param withFundingInfos
	 * @return
	 * @author wangli@flf77.com
	 * @throws Exception
	 * @date 2015年3月6日 下午3:22:44
	 */
	public RegisterAccountReqBody generateRegisterAccountReqBody(
			SignatureInfo signatureInfo, WithFundingInfos withFundingInfos)
			throws Exception {
		RegisterAccountReqBody registerAccountReqBody = new RegisterAccountReqBody();
		Assert.notNull(withFundingInfos, "withFundingInfos can not be null");

		AgreementInfoReq agreementInfoReq = buildAgreementInfoReq(
				signatureInfo, AgreementInfoReq.SERVICE_REGISTER_ACCOUNT);
		RegisterAccountReq registerAccountReq = buildRegisterAccountReq(
				signatureInfo, withFundingInfos);

		// 签名
		String dataString = agreementInfoReq.toLinkString()
				+ FLStringUtils.toLinkString(registerAccountReq);

		log.info("发送请求--签名前: " + dataString);
		String sign = FLRSAUtils.sign(
				dataString.getBytes(Constants.CHARSET_UTF_8),
				signatureInfo.getPriKey());
		log.info("发送请求--签名后: " + sign);
		agreementInfoReq.setSign(sign);

		registerAccountReqBody.setAgreementInfoReq(agreementInfoReq);
		registerAccountReqBody.setRegisterAccountReq(registerAccountReq);
		return registerAccountReqBody;
	}

	/**
	 * 组建AgreementInfoReq对象
	 * 
	 * @param signatureInfo
	 * @param serviceName
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月18日 下午2:51:49
	 */
	private AgreementInfoReq buildAgreementInfoReq(SignatureInfo signatureInfo,
			String serviceName) {
		AgreementInfoReq agreementInfoReq = new AgreementInfoReq();
		agreementInfoReq.setService(serviceName);
		agreementInfoReq.setSignType(signatureInfo.getAlgorthm());
		agreementInfoReq.setVersion(Constants.VERSION);
		agreementInfoReq.setChannelNo(signatureInfo.getChannelNo());
		agreementInfoReq.setTransDate(new DateTime()
				.toString(Constants.DATE_FORMAT));
		agreementInfoReq.setTransTime(new DateTime()
				.toString(Constants.TIME_24_FORMAT));
		agreementInfoReq.setChannelOrdersNo(orderSerialNumberGenerator
				.nextOrderSerialNumber());
		return agreementInfoReq;
	}

	/**
	 * 组建RegisterAccountReq对象
	 * 
	 * @param signatureInfo

	 * @return
	 * @author wangli@flf77.com
	 * @throws Exception
	 * @date 2015年3月6日 下午3:23:56
	 */
	private RegisterAccountReq buildRegisterAccountReq(
			SignatureInfo signatureInfo, WithFundingInfos withFundingInfos)
			throws Exception {
		RegisterAccountReq registerAccountReq = new RegisterAccountReq();
		UserAccount user = withFundingInfos.getUser();
		Assert.notNull(user, "UserAccount can't be null ");

		registerAccountReq.setUserId(user.getId().toString());
		registerAccountReq.setSupplierNo(Constants.SUPPLIER_NO_HUNDSUN);
		String userName = FLBase64Utils.base64Encode(FLRSAUtils
				.encryptByPublicKey(user.getUsername().getBytes(),
						signatureInfo.getPubKey()));
		registerAccountReq.setUserName(userName);
		registerAccountReq.setIdentityType(Constants.IDENTITY_TYPE_ID_CARD);
		String identityCode = FLBase64Utils
				.base64Encode(FLRSAUtils.encryptByPublicKey(user
						.getIdCardVerification().getCardNumber().getBytes(),
						signatureInfo.getPubKey()));
		registerAccountReq.setIdentityCode(identityCode);
		// 申请配资时必然已通过实名认证
		registerAccountReq
				.setRealNameState(RegisterAccountReq.REALNAME_STATE_YES);

		List<BankCardInfo> bankCardInfoList = bankCardInfoRepository
				.findByUserId(user.getId());
		Assert.notEmpty(bankCardInfoList, "bankCardInfoList can not be null");
		registerAccountReq.setBankName(bankCardInfoList.get(0).getBankId());
		registerAccountReq.setSettleCardNo(FLBase64Utils
				.base64Encode(FLRSAUtils.encryptByPublicKey(bankCardInfoList
						.get(0).getCardId().getBytes(),
						signatureInfo.getPubKey())));

		if (!withFundingInfos.getStatus().equals(
				WithFundingInfos.Status.PENDING)
				&& !withFundingInfos.getStatus().equals(
						WithFundingInfos.Status.DISMISSED)) {
			registerAccountReq
					.setMarginState(RegisterAccountReq.MARGIN_STATE_YES);
		} else {
			registerAccountReq
					.setMarginState(RegisterAccountReq.MARGIN_STATE_NO);
		}

		registerAccountReq
				.setMarginPayType(RegisterAccountReq.MARGIN_PAY_TYPE_ONLINE);
		registerAccountReq.setMarginAmt(withFundingInfos.getDeposit()
				.movePointRight(2).intValue()
				+ "");
		registerAccountReq.setBorrowAmt(withFundingInfos.getFunding()
				.movePointRight(2).intValue()
				+ "");
		registerAccountReq.setFundAmt((withFundingInfos.getDeposit()
				.movePointRight(2).intValue() + withFundingInfos.getFunding()
				.movePointRight(2).intValue())
				+ "");

		LocalDate fundStartDate = (withFundingInfos.getConfirmedDate() == null ? LocalDate
				.now() : withFundingInfos.getConfirmedDate().toLocalDate());
		registerAccountReq.setFundStartDate(fundStartDate
				.format(DateTimeFormatter.ofPattern(Constants.DATE_FORMAT)));
		LocalDate fundEndDate = fundStartDate;
		if (withFundingInfos.getMode().equals(WithFundingInfos.Mode.DAY)) {
			fundEndDate = fundStartDate.plusDays(withFundingInfos.getTerms());
			registerAccountReq
					.setPlanBreathWay(RegisterAccountReq.PLAN_BREATH_WAY_DAY);
		} else if (withFundingInfos.getMode().equals(
				WithFundingInfos.Mode.MONTH)) {
			fundEndDate = fundStartDate.plusMonths(withFundingInfos.getTerms());
			registerAccountReq
					.setPlanBreathWay(RegisterAccountReq.PLAN_BREATH_WAY_MONTH);
		}
		registerAccountReq.setFundEndDate(fundEndDate.format(DateTimeFormatter
				.ofPattern(Constants.DATE_FORMAT)));

		WithFundingBrokerageConfs withFundingBrokerageConfs = withFundingBrokerageConfsRepository
				.find4WithFundingApply(withFundingInfos.getFunding(),
						withFundingInfos.getGearing());
		// 利率 单位：万分比；最多两位小数位
		if (WithFundingInfos.Mode.DAY.equals(withFundingInfos.getMode())) {
			registerAccountReq.setInterestRate(withFundingBrokerageConfs
					.getDailyPercentageRate().movePointRight(2)
					.setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
		} else if (WithFundingInfos.Mode.MONTH.equals(withFundingInfos
				.getMode())) {
			registerAccountReq.setInterestRate(withFundingBrokerageConfs
					.getAnnualPercentageRate().movePointRight(2)
					.setScale(2, BigDecimal.ROUND_DOWN).toPlainString());
		}

		// TODO 佣金 单位：万分比；最多两位小数位
		String commission = (withFundingInfos.getCommission() == null ? "3"
				: withFundingInfos.getCommission()
						.setScale(2, BigDecimal.ROUND_DOWN).toPlainString());

		registerAccountReq.setCommission(commission);
		// 利息 单位：分；无小数位
		registerAccountReq.setInterest(withFundingInfos.getBrokerage()
				.multiply(new BigDecimal(withFundingInfos.getTerms()))
				.movePointRight(2).setScale(2, BigDecimal.ROUND_DOWN)
				.toPlainString());

		registerAccountReq.setGuardLineOne(withFundingInfos.getWarningLineOne()
				.movePointRight(2).intValue()
				+ "");
		registerAccountReq.setGuardLineTwo(withFundingInfos.getWarningLineTwo()
				.movePointRight(2).intValue()
				+ "");
		registerAccountReq.setClearLine(withFundingInfos
				.getPositionspreadingLine().movePointRight(2).intValue()
				+ "");

		registerAccountReq.setMobileId((user.getMobileNumber() == null ? ""
				: user.getMobileNumber()));
		registerAccountReq.setEmail((user.getEmail() == null ? "" : user
				.getEmail()));
		registerAccountReq.setRetUrl(signatureInfo.getPrefixRetUrl()
				+ AgreementInfoReq.SERVICE_REGISTER_ACCOUNT);
		return registerAccountReq;
	}

	/**
	 * 保存响应流水并往配资开户的账户信息插入一条数据
	 * 
	 * @param agreementInfoResp
	 * @param registerAccountResp
	 * @param signatureInfo
	 * @author wangli@flf77.com
	 * @throws Exception
	 * @date 2015年3月6日 下午4:55:29
	 */
	public void registerAccountResp(AgreementInfoResp agreementInfoResp,
			RegisterAccountResp registerAccountResp, SignatureInfo signatureInfo)
			throws Exception {
		// 1.RSA校验
		String dataString = agreementInfoResp.toLinkString()
				+ FLStringUtils.toLinkString(registerAccountResp);
		log.info("签名校验前: " + dataString);

		if (FLRSAUtils.verify(
				dataString.getBytes(Constants.CHARSET_ISO_8859_1),
				signatureInfo.getPubKey(), agreementInfoResp.getSign())) {
			// 2.流水落地
			WithFundingAccountLog withFundingAccountLog = new WithFundingAccountLog();

			withFundingAccountLog.setService(agreementInfoResp.getService());
			withFundingAccountLog.setVersion(agreementInfoResp.getVersion());
			withFundingAccountLog.setChannelNo(agreementInfoResp
					.getChannel_no());
			withFundingAccountLog.setChannelOrderNo(agreementInfoResp
					.getChannel_order_no());
			withFundingAccountLog.setRetCode(agreementInfoResp.getRet_code());
			withFundingAccountLog.setRetMsg(agreementInfoResp.getRet_msg());
			withFundingAccountLog = withFundingAccountLogRepository
					.saveAndFlush(withFundingAccountLog);
			log.info("配资开户流水记入数据库: "
					+ JSONObject.fromObject(withFundingAccountLog).toString());

			if (withFundingAccountLog.getRetCode().equals(
					Constants.RESP_CODE_SUCCESS)) {
				// 3.账户信息插入数据库保存
				WithFundingAccount withFundingAccount = withFundingAccountRepository
						.findByChannelOrderNoAndActiveIsFalse(agreementInfoResp
								.getChannel_order_no());
				LogUtils.error(log, "withFundingAccount can not be null!");
				Assert.isNull(withFundingAccount,
						"withFundingAccount can not be null!");
				withFundingAccount.setPzOrderNo(registerAccountResp
						.getPz_order_no());
				withFundingAccount.setOpenState(registerAccountResp
						.getOpen_state());
				withFundingAccount.setSupplierNo(registerAccountResp
						.getSupplier_no());
				withFundingAccount.setOpenTime(registerAccountResp
						.getOpen_time());

				String accountName = FLRSAUtils.decryptByPublicKey(
						FLBase64Utils.decode(registerAccountResp
								.getAccount_name()), signatureInfo.getPubKey());
				withFundingAccount.setAccountName(accountName);

				String accountNo = FLRSAUtils.decryptByPublicKey(FLBase64Utils
						.decode(registerAccountResp.getAccount_no()),
						signatureInfo.getPubKey());
				withFundingAccount.setAccountNo(accountNo);

				String operatorId = FLRSAUtils.decryptByPublicKey(FLBase64Utils
						.decode(registerAccountResp.getOperator_id()),
						signatureInfo.getPubKey());
				withFundingAccount.setOperatorId(operatorId);

				String password = FLRSAUtils
						.decryptByPublicKey(FLBase64Utils
								.decode(registerAccountResp.getPassword()),
								signatureInfo.getPubKey());
				withFundingAccount.setPassword(password);
				withFundingAccount
						.setWithFundingAccountLog(withFundingAccountLog);
				withFundingAccount.setActive(true);

				withFundingAccountRepository.saveAndFlush(withFundingAccount);
				log.info("账户信息记入数据库: "
						+ JSONObject.fromObject(withFundingAccount).toString());

				// TODO 4.发送短信
				// rabbitTemplate.convertAndSend(MessagingConstants.EXCHANGE_SMS,
				// MessagingConstants.ROUTING_SMS, new SmsMessage(
				// "18521051719", "ss"));
			} else {
				log.info("withfunding registerAccountResp unsuccess @CODE: "
						+ withFundingAccountLog.getRetCode() + " @MSG: "
						+ withFundingAccountLog.getRetMsg());
			}
		}
	}

	/**
	 * 发送json开户
	 * 
	 * @param signatureInfo
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @throws Exception
	 * @date 2015年3月9日 下午2:44:02
	 */
	public ServiceResult sendRegisterAccountReqBody(
			SignatureInfo signatureInfo, String serialNumber) throws Exception {
		WithFundingInfos withFundingInfos = withFundingInfosRepository
				.findBySerialNumberAndActiveIsTrue(serialNumber);
		RegisterAccountReqBody registerAccountReqBody = generateRegisterAccountReqBody(
				signatureInfo, withFundingInfos);
		// 1.发送之前插入WithFundingAccount一条无效记录，保存channel_order_no和with_funding_infos_sn对应关系
		WithFundingAccount withFundingAccount = new WithFundingAccount();
		withFundingAccount.setActive(false);
		withFundingAccount.setChannelNo(signatureInfo.getChannelNo());
		withFundingAccount.setVersion(registerAccountReqBody
				.getAgreementInfoReq().getVersion());
		withFundingAccount.setChannelOrderNo(registerAccountReqBody
				.getAgreementInfoReq().getChannelOrdersNo());
		withFundingAccount.setWithFundingInfos(withFundingInfos);
		withFundingAccountRepository.saveAndFlush(withFundingAccount);

		// 2.发送请求
		httpClientService.setServiceUrl(signatureInfo.getReqUrl());
		httpClientService.setContentType(Constants.APPLICATION_JSON);
		JSONObject json = JSONObject.fromObject(registerAccountReqBody);
		ServiceResult serviceResult = httpClientService.postJson(FLStringUtils
				.convertString(json.toString()));
		return serviceResult;
	}

	/**
	 * 发送json查询开户情况
	 * 
	 * @param signatureInfo
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @throws Exception
	 * @date 2015年3月9日 下午2:44:02
	 */
	public QueryAccountResp sendQueryAccountReqBodyAndGetResult(
			SignatureInfo signatureInfo, String serialNumber) throws Exception {
		QueryAccountResp qar = new QueryAccountResp();
		QueryAccountReqBody queryAccountReqBody = generateQueryAccountReqBody(
				signatureInfo, serialNumber);
		httpClientService.setServiceUrl(signatureInfo.getReqUrl());
		httpClientService.setContentType(Constants.APPLICATION_JSON);
		JSONObject json = JSONObject.fromObject(queryAccountReqBody);
		ServiceResult serviceResult = httpClientService.postJson(FLStringUtils
				.convertString(json.toString()));

		// 处理同步返回的json
		String returnJson = serviceResult
				.getAttribute(HttpClientService.RESULT_STRING);
		LogUtils.info(log, "查询开户同步返回json @" + returnJson);

		JSONObject returnJsonObj = JSONObject.fromObject(returnJson);
		qar = (QueryAccountResp) JSONObject.toBean(returnJsonObj,
				QueryAccountResp.class);
		return qar;
	}

	/**
	 * 生成RegisterAccountReqBody
	 * 
	 * @param signatureInfo
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月18日 下午2:43:16
	 */
	public QueryAccountReqBody generateQueryAccountReqBody(
			SignatureInfo signatureInfo, String serialNumber) {
		QueryAccountReqBody queryAccountReqBody = new QueryAccountReqBody();

		AgreementInfoReq agreementInfoReq = buildAgreementInfoReq(
				signatureInfo, AgreementInfoReq.SERVICE_QUERY_ACCOUNT);
		QueryAccountReq queryAccountReq = new QueryAccountReq();

		WithFundingAccount withFundingAccount = withFundingAccountRepository
				.findByWithFundingInfosSerialNumber(serialNumber);
		LogUtils.error(log, "withFundingAccount can not be null!");
		Assert.isNull(withFundingAccount, "withFundingAccount can not be null!");

		queryAccountReq.setChannelOrderNo(withFundingAccount.getChannelNo());

		queryAccountReqBody.setAgreementInfoReq(agreementInfoReq);
		queryAccountReqBody.setQueryAccountReq(queryAccountReq);
		return queryAccountReqBody;

	}
}
