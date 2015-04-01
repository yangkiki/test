package com.fenglianfinance.bill.api.security;

import javax.inject.Named;

import org.springframework.data.domain.AuditorAware;

import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.repository.UserRepository;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Named
public class Auditor implements AuditorAware<UserAccount> {

    public static final Logger log = LoggerFactory.getLogger(Auditor.class);

    @Inject
    UserRepository userRepository;

    @Override
    public UserAccount getCurrentAuditor() {
        UserAccount secUser = SecurityUtil.currentUser();

        if (secUser != null) {
            if (log.isDebugEnabled()) {
                log.debug("auditor details.@" + secUser);
            }
        }
        return secUser;
    }
}
