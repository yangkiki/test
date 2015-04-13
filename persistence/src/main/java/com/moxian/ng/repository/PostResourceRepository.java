/**
 * 
 */
package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.moxian.ng.domain.PostResource;

/**
 * @author yang
 *
 */
public interface PostResourceRepository extends JpaRepository<PostResource, Long>,
        JpaSpecificationExecutor<PostResource> {

}
