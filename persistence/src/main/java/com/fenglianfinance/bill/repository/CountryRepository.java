package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fenglianfinance.bill.domain.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

    public Country findByName(String name);
}
