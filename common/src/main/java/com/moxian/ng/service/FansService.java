package com.moxian.ng.service;

import java.time.LocalDateTime;
import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.Connections;
import com.moxian.ng.domain.Fans;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.FansDetails;
import com.moxian.ng.model.FansForm;
import com.moxian.ng.repository.ConnectionsRepository;
import com.moxian.ng.repository.FansRepository;
//import com.moxian.ng.repository.FansSpecifications;
import com.moxian.ng.repository.UserRepository;

@Service
public class FansService {

	private static final Logger log = LoggerFactory
			.getLogger(FansService.class);

	@Inject
	private FansRepository fansRepository;

	@Inject
	private ConnectionsRepository connectionsRepository;

	@Inject
	private UserRepository userRepository;

	@Transactional
	public FansDetails savefans(FansForm form) {

		UserAccount followingUser = this.userRepository.findOne(form
				.getFollowingUserId());
		UserAccount memberUser = this.userRepository.findOne(form
				.getMemberUserId());

		if (log.isDebugEnabled()) {
			log.debug("save fans @" + form);
		}
		Fans fans = DTOUtils.map(form, Fans.class);
		// fans.setSend(connectedUser);
		// fans.setRecept(memberUser);
		fans.setFollowingUser(followingUser);
		fans.setMemberUser(memberUser);

		Fans fansOld = this.findFansBySendAndRecept(form.getFollowingUserId(),
				form.getMemberUserId());

		Fans saved = null;

		if (fansOld == null) {
			saved = this.fansRepository.save(fans);
		} else {
			saved = fansOld;
			saved.setActive(true);
		}

		Connections send = this.connectionsRepository
				.findConnectionByConnectedUserAndMemberUser(memberUser.getId(),
						followingUser.getId());

		if (send == null) {
			send = new Connections();
			send.setConnectedUser(memberUser);
			send.setMemberUser(followingUser);
			send.setCreateOn(LocalDateTime.now());
			send.setType(Connections.Type.UNIDIRECTIONAL);
			this.connectionsRepository.save(send);
		}

		Connections recept = this.connectionsRepository
				.findConnectionByConnectedUserAndMemberUser(
						followingUser.getId(), memberUser.getId());

		if (recept != null) {
			send.setType(Connections.Type.BILATERAL);
			recept.setType(Connections.Type.BILATERAL);
			this.connectionsRepository.save(recept);
			this.connectionsRepository.save(send);
		}

		if (log.isDebugEnabled()) {
			log.debug("saved fans id is @" + saved);
		}
		return DTOUtils.map(saved, FansDetails.class);

	}

	@Transactional
	public void deactivateFans(Long id) {

		Assert.notNull(id, "fans id can not be null");

		if (log.isDebugEnabled()) {
			log.debug("delete post by id @" + id);
		}

		Fans fans = this.fansRepository.findOne(id);

		if (fans == null) {
			throw new ResourceNotFoundException(id);
		}

		UserAccount connectedUser = this.userRepository.findOne(fans
				.getFollowingUser().getId());
		UserAccount memberUser = this.userRepository.findOne(fans
				.getMemberUser().getId());

		Connections send = this.connectionsRepository
				.findConnectionByConnectedUserAndMemberUser(memberUser.getId(),
						connectedUser.getId());

		if (send != null) {
			// send.setType(Connections.Type.UNIDIRECTIONAL);
			// this.connectionsRepository.save(send);
			this.connectionsRepository.delete(send);
		}

		Connections recept = this.connectionsRepository
				.findConnectionByConnectedUserAndMemberUser(
						connectedUser.getId(), memberUser.getId());

		if (recept != null) {
			recept.setType(Connections.Type.UNIDIRECTIONAL);
			this.connectionsRepository.save(recept);
		}

		fans.setActive(false);
	}

	// @Transactional
	// public void updatefans(Long id, FansForm form) {
	// Assert.notNull(id, "fans id can not be null");
	//
	// if (log.isDebugEnabled()) {
	// log.debug("update fans @" + form);
	// }
	//
	// Fans fans = this.fansRepository.findOne(id);
	// DTOUtils.mapTo(form, fans);
	//
	// Fans saved = this.fansRepository.save(fans);
	//
	// if (log.isDebugEnabled()) {
	// log.debug("updated fans@" + saved);
	// }
	// }

	// public Page<FansDetails> findFansBySendAndRecept(Long sendId,
	// Long receptId, Pageable page) {
	// Assert.notNull(sendId, "sendId id can not be null");
	// Assert.notNull(receptId, "receptId id can not be null");
	//
	// if (log.isDebugEnabled()) {
	// log.debug("find fans by sendId@" + sendId);
	// log.debug("find fans by receptId@" + receptId);
	// }
	//
	// Page<Fans> result = this.fansRepository.findAll(
	// FansSpecifications.filterFansBySendAndRecept(sendId, receptId),
	// page);
	//
	// if (result == null) {
	// throw new ResourceNotFoundException(sendId);
	// }
	//
	// if (log.isDebugEnabled()) {
	// log.debug("get result size @" + result.getTotalElements());
	// }
	//
	// return DTOUtils.mapPage(result, FansDetails.class);
	// }

	private Fans findFansBySendAndRecept(Long followingUserId, Long memberUserId) {

		Assert.notNull(followingUserId, "send can not be null");
		Assert.notNull(memberUserId, "recept can not be null");

		if (log.isDebugEnabled()) {
			log.debug("find fans by send@" + followingUserId);
			log.debug("find fans by recept@" + memberUserId);
		}

		return this.fansRepository.findFansBySendAndRecept(followingUserId,
				memberUserId);

	}

	public FansDetails findfansById(Long id) {
		Assert.notNull(id, "Fans id can not be null");

		if (log.isDebugEnabled()) {
			log.debug("find fans by id@" + id);
		}

		Fans Fans = this.fansRepository.findOne(id);

		if (Fans == null) {
			throw new ResourceNotFoundException(id);
		}

		return DTOUtils.map(Fans, FansDetails.class);
	}
	//
	// @Transactional
	// public void updateReceipt(Long id, boolean action) {
	// Assert.notNull(id, "Fans id can not be null");
	//
	// if (log.isDebugEnabled()) {
	// log.debug("find fans by id@" + id);
	// }
	//
	// Fans Fans = this.fansRepository.findOne(id);
	//
	// if (Fans == null) {
	// throw new ResourceNotFoundException(id);
	// }
	//
	// // this.fansRepository.save(Fans);
	// }

}
