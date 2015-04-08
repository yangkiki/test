package com.moxian.ng.service;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.ConnectionRequests;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.ConnectionRequestForm;
import com.moxian.ng.model.ConnectionRequestsDetails;
import com.moxian.ng.repository.ConnectionRequestRepository;
import com.moxian.ng.repository.ConnectionRequestSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import javax.inject.Inject;
import javax.transaction.Transactional;


@Service
public class ConnectionRequestService {
	
	private static final Logger log = LoggerFactory.getLogger(ConnectionRequestService.class);

	@Inject
	private ConnectionRequestRepository connectionRequestRepository;
	
	@Inject
	private ConnectionService connectionService;
	
	@Transactional
	public ConnectionRequestsDetails saveConnectionRequest(ConnectionRequestForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save connectionrequest @" + form);
        }

        ConnectionRequests connectionRequest = DTOUtils.map(form, ConnectionRequests.class);

        ConnectionRequests saved = this.connectionRequestRepository.save(connectionRequest);

        if (log.isDebugEnabled()) {
            log.debug("saved connectionrequest id is @" + saved);
        }

        return DTOUtils.map(saved, ConnectionRequestsDetails.class);

    }
	
	@Transactional
	public void updateConnectionRequest(Long id, ConnectionRequestForm form) {
        Assert.notNull(id, "connectionrequest id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("update connectionrequest @" + form);
        }

        ConnectionRequests connectionRequest = this.connectionRequestRepository.findOne(id);
        DTOUtils.mapTo(form, connectionRequest);

        ConnectionRequests saved = this.connectionRequestRepository.save(connectionRequest);

        if (log.isDebugEnabled()) {
            log.debug("updated connectionrequest@" + saved);
        }
    }
	
	public Page<ConnectionRequestsDetails> findConnectionRequestsByMemberUser(Long id,Pageable page) {
        Assert.notNull(id, "memberUser id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("find connectionrequest by memberUser@" + id);
        }

        Page<ConnectionRequests> result = this.connectionRequestRepository.findAll(ConnectionRequestSpecifications.filterConnectionRequestsByMemberUser(id),page);

        if (result == null) {
            throw new ResourceNotFoundException(id);
        }

        if (log.isDebugEnabled()) {
            log.debug("get result size @" + result.getTotalElements());
        }

        return DTOUtils.mapPage(result, ConnectionRequestsDetails.class);
    }

	public ConnectionRequestsDetails findConnectionRequestById(Long id) {
		Assert.notNull(id, "ConnectionRequests id can not be null");
		
		if (log.isDebugEnabled()) {
            log.debug("find connectionrequest by id@" + id);
	    }
	
		ConnectionRequests connectionRequests = this.connectionRequestRepository.findOne(id);
	
        if (connectionRequests == null) {
	            throw new ResourceNotFoundException(id);
	    }

		return DTOUtils.map(connectionRequests, ConnectionRequestsDetails.class);
	}

	@Transactional
	public void updateReceipt(Long id, boolean action) {
		Assert.notNull(id, "ConnectionRequests id can not be null");
		
		if (log.isDebugEnabled()) {
            log.debug("find connectionrequest by id@" + id);
	    }
	
		ConnectionRequests connectionRequests = this.connectionRequestRepository.findOne(id);
		
		if (connectionRequests == null) {
            throw new ResourceNotFoundException(id);
		}
		
		if(action){
			connectionRequests.setStatus(ConnectionRequests.Status.ACCEPT);
			this.connectionService.saveConnectionByConnectionRequest(connectionRequests);
		}else{
			connectionRequests.setStatus(ConnectionRequests.Status.REJECT);
		}
		
		//this.connectionRequestRepository.save(connectionRequests);
	}
	
}
