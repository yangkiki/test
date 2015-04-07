package com.moxian.ng.api.user;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.service.ConnectionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

  @RequestMapping(value = {""}, method = RequestMethod.GET)
  @ResponseBody
  public Page<UserAccountDetails> getDefaultFriends(

      @PageableDefault(value = 10) Pageable page) {
    if (log.isDebugEnabled()) {
      log.debug("user search criteria   page@ {} ", page);
    }

    Page<UserAccountDetails> users = connectionService.findDefaultGroupFriends(1L,page);

    if (log.isDebugEnabled()) {
      log.debug("count of users @" + users.getTotalElements());
    }

    return users;
  }


}
