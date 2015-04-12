/**
 * 
 */
package com.moxian.ng.api.content;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.ErrorCode;
import com.moxian.ng.model.PostResourceForm;
import com.moxian.ng.model.SingleResponse;
import com.moxian.ng.service.PostResourceService;

/**
 * @author yang
 *
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_RESOURCE)
public class PostResourceMgtController {

    private static final Logger log = LoggerFactory.getLogger(PostResourceMgtController.class);

    @Inject
    private PostResourceService postResourceService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SingleResponse<Void>> savePostResource(@RequestBody PostResourceForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save postResource data @" + form);
        }

        this.postResourceService.savePostResource(form);

        SingleResponse<Void> response = SingleResponse.successRsp();
        response.setCode(ErrorCode.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<SingleResponse<Void>> deletePostResource(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete postresource by id @" + id);
        }

        this.postResourceService.deactivatePostResource(id);

        SingleResponse<Void> response = SingleResponse.successRsp();
        response.setCode(ErrorCode.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
