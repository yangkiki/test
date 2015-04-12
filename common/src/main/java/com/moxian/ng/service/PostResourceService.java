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
import com.moxian.ng.domain.PostResource;
import com.moxian.ng.exception.ResourceNotFoundException;
import com.moxian.ng.model.PostResourceDetails;
import com.moxian.ng.model.PostResourceForm;
import com.moxian.ng.repository.PostRepository;
import com.moxian.ng.repository.PostResourceRepository;

/**
 * @author yang
 *
 */
@Service
public class PostResourceService {

    private static final Logger log = LoggerFactory.getLogger(PostResourceService.class);

    @Inject
    private PostResourceRepository postResourceRepository;

    @Inject
    private PostRepository postRepository;

    @Transactional
    public PostResourceDetails savePostResource(PostResourceForm form) {
        if (log.isDebugEnabled()) {
            log.debug("save postResource @" + form);
        }

        Post post = this.postRepository.findOne(form.getPost().getId());

        if (post == null) {
            throw new ResourceNotFoundException(form.getPost().getId());
        }

        PostResource postResource = DTOUtils.map(form, PostResource.class);

        postResource.setPost(post);

        PostResource saved = this.postResourceRepository.save(postResource);

        if (log.isDebugEnabled()) {
            log.debug("saved postResource id is @" + saved);
        }

        return DTOUtils.map(postResource, PostResourceDetails.class);

    }

    /**
     * @param id
     */
    public void deactivatePostResource(Long id) {

        Assert.notNull(id, "postresource id can not be null");

        if (log.isDebugEnabled()) {
            log.debug("delete postresource by id @" + id);
        }

        PostResource postResourcs = this.postResourceRepository.findOne(id);

        if (postResourcs == null) {
            throw new ResourceNotFoundException(id);
        }

        postResourcs.setActive(false);
        this.postResourceRepository.save(postResourcs);
    }

}
