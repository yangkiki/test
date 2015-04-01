package com.fenglianfinance.bill.api.content;

import com.fenglianfinance.bill.domain.Post;
import com.fenglianfinance.bill.exception.ResourceNotFoundException;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fenglianfinance.bill.model.ApiConstants;
import com.fenglianfinance.bill.model.PostDetails;
import com.fenglianfinance.bill.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping(value = ApiConstants.URI_API_PUBLIC + "/posts")
public class PostPublicController {

    private static final Logger log = LoggerFactory.getLogger(PostPublicController.class);

    @Inject
    private ContentService contentService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<PostDetails>> getAllPublishedPosts(
            @RequestParam("f") Post.Type type,
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get all posts@ pageable=" + page);
        }

        Page<PostDetails> posts = contentService.findPostDetailsByKeyword(null, true, type, Post.Status.PUBLISHED, page);

        if (log.isDebugEnabled()) {
            log.debug("get posts size @" + posts.getTotalElements());
        }

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PostDetails> getPost(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("get postDetails by id @" + id);
        }

        PostDetails post = contentService.findPostById(id);
        
        if(!post.isActive()){
            throw new ResourceNotFoundException(id);
        }

        if (log.isDebugEnabled()) {
            log.debug("get post @" + post);
        }

        return new ResponseEntity<PostDetails>(post, HttpStatus.OK);
    }

}
