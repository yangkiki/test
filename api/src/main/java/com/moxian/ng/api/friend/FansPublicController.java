package com.moxian.ng.api.friend;

import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.FansDetails;
import com.moxian.ng.service.FansService;



@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + ApiConstants.URI_FANS)
public class FansPublicController {
	
	private static final Logger log = LoggerFactory.getLogger(FansPublicController.class);
	
	@Inject
    private FansService fansService;
	
	@RequestMapping(value = {"/{id}"}, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<FansDetails> getUser(@PathVariable("id") Long id) {

        if (log.isDebugEnabled()) {
            log.debug("get user data by id @" + id);
        }

        FansDetails fansDetails = this.fansService.findfansById(id);

        return new ResponseEntity<>(fansDetails, HttpStatus.OK);
    }


}
