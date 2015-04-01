package com.fenglianfinance.bill.api.user;

import com.fenglianfinance.bill.domain.UserAccount;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.AuthenticationRequest;
import com.fenglianfinance.bill.model.AuthenticationToken;
import javax.inject.Inject;
import org.springframework.security.access.AccessDeniedException;

@RestController
@RequestMapping(value = ApiConstants.URI_API)
public class AuthenticationController {

    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private UserDetailsService userDetailsService;

    public AuthenticationController() {
    }

    @RequestMapping(value = "/authenticate", method = {RequestMethod.POST})
    public AuthenticationToken authorize(@RequestBody AuthenticationRequest authenticationRequest,
            HttpServletRequest request) {

        final UsernamePasswordAuthenticationToken token
                = new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword());

        final UserAccount details = (UserAccount) this.userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        if (details.hasUserRole()) {
            throw new AccessDeniedException("");
        }

        final Authentication authentication = this.authenticationManager.authenticate(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final HttpSession session = request.getSession(true);

        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext());

        final Map<String, Boolean> roles = new HashMap<>();

        details.getAuthorities().stream().forEach((authority) -> {
            roles.put(authority.toString(), Boolean.TRUE);
        });

        return new AuthenticationToken(details.getUsername(), roles, session.getId());
    }
}
