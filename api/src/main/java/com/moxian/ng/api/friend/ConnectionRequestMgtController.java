package com.moxian.ng.api.friend;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.moxian.ng.api.security.CurrentUser;
import com.moxian.ng.domain.IdCardVerification;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ConnectionRequestForm;
import com.moxian.ng.model.ConnectionRequestsDetails;
import com.moxian.ng.model.IdCardForm;
import com.moxian.ng.model.ResponseMessage;
import com.moxian.ng.service.ConnectionRequestService;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_CONNECTIONREQUEST)
public class ConnectionRequestMgtController {

	private static final Logger log = LoggerFactory
			.getLogger(ConnectionRequestMgtController.class);

	@Inject
	private ConnectionRequestService connectionRequestService;
	
	@RequestMapping(value = { "" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createConnectionRequest(
			@RequestBody ConnectionRequestForm form,
			UriComponentsBuilder uriComponentsBuilder) {
		if (log.isDebugEnabled()) {
			log.debug("save connectionRequest data @" + form);
		}

		ConnectionRequestsDetails saved = this.connectionRequestService.saveConnectionRequest(form);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_CONNECTIONREQUEST
						+ "/{id}").buildAndExpand(saved.getId()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
    @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET, params = "T=USER")
    @ResponseBody
	public ResponseEntity<Page<ConnectionRequestsDetails>>getCurrentUserConnectionRequest(@PathVariable("id") Long id,
     @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Sort.Direction.DESC)Pageable page) {
    	if (log.isDebugEnabled()) {
			log.debug("Gets the current user friend request userId@" + id);
		}
    	
    	Page<ConnectionRequestsDetails> connectionRequestsDetails =this.connectionRequestService.findConnectionRequestsByMemberUser(id, page);
    	
        return new ResponseEntity<>(connectionRequestsDetails, HttpStatus.OK);
	}
    
    @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT, params = "action=VERIFY_ACCEPT")
    @ResponseBody
    public ResponseEntity<ResponseMessage> verifyReceiptTrue(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("by request id@" + id);
        }
        
        this.connectionRequestService.updateReceipt(id,true);
        
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.success, "add friend success"),
                HttpStatus.NO_CONTENT);
    }
    
    @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT, params = "action=VERIFY_REJECT")
    @ResponseBody
    public ResponseEntity<ResponseMessage> verifyReceiptFalse(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("to refuse the request id@" + id);
        }
        
        this.connectionRequestService.updateReceipt(id,false);
        
        return new ResponseEntity<>(new ResponseMessage(ResponseMessage.Type.success, "add friend fail "),
                HttpStatus.NO_CONTENT);
    }

}
