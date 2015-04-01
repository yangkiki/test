package com.moxian.ng.repository;

import com.moxian.ng.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, Long> {

    public Province findByName(String name);


}

