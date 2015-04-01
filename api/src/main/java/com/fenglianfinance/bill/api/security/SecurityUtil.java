package com.fenglianfinance.bill.api.security;

import com.fenglianfinance.bill.domain.UserAccount;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    public static UserAccount currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }

        if (auth instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (UserAccount) auth.getPrincipal();
    }
}
