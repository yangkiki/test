package com.fenglianfinance.bill.api.personal;

import java.util.List;

import javax.inject.Inject;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.api.security.CurrentUser;
import com.fenglianfinance.bill.api.user.CurrentUserController;
import com.fenglianfinance.bill.domain.TransactionLog;
import com.fenglianfinance.bill.domain.TransactionType;
import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.EnterpriseDetails;
import com.fenglianfinance.bill.model.OrderDetails;
import com.fenglianfinance.bill.model.ProductDetails;
import com.fenglianfinance.bill.model.ProductForm;
import com.fenglianfinance.bill.model.TransactionLogDetails;
import com.fenglianfinance.bill.model.TransactionLogStatistics;
import com.fenglianfinance.bill.model.UserAcctView;
import com.fenglianfinance.bill.service.EnterpriseService;
import com.fenglianfinance.bill.service.OrderService;
import com.fenglianfinance.bill.service.ProductService;
import com.fenglianfinance.bill.service.UserService;

@RestController
@RequestMapping(value = ApiConstants.URI_API + ApiConstants.URI_PERSONAL)
public class UserAccountController {
	private static final Logger log = LoggerFactory
			.getLogger(CurrentUserController.class);

	@Inject
	private UserService userService;

	@Inject
	private ProductService productService;

	@Inject
	private EnterpriseService enterpriseService;

	@Inject
	private OrderService orderService;

	/**
	 * 账户中心-资金账户首页
	 * 
	 * @param user
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月26日 下午5:28:00
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserAcctView> getUserAcctIndex(
			@CurrentUser UserAccount user) {
		UserAcctView userAcctView = new UserAcctView();
		if (log.isDebugEnabled()) {
			log.debug("get UserAcctView 4 personal/index.html of @" + user);
		}
		userAcctView = userService.getUserAcctView(user);
		return new ResponseEntity<>(userAcctView, HttpStatus.OK);
	}

	/**
	 * 获取充值/提现统计值
	 * 
	 * @param user
	 * @param type
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月27日 下午5:38:30
	 */
	@RequestMapping(value = "/transactionLogs/statistics/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<TransactionLogStatistics> getUserAcctTransactionLogStatistics(
			@CurrentUser UserAccount user,
			@PathVariable("type") TransactionType type) {
		Assert.notNull(user, "user account can't be null ");
		if (log.isDebugEnabled()) {
			log.debug("get TransactionLogs statistics 4 UserAcct of @" + user);
		}
		return new ResponseEntity<>(
				userService.getUserAcctTransactionLogStatisticsByType(
						user.getUsername(), type), HttpStatus.OK);
	}

	/**
	 * 获取充值/提现交易流水
	 * 
	 * @param user
	 * @param type
	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月27日 下午6:26:35
	 */
	@RequestMapping(value = "/transactionLogs/{type}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<TransactionLogDetails>> getUserAcctTransactionLog(
			@CurrentUser UserAccount user,
			@PathVariable("type") TransactionType type,
			@PageableDefault(value = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
		Assert.notNull(user, "user account can't be null ");
		if (log.isDebugEnabled()) {
			log.debug("get TransactionLogs 4 UserAcct of @" + user);
		}
		Page<TransactionLogDetails> transactionLogDetailsPage = userService
				.getUserAcctTransactionLogsByType(user.getUsername(), type,
						page);
		return new ResponseEntity<>(transactionLogDetailsPage, HttpStatus.OK);
	}

	/**
	 * 获取用户对配资标的的投标列表
	 * 
	 * @param user

	 * @param page
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年1月27日 下午6:26:35
	 */
	@RequestMapping(value = "/withFundingOrders", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Page<OrderDetails>> getWithFundingOrders(
			@CurrentUser UserAccount user,
			@PageableDefault(value = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
		Assert.notNull(user, "user account can't be null ");
		if (log.isDebugEnabled()) {
			log.debug("get withFundingOrders 4 UserAcct of @" + user);
		}

		Page<OrderDetails> p = null;
		return new ResponseEntity<>(p, HttpStatus.OK);
	}

	/**
	 * 生成配资标的
	 * 
	 * @param user
	 * @param form
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月13日 下午3:02:22
	 */
	@RequestMapping(value = { "/genProductForWithFunding" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProductDetails> genProductForWithFunding(
			@CurrentUser UserAccount user, @RequestBody ProductForm form) {
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");
		if (log.isDebugEnabled()) {
			log.debug("save ProductForm data @" + form);
		}
		ProductDetails productDetails = productService
				.saveProduct4WithFunding(form);
		if (null == productDetails) {
			return new ResponseEntity<>(productDetails, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(productDetails, HttpStatus.CREATED);
	}

	/**
	 * 获取所有有效的企业信息
	 * 
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月15日 下午6:05:58
	 */
	@RequestMapping(value = { "/menu" }, method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<EnterpriseDetails>> getEnterprisesMenu(
			@CurrentUser UserAccount user) {
		Assert.isTrue("admin".equals(user.getName()),
				"You do not have permission to perform this operation.");
		if (log.isDebugEnabled()) {
			log.debug("call getEnterprisesMenu");
		}

		return new ResponseEntity<>(enterpriseService.findEnterpriseDetails(),
				HttpStatus.OK);
	}

	/**
	 * 取消订单
	 * 
	 * @param user
	 * @param id
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年3月11日 下午1:18:40
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.DELETE)
	public ResponseEntity<OrderDetails> cancelOrder(
			@CurrentUser UserAccount user, @PathVariable("id") Long id) {
		Assert.notNull(user, "user account can't be null ");
		if (log.isDebugEnabled()) {
			log.debug("cancel order which id is @" + id + " @ by @" + user);
		}

		orderService.cancelOrderById(id, user);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
