package com.fenglianfinance.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fenglianfinance.bill.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {


}
