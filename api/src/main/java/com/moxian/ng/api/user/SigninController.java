package com.moxian.ng.api.user;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.InvalidRequestException;
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
import org.springframework.web.bind.annotation.RestController;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ApiErrors;
import com.moxian.ng.model.AuthenticationToken;
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

    public SigninController() {
    }

    @RequestMapping(value = "/signin", method = {RequestMethod.POST})
    public AuthenticationToken signin(
        @RequestBody @Valid SigninForm authenticationRequest,
        BindingResult errors, HttpServletRequest request) {

        if (log.isDebugEnabled()) {
            log.debug("signin form  data@" + authenticationRequest);
        }

        if (errors.hasErrors()) {
            throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
        }

        final UserAccount details = (UserAccount) this.userDetailsService
            .loadUserByUsername(authenticationRequest.getUsername());

        if (!details.hasUserRole()) {
            throw new AccessDeniedException("");
        }

        final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
            authenticationRequest.getUsername(),
            authenticationRequest.getPassword()
        );

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
