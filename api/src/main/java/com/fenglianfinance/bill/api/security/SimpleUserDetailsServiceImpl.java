package com.fenglianfinance.bill.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fenglianfinance.bill.domain.UserAccount;
import com.fenglianfinance.bill.repository.GrantedPermissionRepository;
import com.fenglianfinance.bill.repository.PermissionRepository;
import com.fenglianfinance.bill.repository.UserRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

//@Named
public class SimpleUserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(SimpleUserDetailsServiceImpl.class);

    // @Inject
    private UserRepository userRepository;

    private PermissionRepository permissionRepository;

    private GrantedPermissionRepository grantedPermissionRepository;

    public SimpleUserDetailsServiceImpl(UserRepository userRepository,
            PermissionRepository permissionRepository,
            GrantedPermissionRepository grantedPermissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
        this.grantedPermissionRepository = grantedPermissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (log.isDebugEnabled()) {
            log.debug("load user by username @" + username + "");
        }

        UserAccount user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("username not found:" + username);
        }

        Set<GrantedAuthority> auths = new HashSet<>();

        for (String role : user.getRoles()) {
            if ("USER".equals(role)) {
                SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role);
                auths.add(grantedAuthority);
            } else if ("ADMIN".equals(role)) {
                List<String> names = permissionRepository.findAllActivePermissionNames();

                for (String name : names) {
                    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(name);
                    auths.add(grantedAuthority);
                }
            } else {
                List<String> names = grantedPermissionRepository.findGrantedPermisionNamesByRoleName(role);

                for (String name : names) {
                    SimpleGrantedAuthority grantedAuthority = new SimpleGrantedAuthority(name);
                    auths.add(grantedAuthority);
                }
            }

        }

        user.setAuthorities(auths);

        return user;
    }
}
