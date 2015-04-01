package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class BillDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String image;

    private String serialNumber;

    private LocalDate issuingDate;

    private String issuer;

    private String issuerBankAccount;

    private String payee;

    private String payeeBankAccount;

    private BankDetails payeeBank;

    private BankDetails acceptingBank;

    private BigDecimal denomination;// 票面金额

    private LocalDate expirationDate;

    private String acceptanceAgreementNumber;

    private LocalDate invoiceDate;

    private Integer usance;

    private Float invoiceRate;

    private BigDecimal invoiceInterestAmount;

    private EnterpriseDetails enterprise;

    private String financingPurposes;

    private String rejectionCause;

    private String status;

    private boolean active = true;

    private UserAccountDetails reviewer;

    private LocalDateTime reviewTime;

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

    public BankDetails getPayeeBank() {
        return payeeBank;
    }

    public void setPayeeBank(BankDetails payeeBank) {
        this.payeeBank = payeeBank;
    }

    public BankDetails getAcceptingBank() {
        return acceptingBank;
    }

    public void setAcceptingBank(BankDetails acceptingBank) {
        this.acceptingBank = acceptingBank;
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

    public EnterpriseDetails getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(EnterpriseDetails enterprise) {
        this.enterprise = enterprise;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserAccountDetails getReviewer() {
        return reviewer;
    }

    public void setReviewer(UserAccountDetails reviewer) {
        this.reviewer = reviewer;
    }

    public LocalDateTime getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(LocalDateTime reviewTime) {
        this.reviewTime = reviewTime;
    }

}
