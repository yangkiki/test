package com.moxian.ng.exception;

public class EnterpriseExistedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String enterpriseName;

    public EnterpriseExistedException(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

}
