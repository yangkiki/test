package com.moxian.ng.repository;

import com.moxian.ng.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    public Country findByName(String name);
}
