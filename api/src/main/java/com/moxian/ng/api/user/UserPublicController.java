package com.moxian.ng.api.user;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.SystemUserDetails;
import com.moxian.ng.service.UserService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_USERS)
public class UserPublicController {

    private static final Logger log = LoggerFactory.getLogger(UserPublicController.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserService userService;

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SystemUserDetails> getUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get user data by id @" + id);
        }

        SystemUserDetails user = userService.findUserById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
