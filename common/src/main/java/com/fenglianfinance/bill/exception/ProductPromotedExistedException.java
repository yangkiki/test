package com.fenglianfinance.bill.exception;

public class ProductPromotedExistedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String promoted;

    public ProductPromotedExistedException(String promoted) {
        this.promoted = promoted;
    }

    public String getPromoted() {
        return promoted;
    }

    public void setPromoted(String promoted) {
        this.promoted = promoted;
    }

}
