package com.moxian.ng.repository;

import com.moxian.ng.domain.Connections;
import com.moxian.ng.domain.UserAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConnectionsRepository extends JpaRepository<Connections, Long>,//
                                               JpaSpecificationExecutor<Connections> {


  @Query(" select  u from Connections c inner  join  c.memberUser as u where u.id=:userId order by  u.name asc ")
  Page<UserAccount> findDefaultGroupFriends(@Param("userId")Long userId,Pageable page);


}
