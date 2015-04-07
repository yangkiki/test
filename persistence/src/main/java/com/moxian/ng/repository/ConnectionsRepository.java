package com.moxian.ng.repository;

import com.moxian.ng.domain.Connections;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ConnectionsRepository extends JpaRepository<Connections, Long>,//
                                               JpaSpecificationExecutor<Connections> {


}
