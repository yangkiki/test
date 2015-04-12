/**
 * 
 */
package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.moxian.ng.domain.PostComment;

/**
 * @author yang
 *
 */
public interface PostCommentRepository extends JpaRepository<PostComment, Long>, JpaSpecificationExecutor<PostComment> {

}
