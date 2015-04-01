package com.moxian.ng.api.user;

import javax.inject.Inject;

import com.moxian.ng.api.security.CurrentUser;
import com.moxian.ng.domain.IdCardVerification;
import com.moxian.ng.domain.MobileNumberVerification;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.CaptchaMismatchedException;
import com.moxian.ng.exception.InvalidRequestException;
import com.moxian.ng.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.PasswordForm;
import com.moxian.ng.model.ProfileForm;
import com.moxian.ng.model.ResponseMessage;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.model.ApiErrors;
import com.moxian.ng.model.IdCardForm;
import com.moxian.ng.model.LongValue;
import com.moxian.ng.model.MessageDetails;
import com.moxian.ng.model.MobileNumberForm;
import com.moxian.ng.model.OrderDetails;
import com.moxian.ng.model.OrderSearchCriteria;
import com.moxian.ng.model.UpdateMobileNumberForm;
import com.moxian.ng.service.SmsService;
import com.moxian.ng.service.UserService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = ApiConstants.URI_API + ApiConstants.URI_CURRENT_USER)
public class CurrentUserController {
    
    private static final Logger log = LoggerFactory.getLogger(CurrentUserController.class);
    
    @Inject
    private PasswordEncoder passwordEncoder;
    
    @Inject
    private UserService userService;
    
    @Inject
    private UserRepository userRepository;
    
    @Inject
    private SmsService smsService;
    
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public UserAccountDetails currentUser(@CurrentUser UserAccount user) {
        if (log.isDebugEnabled()) {
            log.debug("get current user info@" + user);
        }
        
        UserAccountDetails info = userService.findUserByUsername(user.getUsername());
        
        return info;
    }
    
    @RequestMapping(method = RequestMethod.PUT, params = "action=CHANGE_PWD")
    @ResponseBody
    public ResponseEntity<ResponseMessage> changePassword(@RequestBody @Valid PasswordForm fm, BindingResult errors, @CurrentUser UserAccount u) {
        if (log.isDebugEnabled()) {
            log.debug("change password of user@" + fm);
        }
        
        if (errors.hasErrors()) {
            throw new InvalidRequestException(ApiErrors.INVALID_PASSWORD, errors);
        }
        
        UserAccount user = userRepository.findByUsername(u.getUsername());
        
        if (!passwordEncoder.matches(fm.getOldPassword(), user.getPassword())) {
            return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.danger, "currentPasswordIsWrong"),
                    HttpStatus.BAD_REQUEST);
        }
        
        user.setPassword(passwordEncoder.encode(fm.getNewPassword()));
        userRepository.save(user);
        
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.success, "passwordUpdated"),
                HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(method = RequestMethod.PUT, params = "action=UPDATE_PROFILE")
    @ResponseBody
    public ResponseEntity<ResponseMessage> updateProfile(@RequestBody ProfileForm u, @CurrentUser UserAccount su) {
        if (log.isDebugEnabled()) {
            log.debug("update user profile data @" + u);
        }
        
        UserAccount user = userRepository.findByUsername(su.getUsername());
        
        BeanUtils.copyProperties(u, user, "username", "password", "role");
        userRepository.save(user);
        
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.success, "profileUpdated"),
                HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(method = RequestMethod.PUT, params = "action=UPDATE_MOBILE_NUMBER")
    @ResponseBody
    public ResponseEntity<Void> updateMobileNumber(@RequestBody @Valid UpdateMobileNumberForm u, BindingResult errors, @CurrentUser UserAccount su) {
        if (log.isDebugEnabled()) {
            log.debug("update user mobilenumber data @" + u);
        }
        
        if (errors.hasErrors()) {
            throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
        }
        
        if (!smsService.validate(u.getNewMobileNumber(), u.getNewSmsCode(), true)
        		|| !smsService.validate(u.getMobileNumber(), u.getSmsCode(), true)) {
            throw new CaptchaMismatchedException();
        }
        
        UserAccount user = userRepository.findByUsername(su.getUsername());
        
        user.setMobileNumber(u.getNewMobileNumber());
        user.setMobileNumberVerification(new MobileNumberVerification(u.getNewMobileNumber()));
        userRepository.save(user);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(method = RequestMethod.PUT, params = "action=VERIFY_MOBILE_NUMBER")
    @ResponseBody
    public ResponseEntity<ResponseMessage> verifyMobileNumber(@RequestBody @Valid MobileNumberForm u, BindingResult errors, @CurrentUser UserAccount su) {
        if (log.isDebugEnabled()) {
            log.debug("update user mobilenumber data @" + u);
        }
        
        if (errors.hasErrors()) {
            throw new InvalidRequestException(ApiErrors.INVALID_REQUEST, errors);
        }
        
        if (!smsService.validate(u.getMobileNumber(), u.getSmsCode(), true)) {
            throw new CaptchaMismatchedException();
        }
        
        UserAccount user = userRepository.findByUsername(su.getUsername());
        
        user.setMobileNumber(u.getMobileNumber());
        MobileNumberVerification verification = new MobileNumberVerification(u.getMobileNumber());
        user.setMobileNumberVerification(verification);
        
        userRepository.save(user);
        
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.success, "mobileNumberUpdated"),
                HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(method = RequestMethod.PUT, params = "action=VERIFY_ID_CARD")
    @ResponseBody
    public ResponseEntity<ResponseMessage> verifyIdCard(@RequestBody IdCardForm u, @CurrentUser UserAccount su) {
        if (log.isDebugEnabled()) {
            log.debug("update ID CARD data @" + u);
        }
        
        UserAccount user = userRepository.findByUsername(su.getUsername());
        
        IdCardVerification verification = new IdCardVerification(u.getCardName(), u.getCardNumber());
        user.setIdCardVerification(verification);
        userRepository.save(user);
        
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.success, "idCardUpdated"),
                HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "messages", method = RequestMethod.GET, params = "f=RECEIVED")
    @ResponseBody
    public ResponseEntity<Page<MessageDetails>> getReceivedMessagss(//
            @CurrentUser UserAccount user, //
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get received messages of @" + user);
        }
        
        Page<MessageDetails> messages = userService.getReceivedMessages(user.getId(), null, page);
        
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    
    @RequestMapping(value = "messages", method = RequestMethod.GET, params = "f=SENT")
    @ResponseBody
    public ResponseEntity<Page<MessageDetails>> getSentMessagss(//
            @CurrentUser UserAccount user, //
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get received messages of @" + user);
        }
        
        Page<MessageDetails> messages = userService.getSentMessages(user.getId(), page);
        
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
    
    @RequestMapping(value = "messages/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MessageDetails> getMessage(
            @PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("getMessage of @" + id);
        }
        
        MessageDetails msg = userService.getMessage(id);
        
        return new ResponseEntity<>(msg, HttpStatus.OK);
    }
    
    @RequestMapping(value = "messages/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteMessage(
            @PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("deleteMessage @" + id);
        }
        
        userService.deleteMessage(id);
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "messages", method = RequestMethod.PUT, params = "MARK_READ")
    @ResponseBody
    public ResponseEntity<Void> markAllMessagesRead(//
            @CurrentUser UserAccount user) {
        if (log.isDebugEnabled()) {
            log.debug("mark unread messages as read of @" + user);
        }
        
        userService.markAllMessagesAsRead(user.getId());
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = "messages/count", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<LongValue> getUnreadReceivedMessagss(//
            @CurrentUser UserAccount user) {
        if (log.isDebugEnabled()) {
            log.debug("get received messages of @" + user);
        }
        
        long cnt = userService.countUnreadMessages(user.getId());
        
        return new ResponseEntity<>(new LongValue(cnt), HttpStatus.OK);
    }
   
}
