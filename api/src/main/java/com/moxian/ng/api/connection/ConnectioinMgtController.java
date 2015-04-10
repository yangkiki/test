package com.moxian.ng.api.connection;

import com.moxian.ng.api.security.CurrentUser;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ErrorCode;
import com.moxian.ng.model.GroupDetails;
import com.moxian.ng.model.GroupForm;
import com.moxian.ng.model.ListResponse;
import com.moxian.ng.model.SingleResponse;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.service.ConnectionService;
import com.moxian.ng.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_API_CONNECTION)
public class ConnectioinMgtController {

  private static final Logger log = LoggerFactory.getLogger(ConnectioinMgtController.class);


  @Inject
  private ConnectionService connectionService;

  @Inject
  private UserService userService;

  @RequestMapping(value = {"/friends"}, method = RequestMethod.GET)
  @ResponseBody
  public ListResponse<UserAccountDetails> getAllFriends(
      @CurrentUser UserAccount user,
      @PageableDefault(value = 2) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search criteria   page@ {} ", page);
    }
    Page<UserAccountDetails> users = null;

    users = connectionService.findUserAllGroupFriends(user.getId(), page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + users.getTotalElements());
    }

    ListResponse<UserAccountDetails> response = ListResponse.successRsp();
    response.setCode(ErrorCode.SUCCESS);
    response.setData(users.getContent());
    response.setTotalCount(users.getTotalElements());

    return response;
  }

  @RequestMapping(value = {"/friends"}, method = RequestMethod.GET, params = {"action=NOTINGROUP"})
  @ResponseBody
  public Page<UserAccountDetails> getNotGroupFriends(
      @CurrentUser UserAccount user,
      @PageableDefault(value = 10) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search criteria   page@ {} ", page);
    }

    Page<UserAccountDetails> users = connectionService.findNotGroupFriends(user.getId(), page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + users.getTotalElements());
    }

    return users;
  }


  @RequestMapping(value = {"/friends/{id}"}, method = RequestMethod.GET)
  @ResponseBody
  public Page<UserAccountDetails> getFriendsByGroupId(
      @CurrentUser UserAccount user,
      @PathVariable("id") Long groupId,
      @PageableDefault(value = 10) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search criteria   page@ {} ", page);
    }

    Page<UserAccountDetails> users = connectionService.findFriendsByGroupId(user.getId(), groupId, page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + users.getTotalElements());
    }

    return users;
  }

  @RequestMapping(value = {"/groups"}, method = RequestMethod.GET)
  @ResponseBody
  public Page<GroupDetails> getAllGroups(
      @CurrentUser UserAccount user,
      @PageableDefault(value = 10) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search criteria   page@ {} ", page);
    }

    Page<GroupDetails> groups = connectionService.findUserAllGroups(user.getId(), page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + groups.getTotalElements());
    }

    return groups;
  }

  @RequestMapping(value = {"/groups/{id}",}, method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<GroupDetails> getBill(@PathVariable("id") Long id) {

    log.debug("get group data by id @ {}", id);

    GroupDetails bill = connectionService.findGroupById(id);

    return new ResponseEntity<>(bill, HttpStatus.OK);
  }

  @RequestMapping(value = {"/groups"}, method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<Void> createGroup(@RequestBody GroupForm form, UriComponentsBuilder uriComponentsBuilder) {
    log.debug("save GroupForm data @ {}", form);

    GroupDetails saved = connectionService.saveGroup(form);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
        uriComponentsBuilder.path(ApiConstants.URI_API_MGT + ApiConstants.URI_API_CONNECTION + "/groups/{id}")
            .buildAndExpand(saved.getId()).toUri());

    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(value = {"/groups/{id}"}, method = RequestMethod.PUT, params = {"action=DELETE"})
  @ResponseBody
  public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id) {

    log.debug("delete group id {} @", id);

    connectionService.deleteGroup(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = {"/groups/{id}"}, method = RequestMethod.PUT, params = {"action=ADD"})
  @ResponseBody
  public ResponseEntity<SingleResponse<Void>> addFriendToGroup(@PathVariable("id") Long groupId,  //
                                               @CurrentUser UserAccount user,//
                                               @RequestBody Long[] friends) {
    if (log.isDebugEnabled()) {
      log.debug(" user {} add friend length {}  friend  {}  to group {}", user.getId(), friends.length, friends,
                groupId);
    }

    connectionService.addFriendToGroup(user.getId(), groupId, friends);

    SingleResponse<Void> response=SingleResponse.successRsp();

    return new ResponseEntity<>(response,HttpStatus.CREATED);
  }

  @RequestMapping(value = {"/groups/{id}"}, method = RequestMethod.DELETE)
  @ResponseBody
  public ResponseEntity<Void> removeFriendFromGroup(@PathVariable("id") Long groupId,  //
                                                    @CurrentUser UserAccount user,//
                                                    @RequestBody Long[] friends) {
    if (log.isDebugEnabled()) {
      log.debug(" user {} remove friend length {}  friend  {}  from group {}", user.getId(), friends.length,
                friends, groupId);
    }

    connectionService.removeFriendFromGroup(user.getId(), friends);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }


  @RequestMapping(value = {"/search"}, method = RequestMethod.GET)
  @ResponseBody
  public Page<UserAccountDetails> getUserAccountByKeyword(
      @RequestParam("keyword") String keyword,
      @PageableDefault(value = 10) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search keyword  {} page@ {} ", keyword, page);
    }
    Page<UserAccountDetails> users = null;

    users = userService.findUserAccountByKeyword(keyword, page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + users.getTotalElements());
    }

    return users;
  }


}
