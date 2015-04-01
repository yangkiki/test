package com.moxian.ng.api.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.moxian.ng.cache.service.CacheService;
import com.moxian.ng.captcha.KaptchaDelegate;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.CaptchaMismatchedException;
import com.moxian.ng.exception.InvalidRequestException;
import com.moxian.ng.service.SmsService;
import com.moxian.ng.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moxian.ng.captcha.CaptchaService;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ApiErrors;
import com.moxian.ng.model.AuthenticationToken;
import com.moxian.ng.model.RegisterForm;
import com.moxian.ng.model.SigninForm;

@RestController
@RequestMapping(value = ApiConstants.URI_API)
public class SigninController {

	private static final Logger log = LoggerFactory
			.getLogger(SigninController.class);

	@Inject
	private AuthenticationManager authenticationManager;

	@Inject
	private UserDetailsService userDetailsService;
	
	@Inject
	private UserService userService;

	@Inject
	private CaptchaService captchaService;
	
	@Inject
    private SmsService smsService;
	
	@Inject
    private CacheService cacheService;

	public SigninController() {
	}

	@RequestMapping(value = "/signin", method = { RequestMethod.POST })
	public AuthenticationToken signin(
			@RequestBody @Valid SigninForm authenticationRequest,
			BindingResult errors, HttpServletRequest request) {

		// website 前台登录检查验证码
		return signinCommon(authenticationRequest, true, errors, request);
	}

	/**
	 * 用户注册（website）
	 * 
	 * @param form
	 * @param errors
	 * @return
	 * @author ourh@flf77.com
	 * @date 2015年3月10日 下午3:20:12
	 */
	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	@ResponseBody
	public AuthenticationToken register(@RequestBody @Valid RegisterForm form,
			BindingResult errors, HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("register data@" + form);
		}

		if (errors.hasErrors()) {
			throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
		}
		
		if (!smsService.validate(form.getMobileNumber(), form.getSmsCode(), true)) {
			throw new CaptchaMismatchedException();
		}

		userService.registerUser(form);

		SigninForm signinForm = new SigninForm();
		signinForm.setUsername(form.getMobileNumber());
		signinForm.setPassword(form.getPassword());

		// 注册后自动登录，不检查验证码
		return signinCommon(signinForm, false, errors, request);

	}

	/**
	 * 登录公共
	 * @param authenticationRequest
	 * @param verfyImgCaptcha 是否校验图片验证码
	 * @param errors
	 * @param request
	 * @return
	 */
	private AuthenticationToken signinCommon(
			@RequestBody @Valid SigninForm authenticationRequest,
			boolean verfyImgCaptcha, BindingResult errors,
			HttpServletRequest request) {
		if (log.isDebugEnabled()) {
			log.debug("signin form  data@" + authenticationRequest);
		}

		if (errors.hasErrors()) {
			throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
		}

		if (verfyImgCaptcha
				&& (authenticationRequest.getCaptchaResponse() == null || !captchaService
						.verifyImgCaptcha(authenticationRequest
								.getCaptchaResponse()))) {
			throw new CaptchaMismatchedException();
		}
		// 验证通过，清除redis中该验证码的缓存
		if (verfyImgCaptcha) {
			cacheService.delete(KaptchaDelegate.CACHE_KEY_PREFIX
					+ authenticationRequest.getCaptchaResponse().getKey());
		}
		
		final UserAccount details = (UserAccount) this.userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		if (!details.hasUserRole()) {
			throw new AccessDeniedException("");
		}

		final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(),
				authenticationRequest.getPassword());

		final Authentication authentication = this.authenticationManager
				.authenticate(token);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		final HttpSession session = request.getSession(true);

		session.setAttribute(
				HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
				SecurityContextHolder.getContext());

		final Map<String, Boolean> roles = new HashMap<>();

		details.getAuthorities().stream().forEach((authority) -> {
			roles.put(authority.toString(), Boolean.TRUE);
		});

		return new AuthenticationToken(details.getUsername(), roles,
				session.getId());
	}

}
