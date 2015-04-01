/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.service;

import com.moxian.ng.DTOUtils;
import com.moxian.ng.domain.Post;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.PostDetails;
import com.moxian.ng.model.PostForm;
import com.moxian.ng.repository.PostSpecifications;
import com.moxian.ng.repository.PostRepository;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
@Service
@Transactional
public class ContentService {

    private static final Logger log = LoggerFactory.getLogger(ContentService.class);

    @Inject
    private PostRepository postRepository;

    public Page<PostDetails> findPostDetailsByKeyword(String q, boolean active, Post.Type type, Post.Status status, Pageable page) {
        if (log.isDebugEnabled()) {
            log.debug("search posts by keyword@" + q + ", active @" + active + ", type=" + type + ", status @" + status);
        }

        Page<Post> posts = postRepository.findAll(PostSpecifications.filterPostsByKeywordAndStatus(q, active, type, status),
                page);

        if (log.isDebugEnabled()) {
            log.debug("get posts size @" + posts.getTotalElements());
        }

        return DTOUtils.mapPage(posts, PostDetails.class);
    }

    public PostDetails savePost(PostForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save post @" + form);
        }

        Post post = DTOUtils.map(form, Post.class);

        Post saved = postRepository.save(post);

        if (log.isDebugEnabled()) {
            log.debug("saved post id is @" + saved);
        }

        return DTOUtils.map(post, PostDetails.class);

    }

    public void updatePost(Long id, PostForm form) {
        Assert.notNull(id, "post id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("update post @" + form);
        }

        Post post = postRepository.findOne(id);
        DTOUtils.mapTo(form, post);

        Post saved = postRepository.save(post);

        if (log.isDebugEnabled()) {
            log.debug("updated post@" + saved);
        }
    }

    public PostDetails findPostById(Long id) {
        Assert.notNull(id, "post id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("find post by id@" + id);
        }

        Post post = postRepository.findOne(id);

        if (post == null) {
            throw new ResourceNotFoundException(id);
        }

        return DTOUtils.map(post, PostDetails.class);
    }

    public void deactivatePost(Long id) {
        Assert.notNull(id, "post id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("delete post by id @" + id);
        }

        Post post = postRepository.findOne(id);

        if (post == null) {
            throw new ResourceNotFoundException(id);
        }

        postRepository.updateActive(id, false);
    }

    public void activatePost(Long id) {
        Assert.notNull(id, "post id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("delete post by id @" + id);
        }

        Post post = postRepository.findOne(id);

        if (post == null) {
            throw new ResourceNotFoundException(id);
        }

        postRepository.updateActive(id, true);
    }

    public void publishPost(Long id) {
        Assert.notNull(id, "post id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("update post status, set status to 'PUBLISHED'");
        }

        postRepository.updateStatus(id, Post.Status.PUBLISHED);

    }

}
