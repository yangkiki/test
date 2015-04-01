/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.gateway.api;

/**
 *
 * @author hansy
 */
public class OrderOwnerMismatchedExceptin extends RuntimeException {

    public OrderOwnerMismatchedExceptin(Long userId, Long orderId) {
    }
    
}
