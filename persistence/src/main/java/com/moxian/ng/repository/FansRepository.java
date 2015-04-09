package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.moxian.ng.domain.Fans;

public interface FansRepository extends JpaRepository<Fans, Long>,
		JpaSpecificationExecutor<Fans> {

	@Query("SELECT f FROM Fans f where f.followingUser.id=:followingUserId and f.memberUser.id=:memberUserId")
	Fans findFansBySendAndRecept(
			@Param("followingUserId") Long followingUserId,
			@Param("memberUserId") Long memberUserId);

}
