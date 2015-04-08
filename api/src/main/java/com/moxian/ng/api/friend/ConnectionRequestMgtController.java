package com.moxian.ng.api.friend;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ConnectionRequestForm;
import com.moxian.ng.model.ConnectionRequestsDetails;
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

		ConnectionRequestsDetails saved = connectionRequestService.saveConnectionRequest(form);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uriComponentsBuilder.path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_CONNECTIONREQUEST
						+ "/{id}").buildAndExpand(saved.getId()).toUri());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

}
