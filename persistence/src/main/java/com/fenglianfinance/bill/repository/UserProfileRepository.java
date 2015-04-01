package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.fenglianfinance.bill.domain.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> ,//
    JpaSpecificationExecutor<UserProfile> {

   
    public UserProfile findByAccountId(Long id);

    

}
