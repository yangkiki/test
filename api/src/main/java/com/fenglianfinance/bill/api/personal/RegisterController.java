package com.fenglianfinance.bill.api.personal;

import javax.inject.Inject;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.exception.InvalidRequestException;
import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.ApiErrors;
import com.fenglianfinance.bill.model.RegisterForm;
import com.fenglianfinance.bill.model.UserAccountDetails;
import com.fenglianfinance.bill.service.UserService;

/**
 * @author wangli@flf77.com
 * @date 2015年2月4日 下午4:20:03
 */
@RequestMapping(value = ApiConstants.URI_API_PUBLIC)
@RestController
public class RegisterController {

	private static final Logger log = LoggerFactory
			.getLogger(RegisterController.class);

	@Inject
	private UserService userService;

	/**
	 * 用户注册（website）
	 * 
	 * @param form
	 * @param errors
	 * @return
	 * @author wangli@flf77.com
	 * @date 2015年2月4日 下午4:20:12
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserAccountDetails> register(
			@RequestBody @Valid RegisterForm form, BindingResult errors) {
		if (log.isDebugEnabled()) {
			log.debug("register data@" + form);
		}

		if (errors.hasErrors()) {
			throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
		}

		return new ResponseEntity<>(userService.registerUser(form),
				HttpStatus.CREATED);
	}
}
