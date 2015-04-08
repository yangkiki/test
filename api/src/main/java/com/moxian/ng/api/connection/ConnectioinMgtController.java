package com.moxian.ng.api.connection;

import com.moxian.ng.api.security.CurrentUser;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.GroupDetails;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.service.ConnectionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_API_CONNECTION)
public class ConnectioinMgtController {

  private static final Logger log = LoggerFactory.getLogger(ConnectioinMgtController.class);


  @Inject
  private ConnectionService connectionService;

  @RequestMapping(value = {"/friends"}, method = RequestMethod.GET)
  @ResponseBody
  public Page<UserAccountDetails> getAllFriends(
      @CurrentUser UserAccount user,
      @PageableDefault(value = 10) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search criteria   page@ {} ", page);
    }

    Page<UserAccountDetails> users = connectionService.findUserAllGroupFriends(user.getId(), page);

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

    Page<GroupDetails> groups = connectionService.findUserAllGroups(user.getId(),page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + groups.getTotalElements());
    }

    return groups;
  }


}
