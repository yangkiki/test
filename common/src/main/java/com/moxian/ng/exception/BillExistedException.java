/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.moxian.ng.exception;

/**
 *
 * @author hantsy<hantsy@gmail.com>
 */
public class BillExistedException extends RuntimeException {

    private Long billId;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public BillExistedException(Long billId) {
        super();
        this.billId = billId;
    }

}
