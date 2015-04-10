package com.moxian.ng.api.friend;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ErrorCode;
// import com.moxian.ng.model.FansDetails;
import com.moxian.ng.model.FansForm;
import com.moxian.ng.model.SingleResponse;
import com.moxian.ng.service.FansService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.util.UriComponentsBuilder;

import javax.inject.Inject;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_FANS)
public class FansMgtController {

    private static final Logger log = LoggerFactory.getLogger(FansMgtController.class);

    @Inject
    private FansService fansService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SingleResponse<Void>> saveFans(@RequestBody FansForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save connectionRequest data @" + form);
        }

        // FansDetails saved =
        this.fansService.savefans(form);

        SingleResponse<Void> response = SingleResponse.successRsp();
        response.setCode(ErrorCode.SUCCESS);

        // HttpHeaders headers = new HttpHeaders();
        // headers.setLocation(uriComponentsBuilder
        // .path(ApiConstants.URI_API_PUBLIC + ApiConstants.URI_FANS
        // + "/{id}").buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<SingleResponse<Void>> deleteFans(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete fans by id @" + id);
        }

        fansService.deactivateFans(id);

        SingleResponse<Void> response = SingleResponse.successRsp();
        response.setCode(ErrorCode.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // @RequestMapping(value = {"/{id}"}, method = RequestMethod.GET, params =
    // "T=USER")
    // @ResponseBody
    // public
    // ResponseEntity<Page<ConnectionRequestsDetails>>getCurrentUserConnectionRequest(@PathVariable("id")
    // Long id,
    // @PageableDefault(page = 0, size = 10, sort = "createdDate", direction =
    // Sort.Direction.DESC)Pageable page) {
    // if (log.isDebugEnabled()) {
    // log.debug("Gets the current user friend request userId@" + id);
    // }
    //
    // Page<ConnectionRequestsDetails> connectionRequestsDetails
    // =this.fansService.findConnectionRequestsByMemberUser(id, page);
    //
    // return new ResponseEntity<>(connectionRequestsDetails, HttpStatus.OK);
    // }

    // @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT, params =
    // "action=VERIFY_ACCEPT")
    // @ResponseBody
    // public ResponseEntity<ResponseMessage>
    // verifyReceiptTrue(@PathVariable("id") Long id) {
    // if (log.isDebugEnabled()) {
    // log.debug("by request id@" + id);
    // }
    //
    // this.fansService.updateReceipt(id,true);
    //
    // return new ResponseEntity<>(new
    // ResponseMessage(ResponseMessage.Type.success,
    // "add friend success"),
    // HttpStatus.NO_CONTENT);
    // }
    //
    // @RequestMapping(value = {"/{id}"},method = RequestMethod.PUT, params =
    // "action=VERIFY_REJECT")
    // @ResponseBody
    // public ResponseEntity<ResponseMessage>
    // verifyReceiptFalse(@PathVariable("id") Long id) {
    // if (log.isDebugEnabled()) {
    // log.debug("to refuse the request id@" + id);
    // }
    //
    // this.fansService.updateReceipt(id,false);
    //
    // return new ResponseEntity<>(new
    // ResponseMessage(ResponseMessage.Type.success,
    // "add friend fail "),
    // HttpStatus.NO_CONTENT);
    // }

}
