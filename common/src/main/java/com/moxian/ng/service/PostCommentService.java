/**
 * 
 */
package com.moxian.ng.service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.Post;
import com.moxian.ng.domain.PostComment;
import com.moxian.ng.domain.UserAccount;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.PostCommentDetails;
import com.moxian.ng.model.PostCommentForm;
import com.moxian.ng.repository.PostCommentRepository;
import com.moxian.ng.repository.PostRepository;
import com.moxian.ng.repository.UserRepository;

/**
 * @author yang
 *
 */
@Service
public class PostCommentService {

    private static final Logger log = LoggerFactory.getLogger(PostStarService.class);

    @Inject
    private PostCommentRepository postCommentRepository;

    @Inject
    private PostRepository postRepository;

    @Inject
    private UserRepository userRepository;

    @Transactional
    public PostCommentDetails savePostComment(PostCommentForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save postComment @" + form);
        }

        Post post = this.postRepository.findOne(form.getPost().getId());

        if (post == null) {
            throw new ResourceNotFoundException(form.getPost().getId());
        }

        UserAccount publishUser = this.userRepository.findOne(form.getPublishUser().getId());
        UserAccount replyUser = this.userRepository.findOne(form.getReplyUser().getId());

        PostComment postComment = DTOUtils.map(form, PostComment.class);

        postComment.setPost(post);
        postComment.setPublishUser(publishUser);
        postComment.setReplyUser(replyUser);

        PostComment saved = this.postCommentRepository.save(postComment);

        if (log.isDebugEnabled()) {
            log.debug("saved postComment id is @" + saved);
        }

        return DTOUtils.map(saved, PostCommentDetails.class);

    }

    /**
     * @param id
     */
    public void deactivatePostComment(Long id) {

        Assert.notNull(id, "postcomment id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("delete postcomment by id @" + id);
        }

        PostComment postComment = this.postCommentRepository.findOne(id);

        if (postComment == null) {
            throw new ResourceNotFoundException(id);
        }

        postComment.setActive(false);
        this.postCommentRepository.save(postComment);
    }

}
