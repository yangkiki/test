package com.moxian.ng.api.user;

import javax.inject.Inject;

import com.moxian.ng.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.BankCardInfoDetails;
import com.moxian.ng.model.OrderDetails;
import com.moxian.ng.model.OrderSearchCriteria;
import com.moxian.ng.model.OrderStatistics;
import com.moxian.ng.model.SystemUserForm;
import com.moxian.ng.model.TransactionLogDetails;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.model.UserProfileDetails;
import com.moxian.ng.model.UserSearchCriteria;
import com.moxian.ng.service.UserService;
import java.util.List;
import org.springframework.data.domain.Sort;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_USERS)
public class UserMgtController {

    private static final Logger log = LoggerFactory.getLogger(UserMgtController.class);

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserService userService;

    @Inject
    private OrderService orderService;

//    @RequestMapping(value = {""}, method = RequestMethod.GET)
//    @ResponseBody
//    public Page<UserAccountDetails> allUsers(
//            @RequestParam("q") String q, //
//            @RequestParam("locked") String locked,//
//            @RequestParam("active") String active, //
//            @PageableDefault(value = 10) Pageable page) {
//        if (log.isDebugEnabled()) {
//            log.debug("fetch all users @ q@" + q + ", locked@" + locked + ", page@" + page);
//        }
//
//        Page<UserAccountDetails> users = userService.findUserAccounts(q, Role.ROLE_USER, active, locked, page);
//
//        if (log.isDebugEnabled()) {
//            log.debug("count of users @" + users.getTotalElements());
//        }
//
//        return users;
//    }
    @RequestMapping(value = {"/search"}, method = RequestMethod.POST)
    @ResponseBody
    public Page<UserAccountDetails> searchUsers(
            @RequestBody UserSearchCriteria criteria,
            @PageableDefault(value = 10) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("user search criteria  @ " + criteria + ", page@" + page);
        }

        Page<UserAccountDetails> users = userService.findUserAccounts(criteria, page);

        if (log.isDebugEnabled()) {
            log.debug("count of users @" + users.getTotalElements());
        }

        return users;
    }

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createUser(@RequestBody SystemUserForm form, UriComponentsBuilder uriComponentsBuilder) {
        if (log.isDebugEnabled()) {
            log.debug("save user data @" + form);
        }

        UserAccountDetails saved = userService.saveUser(form);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_USERS + "/{id}")
                .buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

//    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT)
//    @ResponseBody
//    public ResponseEntity<Void> updateUser(@PathVariable("id") Long id, @RequestBody SystemUserForm form) {
//        if (log.isDebugEnabled()) {
//            log.debug("udpate user @" + id + " data @" + form);
//        }
//
//        userService.updateUser(id, form);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserAccountDetails> getUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get user data by id @" + id);
        }

        UserAccountDetails user = userService.findUserAccountById(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}/profile"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserProfileDetails> getUserProfile(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get user data by id @" + id);
        }

        UserProfileDetails user = userService.findUserProfileByUserId(id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

//    @RequestMapping(value = {"/{id}/accountingInfo"}, method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<AccountingInfoDetails> accountingInfo(@PathVariable("id") Long id) {
//
//        if (log.isDebugEnabled()) {
//            log.debug("accounting info @" + id);
//        }
//
//        AccountingInfoDetails user = userService.findAccountingInfoyUserId(id);
//
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
    @RequestMapping(value = {"/{id}/cards"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<BankCardInfoDetails>> boundCards(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("accounting info @" + id);
        }

        List<BankCardInfoDetails> cards = userService.findBoundCardsByUserId(id);

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}/logs"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<TransactionLogDetails>> transactionLogs(
            @PathVariable("id") Long id,
            @PageableDefault(value = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable page
    ) {

        if (log.isDebugEnabled()) {
            log.debug("get transaction logs of@" + id);
        }

        Page<TransactionLogDetails> logs = userService.findTransactionLogsByUserId(id, page);

        return new ResponseEntity<>(logs, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}/orders"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<OrderDetails>> getUserOrders(//
            @PathVariable("id") Long userId,
            @RequestParam("type") String type,
            @RequestParam("status") String status,
            @PageableDefault(page = 0, size = 10, sort = {"placedDate"}, direction = Sort.Direction.DESC) Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("get all orders by  search@" + userId
                    + ", type=" + type
                    + ", status =" + status
                    + ", page @" + page
            );
        }

        OrderSearchCriteria criteria = new OrderSearchCriteria();
        criteria.setType(type);
        criteria.setStatus(status);

        Page<OrderDetails> result = orderService.findOrders(criteria, userId, page);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("soft delete user @" + id + ", deactivate user not delete it");
        }

        userService.deactivateUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT, params = {"action=UNLOCK"})
    @ResponseBody
    public ResponseEntity<Void> unlockUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("unlock user @" + id + "");
        }
        userService.unlockUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT, params = {"action=LOCK"})
    @ResponseBody
    public ResponseEntity<Void> lockUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("lock user @" + id + "");
        }
        userService.lockUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT, params = {"action=ACTIVATE"})
    @ResponseBody
    public ResponseEntity<Void> activateUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("activate user @" + id + "");
        }
        userService.activateUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"/{id}"}, method = RequestMethod.PUT, params = {"action=DEACTIVATE"})
    @ResponseBody
    public ResponseEntity<Void> deactivateUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("deactivate user @" + id + "");
        }
        userService.deactivateUser(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = {"{id}/stat"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<OrderStatistics> statistics(
            @PathVariable("id") Long id
    ) {

        if (log.isDebugEnabled()) {
            log.debug("get order statistics @" + id);
        }

        OrderStatistics result = userService.calculateOrderStatistics(id);

        if (log.isDebugEnabled()) {
            log.debug("order statistics @" + result);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
