/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fenglianfinance.bill.payment.model;

/**
 *
 * @author hansy
 */
public enum AcctTransactionStatus {

    //汇付交易状态     S-成功  F-失败  I-初始  P-部分成功  H-经办  R-拒绝。
    SUCCESS("S"),
    FAILED("F"),
    INITIALIZED("I"),
    PARTIALLY("P"),
    HISTORY("H"),
    REJECTED("R");

    public static AcctTransactionStatus keyOf(String stat) {
        for (AcctTransactionStatus s : AcctTransactionStatus.values()) {
            if (stat.equals(s.getKey())) {
                return s;
            }
        }
        return null;
    }

    String key;

    private AcctTransactionStatus(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

}
