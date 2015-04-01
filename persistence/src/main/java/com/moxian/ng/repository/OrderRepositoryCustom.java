/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moxian.ng.repository;

import java.math.BigDecimal;
import java.util.Map;

/**
 *
 * @author hansy
 */
public interface OrderRepositoryCustom {

    public Map<String, BigDecimal> sumOrdersByUser(Long id);

    public Map<String, BigDecimal> sumActiveOrdersByUser(Long id);

    public Map<String, BigDecimal> sumOrderInterestsByUser(Long id);

    public Map<String, BigDecimal> sumActiveOrderInterestsByUser(Long id);
    
}
