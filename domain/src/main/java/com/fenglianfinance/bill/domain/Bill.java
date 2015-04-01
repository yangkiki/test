package com.fenglianfinance.bill.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

@Entity
@Table(//
        name = "bills",//
        uniqueConstraints = {//
        @UniqueConstraint(columnNames = {"serial_number"})//
        }//
)
public class Bill extends AuditableEntity<UserAccount, Long> {

    public enum Mode {

        SELECT, //
        CHECKPAY, //
        EIGHTYPERCENT, //
        ABSOLUTELY//
    }

    public enum Status {

        DRAFT, // save
        PENDING, // pulished
        APPROVED, //
        DEPRECATED//
    }

    private static final long serialVersionUID = 1L;

    @Column(name = "image_id")
    private String image;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "issuing_date")
    private LocalDate issuingDate;

    private String issuer;

    @Column(name = "issuer_bank_account")
    private String issuerBankAccount;

    private String payee;

    @Column(name = "payee_bank_account")
    private String payeeBankAccount;

    @ManyToOne
    @JoinColumn(name = "payee_bank_id")
    private Bank payeeBank;

    @ManyToOne
    @JoinColumn(name = "accepting_bank_id")
    private Bank acceptingBank;

    @Column(name = "denomination")
    private BigDecimal denomination;// 票面金额

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "acceptance_agreement_number")
    private String acceptanceAgreementNumber;

    @Column(name = "invoice_date")
    private LocalDate invoiceDate;

    private Integer usance;

    @Column(name = "invoice_rate")
    private Float invoiceRate;

    @Column(name = "invoice_interest_amount")
    private BigDecimal invoiceInterestAmount;

    @ManyToOne
    @JoinColumn(name = "enterprise_id")
    private Enterprise enterprise;

    @Column(name = "financing_purposes")
    private String financingPurposes;

    @Column(name = "rejection_cause")
    private String rejectionCause;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "is_active")
    private boolean active = true;

    public Bill() {}

    // private Float transactionPriceRate;
    //
    // private BigDecimal transactionPriceAmount;
    //
    // private Float companyQuotationRate;
    //
    // private BigDecimal companyQuotationAmount;
    //
    // @Enumerated(EnumType.STRING)
    // private Mode mode;
    //
    // // the following three should be caculated by runtime??
    // private BigDecimal largeAmountInterest;
    // private BigDecimal smallAmountInterest;
    //
    // private BigDecimal deductedInterestAmount;

    // private String payee;
    //
    // private String shroffAccountNumber;
    //
    // private String bankAccount;
    //
    // private String bigMoneyAccount;
    // private BigDecimal wireTransferEnquiryFee;

    // @Enumerated(EnumType.STRING)
    // private Type type;

    // @Embedded
    // private LargePayableAmount largePayableAmount;

    // private BigDecimal smallPayableAmount;

    // @ManyToMany(mappedBy = "intruments")
    // private List<ProductStock> stocks = new ArrayList<>();

    // private LocalDate discountDate;
    //
    // private Integer adjustDays;

    // @ManyToOne
    // @JoinColumn(name = "bill_ticket_clerk")
    // private UserAccount billTicketClerk;

    public String getAcceptanceAgreementNumber() {
        return acceptanceAgreementNumber;
    }

    public Bank getAcceptingBank() {
        return acceptingBank;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public Enterprise getEnterprise() {
        return enterprise;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public String getFinancingPurposes() {
        return financingPurposes;
    }

    public String getImage() {
        return image;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public Float getInvoiceRate() {
        return invoiceRate;
    }

    public String getIssuer() {
        return issuer;
    }

    public String getIssuerBankAccount() {
        return issuerBankAccount;
    }

    public LocalDate getIssuingDate() {
        return issuingDate;
    }

    public String getPayee() {
        return payee;
    }

    public Bank getPayeeBank() {
        return payeeBank;
    }

    public String getPayeeBankAccount() {
        return payeeBankAccount;
    }

    public String getRejectionCause() {
        return rejectionCause;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    // public Float getTransactionPriceRate() {
    // return transactionPriceRate;
    // }
    //
    // public void setTransactionPriceRate(Float transactionPriceRate) {
    // this.transactionPriceRate = transactionPriceRate;
    // }
    //
    // public BigDecimal getTransactionPriceAmount() {
    // return transactionPriceAmount;
    // }
    //
    // public void setTransactionPriceAmount(BigDecimal transactionPriceAmount) {
    // this.transactionPriceAmount = transactionPriceAmount;
    // }
    //
    // public Float getCompanyQuotationRate() {
    // return companyQuotationRate;
    // }
    //
    // public void setCompanyQuotationRate(Float companyQuotationRate) {
    // this.companyQuotationRate = companyQuotationRate;
    // }
    //
    // public BigDecimal getCompanyQuotationAmount() {
    // return companyQuotationAmount;
    // }
    //
    // public void setCompanyQuotationAmount(BigDecimal companyQuotationAmount) {
    // this.companyQuotationAmount = companyQuotationAmount;
    // }
    //
    // public Mode getMode() {
    // return mode;
    // }
    //
    // public void setMode(Mode mode) {
    // this.mode = mode;
    // }
    //
    // public BigDecimal getLargeAmountInterest() {
    // return largeAmountInterest;
    // }
    //
    // public void setLargeAmountInterest(BigDecimal largeAmountInterest) {
    // this.largeAmountInterest = largeAmountInterest;
    // }
    //
    // public BigDecimal getSmallAmountInterest() {
    // return smallAmountInterest;
    // }
    //
    // public void setSmallAmountInterest(BigDecimal smallAmountInterest) {
    // this.smallAmountInterest = smallAmountInterest;
    // }
    //
    // public BigDecimal getDeductedInterestAmount() {
    // return deductedInterestAmount;
    // }
    //
    // public void setDeductedInterestAmount(BigDecimal deductedInterestAmount) {
    // this.deductedInterestAmount = deductedInterestAmount;
    // }
    //
    // public BigDecimal getWireTransferEnquiryFee() {
    // return wireTransferEnquiryFee;
    // }
    //
    // public void setWireTransferEnquiryFee(BigDecimal wireTransferEnquiryFee) {
    // this.wireTransferEnquiryFee = wireTransferEnquiryFee;
    // }

    // public Type getType() {
    // return type;
    // }
    //
    // public void setType(Type type) {
    // this.type = type;
    // }
    //
    // public LargePayableAmount getLargePayableAmount() {
    // return largePayableAmount;
    // }
    //
    // public void setLargePayableAmount(LargePayableAmount largePayableAmount) {
    // this.largePayableAmount = largePayableAmount;
    // }
    //
    // public BigDecimal getSmallPayableAmount() {
    // return smallPayableAmount;
    // }
    //
    // public void setSmallPayableAmount(BigDecimal smallPayableAmount) {
    // this.smallPayableAmount = smallPayableAmount;
    // }

    // public List<ProductStock> getStocks() {
    // return stocks;
    // }
    //
    // public void setStocks(List<ProductStock> stocks) {
    // this.stocks = stocks;
    // }
    public Status getStatus() {
        return status;
    }

    public Integer getUsance() {
        return usance;
    }

    public boolean isActive() {
        return active;
    }

    public void setAcceptanceAgreementNumber(String acceptanceAgreementNumber) {
        this.acceptanceAgreementNumber = acceptanceAgreementNumber;
    }

    // public LocalDate getDiscountDate() {
    // return discountDate;
    // }
    //
    // public void setDiscountDate(LocalDate discountDate) {
    // this.discountDate = discountDate;
    // }
    //
    // public Integer getAdjustDays() {
    // return adjustDays;
    // }
    //
    // public void setAdjustDays(Integer adjustDays) {
    // this.adjustDays = adjustDays;
    // }
    //
    // public UserAccount getBillTicketClerk() {
    // return billTicketClerk;
    // }
    //
    // public void setBillTicketClerk(UserAccount billTicketClerk) {
    // this.billTicketClerk = billTicketClerk;
    // }

    public void setAcceptingBank(Bank acceptingBank) {
        this.acceptingBank = acceptingBank;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setDenomination(BigDecimal denomination) {
        this.denomination = denomination;
    }

    public void setEnterprise(Enterprise enterprise) {
        this.enterprise = enterprise;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setFinancingPurposes(String financingPurposes) {
        this.financingPurposes = financingPurposes;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoiceRate(Float invoiceRate) {
        this.invoiceRate = invoiceRate;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public void setIssuerBankAccount(String issuerBankAccount) {
        this.issuerBankAccount = issuerBankAccount;
    }

    public void setIssuingDate(LocalDate issuingDate) {
        this.issuingDate = issuingDate;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public void setPayeeBank(Bank payeeBank) {
        this.payeeBank = payeeBank;
    }

    public void setPayeeBankAccount(String payeeBankAccount) {
        this.payeeBankAccount = payeeBankAccount;
    }

    public void setRejectionCause(String rejectionCause) {
        this.rejectionCause = rejectionCause;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUsance(Integer usance) {
        this.usance = usance;
    }

    public BigDecimal getInvoiceInterestAmount() {
        return invoiceInterestAmount;
    }

    public void setInvoiceInterestAmount(BigDecimal invoiceInterestAmount) {
        this.invoiceInterestAmount = invoiceInterestAmount;
    }

    @Override
    public String toString() {
        return "Bill [image=" + image + ", serialNumber=" + serialNumber + ", issuingDate=" + issuingDate + ", issuer="
                + issuer + ", issuerBankAccount=" + issuerBankAccount + ", payee=" + payee + ", payeeBankAccount="
                + payeeBankAccount + ", payeeBank=" + payeeBank + ", acceptingBank=" + acceptingBank
                + ", denomination=" + denomination + ", expirationDate=" + expirationDate
                + ", acceptanceAgreementNumber=" + acceptanceAgreementNumber + ", invoiceDate=" + invoiceDate
                + ", usance=" + usance + ", invoiceRate=" + invoiceRate + ", invoiceInterestAmount="
                + invoiceInterestAmount + ", enterprise=" + enterprise + ", financingPurposes=" + financingPurposes
                + ", rejectionCause=" + rejectionCause + ", status=" + status + ", active=" + active + "]";
    }

}
