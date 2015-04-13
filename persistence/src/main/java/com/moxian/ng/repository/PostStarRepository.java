/**
 * 
 */
package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.moxian.ng.domain.PostStar;

/**
 * @author yang
 *
 */
public interface PostStarRepository extends JpaRepository<PostStar, Long>, JpaSpecificationExecutor<PostStar> {

}
