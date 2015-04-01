package com.moxian.ng.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moxian.ng.domain.City;

public interface CityRepository extends JpaRepository<City, Long> , CityRepositoryCustom{

    public City findByName(String  cityName);

    
}
