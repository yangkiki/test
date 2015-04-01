package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fenglianfinance.bill.domain.Province;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    public Province findByName(String name);


}

