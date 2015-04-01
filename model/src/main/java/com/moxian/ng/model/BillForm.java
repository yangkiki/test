package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BillForm implements Serializable {

    private static final long serialVersionUID = 1L;

    private String image;

    private String serialNumber;

    private LocalDate issuingDate;

    private String issuer;

    private String issuerBankAccount;

    private String payee;

    private String payeeBankAccount;

    private IdValue payeeBankFK;

    private IdValue acceptingBankFK;

    private BigDecimal denomination;// 票面金额

    private LocalDate expirationDate;

    private String acceptanceAgreementNumber;

    private LocalDate invoiceDate;

    private Integer usance;

    private Float invoiceRate;

    private BigDecimal invoiceInterestAmount;

    private IdValue enterpriseFK;

    private String financingPurposes;

    private String rejectionCause;
    
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public LocalDate getIssuingDate() {
        return issuingDate;
    }

    public void setIssuingDate(LocalDate issuingDate) {
        this.issuingDate = issuingDate;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getIssuerBankAccount() {
        return issuerBankAccount;
    }

    public void setIssuerBankAccount(String issuerBankAccount) {
        this.issuerBankAccount = issuerBankAccount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayeeBankAccount() {
        return payeeBankAccount;
    }

    public void setPayeeBankAccount(String payeeBankAccount) {
        this.payeeBankAccount = payeeBankAccount;
    }

    public IdValue getPayeeBankFK() {
        return payeeBankFK;
    }

    public void setPayeeBankFK(IdValue payeeBankFK) {
        this.payeeBankFK = payeeBankFK;
    }

    public IdValue getAcceptingBankFK() {
        return acceptingBankFK;
    }

    public void setAcceptingBankFK(IdValue acceptingBankFK) {
        this.acceptingBankFK = acceptingBankFK;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getAcceptanceAgreementNumber() {
        return acceptanceAgreementNumber;
    }

    public void setAcceptanceAgreementNumber(String acceptanceAgreementNumber) {
        this.acceptanceAgreementNumber = acceptanceAgreementNumber;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Integer getUsance() {
        return usance;
    }

    public void setUsance(Integer usance) {
        this.usance = usance;
    }

    public Float getInvoiceRate() {
        return invoiceRate;
    }

    public void setInvoiceRate(Float invoiceRate) {
        this.invoiceRate = invoiceRate;
    }

    public BigDecimal getInvoiceInterestAmount() {
        return invoiceInterestAmount;
    }

    public void setInvoiceInterestAmount(BigDecimal invoiceInterestAmount) {
        this.invoiceInterestAmount = invoiceInterestAmount;
    }

    public IdValue getEnterpriseFK() {
        return enterpriseFK;
    }

    public void setEnterpriseFK(IdValue enterpriseFK) {
        this.enterpriseFK = enterpriseFK;
    }

    public String getFinancingPurposes() {
        return financingPurposes;
    }

    public void setFinancingPurposes(String financingPurposes) {
        this.financingPurposes = financingPurposes;
    }

    public String getRejectionCause() {
        return rejectionCause;
    }

    public void setRejectionCause(String rejectionCause) {
        this.rejectionCause = rejectionCause;
    }

    @Override
    public String toString() {
        return "BillForm [image=" + image + ", serialNumber=" + serialNumber + ", issuingDate=" + issuingDate
                + ", issuer=" + issuer + ", issuerBankAccount=" + issuerBankAccount + ", payee=" + payee
                + ", payeeBankAccount=" + payeeBankAccount + ", payeeBankFK=" + payeeBankFK + ", acceptingBankFK="
                + acceptingBankFK + ", denomination=" + denomination + ", expirationDate=" + expirationDate
                + ", acceptanceAgreementNumber=" + acceptanceAgreementNumber + ", invoiceDate=" + invoiceDate
                + ", usance=" + usance + ", invoiceRate=" + invoiceRate + ", invoiceInterestAmount="
                + invoiceInterestAmount + ", enterpriseFK=" + enterpriseFK + ", financingPurposes=" + financingPurposes
                + ", rejectionCause=" + rejectionCause + "]";
    }

}
