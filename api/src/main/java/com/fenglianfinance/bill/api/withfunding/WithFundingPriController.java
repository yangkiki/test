package com.fenglianfinance.bill.api.withfunding;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.api.security.CurrentUser;
import com.fenglianfinance.bill.domain.MarginTrade;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.domain.WithFundingInfos;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.BankCardInfoDetails;
import com.fenglianfinance.bill.model.ResponseMessage;
import com.fenglianfinance.bill.model.ResponseMessage.Type;
import com.fenglianfinance.bill.model.WithFundingForm;
import com.fenglianfinance.bill.model.WithFundingInfoForm;
import com.fenglianfinance.bill.model.WithFundingInfoSearchCriteria;
import com.fenglianfinance.bill.model.WithFundingInfoView;
import com.fenglianfinance.bill.model.WithFundingInfoView4Admin;
import com.fenglianfinance.bill.service.MarginTradeService;
import com.fenglianfinance.bill.service.UserService;
import com.fenglianfinance.bill.service.WithFundingService;

@RestController
@RequestMapping(value = ApiConstants.URI_API + ApiConstants.URI_WITH_FUNDING)
public class WithFundingPriController {
	private static final Logger log = LoggerFactory
			.getLogger(WithFundingPriController.class);

	@Inject
	private WithFundingService withFundingService;

	@Inject
	private UserService userService;

	@Inject
	private MarginTradeService marginTradeService;

	/**
	 * 保存配资申请信息
	 * 
	 * @param user
	 * @param form
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月10日 上午11:09:54
	 */
	@RequestMapping(value = "/saveWithFunding", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<WithFundingInfoView> saveWithFunding(
			@CurrentUser UserAccount user, @RequestBody WithFundingInfoForm form) {
		// TODO 配资企业暂缺
		if (log.isDebugEnabled()) {
			log.debug("save different status' withFundings 4 UserAcct of @"
					+ user);
		}

		WithFundingInfoView withFundingInfoView = withFundingService
				.createWithFundingInfo(user, form);

		return new ResponseEntity<>(withFundingInfoView, HttpStatus.CREATED);
	}

	/**
	 * 获取某个用户的配资信息（查询条件）
	 * 
	 * @param user
	 * @param gearing
	 * @param mode
	 * @param statusArr
	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月11日 下午7:32:22
	 */
	@RequestMapping(value = "/getWithFundingInfos", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<WithFundingInfoView>> getWithFundingInfos(
			@CurrentUser UserAccount user,
			@RequestParam("gearing") Long gearing,
			@RequestParam("mode") String mode,
			@RequestParam("statusArr") String[] statusArr,
			@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
		Assert.notNull(user, "UserAccount can't be null ");

		if (log.isDebugEnabled()) {
			log.debug("get withFundingInfos by searchCriteria 4 UserAcct of @"
					+ user);
		}
		WithFundingInfoSearchCriteria searchCriteria = new WithFundingInfoSearchCriteria();
		searchCriteria.setGearing(gearing);
		searchCriteria.setMode(mode);
		searchCriteria.setStatusArr(statusArr);
		searchCriteria.setActive(true);

		return new ResponseEntity<>(
				withFundingService.getWithFundingInfosBySearchCriteria(user,
						searchCriteria, page), HttpStatus.OK);
	}

	/**
	 * @Admin 获取所有人的配资信息（查询条件）
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月30日 下午2:37:48
	 */
	@RequestMapping(value = "/getAllPeopleWithFundingInfos", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<WithFundingInfoView4Admin>> getWithFundingInfosBySearchCriteria(
			@CurrentUser UserAccount user,
			@RequestParam("gearing") Long gearing,
			@RequestParam("mode") String mode,
			@RequestParam("statusArr") String[] statusArr,
			@PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
		Assert.notNull(user, "UserAccount can't be null ");
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");

		if (log.isDebugEnabled()) {
			log.debug("get withFundingInfos by searchCriteria 4 UserAcct of @"
					+ user);
		}

		WithFundingInfoSearchCriteria searchCriteria = new WithFundingInfoSearchCriteria();
		searchCriteria.setGearing(gearing);
		searchCriteria.setMode(mode);
		searchCriteria.setStatusArr(statusArr);
		searchCriteria.setActive(true);
		return new ResponseEntity<>(
				withFundingService.getWithFundingInfosBySearchCriteria(
						searchCriteria, page), HttpStatus.OK);
	}

	/**
	 * 管理员根据客户查询客户银行卡
	 * @param user 当前登录用户
	 * @param id 用户id
	 * @return
	 */
	@RequestMapping(value = { "/{id}/cards" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<BankCardInfoDetails>> boundCards(
			@CurrentUser UserAccount user, @PathVariable("id") Long id) {
		Assert.notNull(user, "UserAccount can't be null ");
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");
		
		if (log.isDebugEnabled()) {
			log.debug("accounting info @" + id);
		}

		List<BankCardInfoDetails> cards = userService
				.findBoundCardsByUserId(id);

		return new ResponseEntity<>(cards, HttpStatus.OK);
	}

	/**
	 * @Admin 审核、拒绝、生成标的物
	 * @author ronghui.ou
	 * @param serialNumber
	 *            订单号
	 * @param operation
	 *            操作类型;
	 * @return
	 */
	@RequestMapping(value = "/auditData", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> auditData(@CurrentUser UserAccount user,
			@RequestParam("serialNumber") String serialNumber,
			@RequestParam("operation") String operation) {

		Assert.notNull(user, "UserAccount can't be null ");
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");
		Assert.notNull(serialNumber, "serialNumber can't be null ");
		Assert.notNull(operation, "operation can't be null ");

		if (log.isDebugEnabled()) {
			log.debug("auditData WithFunding 4 UserAcct of @" + user);
		}

		// TODO 根据 operation 来 更新数据、调用接口

		return new ResponseEntity<>("test is ok!", HttpStatus.OK);
	}

	/**
	 * 根据serialNumber更改相应配资信息的状态
	 * 
	 * @param user
	 * @param serialNumber
	 * @param status
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午2:09:25
	 */
	@RequestMapping(value = { "/updateStatus" }, method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<ResponseMessage> updateStatus(
			@CurrentUser UserAccount user,
			@RequestBody @Valid WithFundingForm withFundingForm) {
		Assert.notNull(user, "UserAccount can't be null ");
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");

		if (log.isDebugEnabled()) {
			log.debug("update WithFundingInfo status.@ serialNumber:"
					+ withFundingForm.getSerialNumber() + ";status:"
					+ withFundingForm.getStatus());
		}
		withFundingService.updateStatus(withFundingForm.getSerialNumber(),
				WithFundingInfos.Status.valueOf(withFundingForm.getStatus()));

		return new ResponseEntity<>(new ResponseMessage(Type.success,
				"statusUpdated"), HttpStatus.NO_CONTENT);
	}

	/**
	 * 通过配资信息的serialNumber获取保证金交易信息
	 * 
	 * @param user
	 * @param serialNumber
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午2:07:33
	 */
	@RequestMapping(value = "/getMarginTrade/{serialNumber}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<MarginTrade> getMarginTradeBySerialNumber(
			@CurrentUser UserAccount user,
			@PathVariable("serialNumber") String serialNumber) {

		Assert.notNull(user, "UserAccount can't be null ");
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");

		if (log.isDebugEnabled()) {
			log.debug("getMarginTradeBySerialNumber.@ serialNumber:"
					+ serialNumber);
		}
		return new ResponseEntity<>(
				marginTradeService.getMarginTradeByBidOrdId(serialNumber),
				HttpStatus.OK);
	}

}
