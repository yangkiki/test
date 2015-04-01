package com.moxian.ng.exception;

public class ProductNameExistedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String productname;

    public ProductNameExistedException(String productname) {
        this.productname = productname;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

}
