package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.moxian.ng.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> ,//
    JpaSpecificationExecutor<UserProfile> {

   
    public UserProfile findByAccountId(Long id);

    

}
