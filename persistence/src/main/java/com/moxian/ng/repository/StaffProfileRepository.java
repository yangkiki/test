package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.moxian.ng.domain.StaffProfile;

public interface StaffProfileRepository extends JpaRepository<StaffProfile, Long>, 
        //
        JpaSpecificationExecutor<StaffProfile> {

    public StaffProfile findByUserAccountId(Long id);

}
