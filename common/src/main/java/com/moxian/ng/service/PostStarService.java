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
import com.moxian.ng.domain.PostStar;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.PostStarDetails;
import com.moxian.ng.model.PostStarForm;
import com.moxian.ng.repository.PostRepository;
import com.moxian.ng.repository.PostStarRepository;

/**
 * @author yang
 *
 */

@Service
public class PostStarService {

    private static final Logger log = LoggerFactory.getLogger(PostStarService.class);

    @Inject
    private PostStarRepository postStarRepository;

    @Inject
    private PostRepository postRepository;

    @Transactional
    public PostStarDetails savePostStar(PostStarForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save postStar @" + form);
        }

        Post post = this.postRepository.findOne(form.getPost().getId());

        if (post == null) {
            throw new ResourceNotFoundException(form.getPost().getId());
        }

        PostStar postStar = DTOUtils.map(form, PostStar.class);

        postStar.setPost(post);

        PostStar saved = this.postStarRepository.save(postStar);

        if (log.isDebugEnabled()) {
            log.debug("saved postStar id is @" + saved);
        }

        return DTOUtils.map(saved, PostStarDetails.class);

    }

    /**
     * @param id
     */
    public void deactivatePostStar(Long id) {

        Assert.notNull(id, "postrestar id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("delete postrestar by id @" + id);
        }

        PostStar postStar = this.postStarRepository.findOne(id);

        if (postStar == null) {
            throw new ResourceNotFoundException(id);
        }

        postStar.setActive(false);
        this.postStarRepository.save(postStar);
    }
}
