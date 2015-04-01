package com.moxian.ng.api.security;

import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.repository.UserRepository;
import org.springframework.data.domain.AuditorAware;

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
