package com.moxian.ng.api.security.xauth;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.moxian.ng.model.AuthenticationRequest;
import com.moxian.ng.model.AuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wordnik.swagger.annotations.ApiOperation;

//@RestController
//@RequestMapping(value = ApiConstants.URI_API, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserXAuthTokenController {

	private static final Logger log = LoggerFactory
			.getLogger(UserXAuthTokenController.class);

	private final TokenUtils tokenUtils = new TokenUtils();
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;

	@Autowired
	public UserXAuthTokenController(AuthenticationManager am,
			UserDetailsService userDetailsService) {
		this.authenticationManager = am;
		this.userDetailsService = userDetailsService;
	}

	@RequestMapping(value = "authenticate", method = { RequestMethod.POST })
	@ApiOperation(value = "authenticate")
	public AuthenticationToken authorize(@Valid @RequestBody AuthenticationRequest form) {

		String username = form.getUsername();
		String password = form.getPassword();

		if (log.isDebugEnabled()) {
			log.debug("login as@" + username + "/" + password);
		}

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication authentication = this.authenticationManager
				.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetails details = this.userDetailsService
				.loadUserByUsername(username);

		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : details.getAuthorities()) {
			roles.put(authority.toString(), Boolean.TRUE);
		}

		AuthenticationToken user = new AuthenticationToken(details.getUsername(), roles,
				tokenUtils.createToken(details));

		if (log.isDebugEnabled()) {
			log.debug("logged in successfully@" + user);
		}

		return user;
	}
}
