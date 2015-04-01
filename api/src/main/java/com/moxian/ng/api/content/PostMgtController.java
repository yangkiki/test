package com.moxian.ng.api.content;

import com.moxian.ng.domain.Post;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.moxian.ng.model.ApiConstants;
import com.moxian.ng.model.PostDetails;
import com.moxian.ng.model.PostForm;
import com.moxian.ng.service.ContentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = ApiConstants.URI_API_MGT + "/posts")
public class PostMgtController {

    private static final Logger log = LoggerFactory.getLogger(PostMgtController.class);

    @Inject
    private ContentService contentService;

    // @Inject
    // private CommentRepository commentRepository;
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Page<PostDetails>> getAllPosts(//
            @RequestParam("q") String q, //
            @RequestParam("active") boolean active,//
            @RequestParam("type") Post.Type type,//
            @RequestParam("status") Post.Status status,//
            @PageableDefault(page = 0, size = 10, sort = "createdDate", direction = Direction.DESC) Pageable page) {

        if (log.isDebugEnabled()) {
            log.debug("get all posts@" + q + ", active=" + active + ", type =" + type + ", status =" + status + ", pageable=" + page);
        }

        Sort createdOnDesc = new Sort(Direction.DESC, "createdDate");
        Page<PostDetails> posts = contentService.findPostDetailsByKeyword(q, active, type, status, page);

        if (log.isDebugEnabled()) {
            log.debug("get posts size @" + posts.getTotalElements());
        }

        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PostDetails> getPost(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("get postsinfo by id @" + id);
        }

        PostDetails post = contentService.findPostById(id);

        if (log.isDebugEnabled()) {
            log.debug("get post @" + post);
        }

        return new ResponseEntity<PostDetails>(post, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Void> createPost(@RequestBody PostForm post, UriComponentsBuilder builder) {
        if (log.isDebugEnabled()) {
            log.debug("create a new post");
        }

        PostDetails saved = contentService.savePost(post);

        if (log.isDebugEnabled()) {
            log.debug("saved post id is @" + saved.getId());
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/posts/{id}").buildAndExpand(saved.getId()).toUri());

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "!action")
    @ResponseBody
    public ResponseEntity<Void> updatePost(@PathVariable("id") Long id, @RequestBody PostForm form) {
        if (log.isDebugEnabled()) {
            log.debug("update post@ id=" + id, "form @" + form);
        }

        contentService.updatePost(id, form);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "action=PUBLISH")
    @ResponseBody
    public ResponseEntity<Void> publishPost(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("update post@ id=" + id);
        }

        contentService.publishPost(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, params = "action=ACTIVATE")
    @ResponseBody
    public ResponseEntity<Void> activatePost(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("activate post@ id=" + id);
        }

        contentService.activatePost(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Void> deletePost(@PathVariable("id") Long id) {
        if (log.isDebugEnabled()) {
            log.debug("delete post by id @" + id);
        }

        contentService.deactivatePost(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @RequestMapping(value = "/{id}/comments", method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity<Void> createCommentOfPost(@PathVariable("id") Post post, @RequestBody Comment comment) {
//        if (log.isDebugEnabled()) {
//            log.debug("new comment of post@" + post);
//        }
//
//        comment.setPost(post);
//
//        Comment saved = commentRepository.save(comment);
//
//        if (log.isDebugEnabled()) {
//            log.debug("saved comment @" + saved.getId());
//        }
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @RequestMapping(value = "/{id}/comments", method = RequestMethod.GET)
//    @ResponseBody
//    public ResponseEntity<List<Comment>> getCommentsOfPost(@PathVariable("id") Post post) {
//        if (log.isDebugEnabled()) {
//            log.debug("get comments of post@" + post);
//        }
//
//        List<Comment> commentsOfPost = commentRepository.findByPost(post);
//
//        if (log.isDebugEnabled()) {
//            log.debug("get post @" + commentsOfPost.size());
//        }
//
//        return new ResponseEntity<List<Comment>>(commentsOfPost, HttpStatus.OK);
//    }
}
