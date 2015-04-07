package com.moxian.ng.repository;

import com.moxian.ng.domain.ConnectionRequests;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConnectionRequestRepository extends JpaRepository<ConnectionRequests, Long>,//
                                               JpaSpecificationExecutor<ConnectionRequests> {


}
