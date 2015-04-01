/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.repository;

import com.fenglianfinance.bill.model.LabelValueBean;
import java.util.List;

/**
 *
 * @author hantsy
 */
public interface CityRepositoryCustom {
    
    public List<LabelValueBean> filterByKeyword(String q);
}
