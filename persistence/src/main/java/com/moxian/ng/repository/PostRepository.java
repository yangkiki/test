package com.moxian.ng.repository;

import com.moxian.ng.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface PostRepository extends //
        JpaRepository<Post, Long>, //
        JpaSpecificationExecutor<Post> {

    @Query("update Post set status=?2 where id=?1")
    @Modifying
    public void updateStatus(Long id, Post.Status status);

    @Query("update Post set active=?2 where id=?1")
    @Modifying
    public void updateActive(Long id, boolean b);

    public Post findByTitle(String title);

}
