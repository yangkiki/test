package com.fenglianfinance.bill.repository;

import java.time.LocalDate;
import java.util.List;

import com.fenglianfinance.bill.domain.Product;

public interface ProductRepositoryCustom {

    List<Product> findProductOrderByEnterpriseAndType(LocalDate d);
}
