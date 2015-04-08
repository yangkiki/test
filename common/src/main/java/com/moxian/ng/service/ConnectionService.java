/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.service;

import java.time.LocalDateTime;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.ConnectionRequests;
import com.moxian.ng.domain.Connections;
import com.moxian.ng.domain.Group;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.model.GroupDetails;
import com.moxian.ng.model.UserAccountDetails;
import com.moxian.ng.repository.ConnectionsRepository;
import com.moxian.ng.repository.GroupRepository;
import com.moxian.ng.repository.UserRepository;


/**
 * @author Hantsy Bai<hantsy@gmail.com>
 */
@Service
@Transactional
public class ConnectionService {

  public final static Logger log = LoggerFactory.getLogger(ConnectionService.class);

  @Inject
  private UserRepository userRepository;

  @Inject
  private ConnectionsRepository connectionsRepository;

  @Inject
  private GroupRepository groupRepository;



  public Page<UserAccountDetails> findUserAllGroupFriends(Long userId, Pageable page) {

    if (log.isDebugEnabled()) {
      log.debug(" findUserAllGroupFriends begin userId {} , page {} ", userId, page);
    }


    Page<UserAccount> users
        = connectionsRepository.findAllGroupFriends(userId, page);

    if (log.isDebugEnabled()) {
      log.debug("total elements@" + users.getTotalElements());
    }

    return DTOUtils.mapPage(users, UserAccountDetails.class);

  }
  

  public Page<UserAccountDetails> findFriendsByGroupId(Long userId,Long groupId, Pageable page) {

    if (log.isDebugEnabled()) {
      log.debug(" findFriendsByGroupId begin userId {} ,groupId {} , page {} ", userId, groupId, page);
    }


    Page<UserAccount> users
        = connectionsRepository.findFriendsByGroup(userId,groupId, page);

    if (log.isDebugEnabled()) {
      log.debug("total elements@" + users.getTotalElements());
    }

    return DTOUtils.mapPage(users, UserAccountDetails.class);

  }

  public Page<GroupDetails> findUserAllGroups(Long userId, Pageable page){

    if (log.isDebugEnabled()) {
      log.debug(" findUserAllGroups begin userId {} , page {} ", userId, page);
    }


    Page<Group> groups
        = groupRepository.findAllGroups(userId,page);

    if (log.isDebugEnabled()) {
      log.debug("total elements@" + groups.getTotalElements());
    }

    return DTOUtils.mapPage(groups, GroupDetails.class);
  }


  @Transactional
  public <S> void saveConnectionByConnectionRequest(ConnectionRequests connectionRequests) {
	  
	  	final ConnectionRequests  cr = connectionRequests;
	  
	  	Assert.notNull(connectionRequests, "ConnectionRequests id can not be null");
	  	
	  	UserAccount  connectedUser = this.userRepository.findOne(cr.getConnectedUser().getId());
	  	UserAccount  memberUser = this.userRepository.findOne(cr.getMemberUser().getId());
	  	
	  	Connections  connected = new Connections();
	  	connected.setConnectedUser(connectedUser);
	  	connected.setMemberUser(memberUser);
//	  	connected.setGroup(group);
	  	connected.setSource(cr.getSource());
	  	connected.setCreateOn(LocalDateTime.now());
	  	
	    this.connectionsRepository.save(connected);
	  	
	  	Connections  memberUsers = new Connections();
	  	memberUsers.setConnectedUser(memberUser);
	  	memberUsers.setMemberUser(connectedUser);
//	  	memberUser.setGroup(group);
	  	memberUsers.setSource(cr.getSource());
	  	memberUsers.setCreateOn(LocalDateTime.now());
	  
	  	this.connectionsRepository.save(memberUsers);
	  	
		if (log.isDebugEnabled()) {
          log.debug("save connection by id@" + connected.getId()+ " and member by id@" + memberUsers.getId());
	    }
	
  }


}
