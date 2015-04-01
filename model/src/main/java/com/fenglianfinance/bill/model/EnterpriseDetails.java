package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnterpriseDetails implements Serializable {

    private static final long serialVersionUID = 123725354963534360L;

    private Long id;

    private String name;

    private String license;

    private String contacts;

    private String fax;

    private String accountPayeeName;

    private String bankAccountName;

    private String legalPersonFile;

    private String organizationFile;

    private String certificateFile;

    private String type;

    private String organization;

    private String legalPersonCard;

    private String tel;

    private String email;

    private String licenseFile;

    private String accountLicense;

    private String licenseAddressFile;

    private boolean active = true;

    private String verifyState;

    private String verifyCause;

    private String qq;

    private AddressInfo registerAddress;

    private AddressInfo officeAddress;

    private AddressInfo bankAddress;

    private String accountPay;

    private String accountType;

    private List<String> pictures = new ArrayList<>();

    private String bankCode;

    private String taxCode;

    private UserAccountDetails userAccount;

    public UserAccountDetails getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccountDetails userAccount) {
        this.userAccount = userAccount;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    private AccountingInfoDetails accountingInfo;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountPay() {
        return accountPay;
    }

    public void setAccountPay(String accountPay) {
        this.accountPay = accountPay;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAccountPayeeName() {
        return accountPayeeName;
    }

    public void setAccountPayeeName(String accountPayeeName) {
        this.accountPayeeName = accountPayeeName;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getLegalPersonFile() {
        return legalPersonFile;
    }

    public void setLegalPersonFile(String legalPersonFile) {
        this.legalPersonFile = legalPersonFile;
    }

    public String getOrganizationFile() {
        return organizationFile;
    }

    public void setOrganizationFile(String organizationFile) {
        this.organizationFile = organizationFile;
    }

    public String getCertificateFile() {
        return certificateFile;
    }

    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLegalPersonCard() {
        return legalPersonCard;
    }

    public void setLegalPersonCard(String legalPersonCard) {
        this.legalPersonCard = legalPersonCard;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseFile() {
        return licenseFile;
    }

    public void setLicenseFile(String licenseFile) {
        this.licenseFile = licenseFile;
    }

    public String getAccountLicense() {
        return accountLicense;
    }

    public void setAccountLicense(String accountLicense) {
        this.accountLicense = accountLicense;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(String verifyState) {
        this.verifyState = verifyState;
    }

    public String getVerifyCause() {
        return verifyCause;
    }

    public void setVerifyCause(String verifyCause) {
        this.verifyCause = verifyCause;
    }

    public String getLicenseAddressFile() {
        return licenseAddressFile;
    }

    public void setLicenseAddressFile(String licenseAddressFile) {
        this.licenseAddressFile = licenseAddressFile;
    }

    public AddressInfo getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(AddressInfo registerAddress) {
        this.registerAddress = registerAddress;
    }

    public AddressInfo getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(AddressInfo officeAddress) {
        this.officeAddress = officeAddress;
    }

    public AddressInfo getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(AddressInfo bankAddress) {
        this.bankAddress = bankAddress;
    }

    public AccountingInfoDetails getAccountingInfo() {
        return accountingInfo;
    }

    public void setAccountingInfo(AccountingInfoDetails accountingInfo) {
        this.accountingInfo = accountingInfo;
    }

}
