package com.moxian.ng.repository;

import com.moxian.ng.domain.Group;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<Group, Long>,//
                                               JpaSpecificationExecutor<Group> {


  @Query(" select g from Group  g  inner  join  g.memberUser u where u.id=:userId order by  g.createOn asc  ")
  Page<Group> findAllGroups(@Param("userId") Long userId, Pageable page);

}
