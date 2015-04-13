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
import com.moxian.ng.model.PostCommentForm;
import com.moxian.ng.model.SingleResponse;
import com.moxian.ng.service.PostCommentService;

/**
 * @author yang
 *
 */
@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + ApiConstants.URI_COMMENT)
public class PostCommentMgtController {

    private static final Logger log = LoggerFactory.getLogger(PostCommentMgtController.class);

    @Inject
    private PostCommentService postCommentService;

    @RequestMapping(value = {""}, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SingleResponse<Void>> savePostComment(@RequestBody PostCommentForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save postcomment data @" + form);
        }

        this.postCommentService.savePostComment(form);

        SingleResponse<Void> response = SingleResponse.successRsp();
        response.setCode(ErrorCode.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<SingleResponse<Void>> deletePostComment(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete postcomment by id @" + id);
        }

        this.postCommentService.deactivatePostComment(id);

        SingleResponse<Void> response = SingleResponse.successRsp();
        response.setCode(ErrorCode.SUCCESS);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
