package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.domain.Bill;
import com.fenglianfinance.bill.domain.UserViewStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserViewStatisticRepository extends JpaRepository<UserViewStatistic, Long> {


}
