package com.moxian.ng.service;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.Connections;
import com.moxian.ng.domain.Fans;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.FansDetails;
import com.moxian.ng.model.FansForm;
import com.moxian.ng.repository.ConnectionsRepository;
import com.moxian.ng.repository.FansRepository;
import com.moxian.ng.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
public class FansService {

	private static final Logger log = LoggerFactory.getLogger(FansService.class);

	@Inject
	private FansRepository fansRepository;

	@Inject
	private ConnectionsRepository connectionsRepository;
	
	@Inject
	private UserRepository userRepository;

	@Transactional
	public FansDetails savefans(FansForm form) {
		
		UserAccount  connectedUser = this.userRepository.findOne(form.getSendId());
		UserAccount  memberUser = this.userRepository.findOne(form.getReceptId());

		if (log.isDebugEnabled()) {
			log.debug("save fans @" + form);
		}
		Fans fans = DTOUtils.map(form, Fans.class);
		fans.setSend(connectedUser);
		fans.setRecept(memberUser);

		Fans fansOld = this.findFansBySendAndRecept(form.getSendId(),form.getReceptId());

		Fans saved = null;

		if (fansOld == null){
			saved = this.fansRepository.save(fans);
		}else{
			saved = fansOld;
			saved.setActive(true);
		}
		
		Connections send = this.connectionsRepository.findConnectionByConnectedUserAndMemberUser(connectedUser.getId(),memberUser.getId());
		
		if(send == null){
			send  = new Connections();
			send.setConnectedUser(connectedUser);
			send.setMemberUser(memberUser);
			send.setCreateOn(LocalDateTime.now());
			send.setType(Connections.Type.UNIDIRECTIONAL);
			this.connectionsRepository.save(send);
		}
		
		Connections recept = this.connectionsRepository.findConnectionByConnectedUserAndMemberUser(memberUser.getId(),connectedUser.getId());
		
		if(recept !=null){
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
		
		UserAccount  connectedUser = this.userRepository.findOne(fans.getSend().getId());
		UserAccount  memberUser = this.userRepository.findOne(fans.getRecept().getId());
		
		Connections send = this.connectionsRepository.findConnectionByConnectedUserAndMemberUser(connectedUser.getId(),memberUser.getId());
		
		if(send != null){
//			send.setType(Connections.Type.UNIDIRECTIONAL);
//			this.connectionsRepository.save(send);
			this.connectionsRepository.delete(send);
		}
		
		Connections recept = this.connectionsRepository.findConnectionByConnectedUserAndMemberUser(memberUser.getId(),connectedUser.getId());
		
		if(recept !=null){
			recept.setType(Connections.Type.UNIDIRECTIONAL);
			this.connectionsRepository.save(recept);
		}

		fans.setActive(false);
	}

	@Transactional
	public void updatefans(Long id, FansForm form) {
		Assert.notNull(id, "fans id can not be null");

		if (log.isDebugEnabled()) {
			log.debug("update fans @" + form);
		}

		Fans fans = this.fansRepository.findOne(id);
		DTOUtils.mapTo(form, fans);

		Fans saved = this.fansRepository.save(fans);

		if (log.isDebugEnabled()) {
			log.debug("updated fans@" + saved);
		}
	}

	// public Page<FansDetails> findFansBySendAndRecept(Long sendId,Long
	// receptId,Pageable page) {
	// Assert.notNull(sendId, "sendId id can not be null");
	// Assert.notNull(receptId, "receptId id can not be null");
	//
	// if (log.isDebugEnabled()) {
	// log.debug("find fans by sendId@" + sendId);
	// log.debug("find fans by receptId@" + receptId);
	// }
	//
	// Page<Fans> result =
	// this.fansRepository.findAll(Fanspecifications.filterFansBySendAndRecept(sendId,
	// receptId),page);
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

	private Fans findFansBySendAndRecept(Long sendId,
			Long receptId) {

		Assert.notNull(sendId, "send can not be null");
		Assert.notNull(receptId, "recept can not be null");

		if (log.isDebugEnabled()) {
			log.debug("find fans by send@" + sendId);
			log.debug("find fans by recept@" + receptId);
		}

		return this.fansRepository.findFansBySendAndRecept(sendId,
				receptId);

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

	@Transactional
	public void updateReceipt(Long id, boolean action) {
		Assert.notNull(id, "Fans id can not be null");

		if (log.isDebugEnabled()) {
			log.debug("find fans by id@" + id);
		}

		Fans Fans = this.fansRepository.findOne(id);

		if (Fans == null) {
			throw new ResourceNotFoundException(id);
		}

		// this.fansRepository.save(Fans);
	}

}
