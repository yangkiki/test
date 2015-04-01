package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fenglianfinance.bill.domain.City;

public interface CityRepository extends JpaRepository<City, Long> , CityRepositoryCustom{

    public City findByName(String  cityName);

    
}
