package com.moxian.ng.repository;

import com.moxian.ng.domain.Connections;
import com.moxian.ng.domain.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConnectionsRepository extends JpaRepository<Connections, Long>,//
        JpaSpecificationExecutor<Connections> {

    @Query(" select  c.connectedUser from Connections c inner  join  c.memberUser as u where c.group.id=:groupId and u.id=:userId and c.type='BILATERAL' order by  u.name asc ")
    Page<UserAccount> findFriendsByGroup(@Param("userId") Long userId, @Param("groupId") Long groupId, Pageable page);

    @Query(" select  c.connectedUser from Connections c inner  join  c.memberUser as u where  u.id=:userId  and c.type='BILATERAL'  order by  u.name asc ")
    Page<UserAccount> findAllGroupFriends(@Param("userId") Long userId, Pageable page);

    @Query(" select  c.connectedUser from Connections c inner  join  c.memberUser as u where c.group.id is null  and u.id=:userId  and c.type='BILATERAL'  order by  u.name asc ")
    Page<UserAccount> findNotGroupFriends(@Param("userId") Long userId, Pageable page);

    @Modifying
    @Query("update Connections  c set c.group.id=:groupId where c.memberUser.id=:userId and  c.connectedUser.id=:friendId ")
    void updateFriendGroup(@Param("userId") Long userId, @Param("groupId") Long groupId,
            @Param("friendId") Long friendId);

    @Query(" select  c from Connections c where c.memberUser.id=:receptId and  c.connectedUser.id=:sendId ")
    Connections findConnectionByConnectedUserAndMemberUser(@Param("sendId") Long sendId,
            @Param("receptId") Long receptId);

}
