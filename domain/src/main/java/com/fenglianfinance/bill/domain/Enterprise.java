package com.fenglianfinance.bill.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Embedded;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Table(name = "enterprises")
public class Enterprise extends AuditableEntity<UserAccount, Long> {

    public Enterprise() {
    }

    public Enterprise(String name, String license, String contacts, String fax, String accountPay,
            String accountPayeeName, String bankAccountName, String legalPersonFile, String organizationFile,
            String certificateFile, String licenseFile, String licenseAddressFile, Type type, String organization,
            String legalPersonCard, String tel, String email, AccountTypes accountType, String accountLicense,
            Address registerAddress, Address officeAddress, Address bankAddress, boolean active,
            String bankCode, VerifyStatus verifyState, String verifyCause, String qq, UserAccount userAccount,
            List<String> pictures,String taxCode) {
        super();
        this.name = name;
        this.license = license;
        this.contacts = contacts;
        this.fax = fax;
        this.accountPay = accountPay;
        this.accountPayeeName = accountPayeeName;
        this.bankAccountName = bankAccountName;
        this.legalPersonFile = legalPersonFile;
        this.organizationFile = organizationFile;
        this.certificateFile = certificateFile;
        this.licenseFile = licenseFile;
        this.licenseAddressFile = licenseAddressFile;
        this.type = type;
        this.organization = organization;
        this.legalPersonCard = legalPersonCard;
        this.tel = tel;
        this.email = email;
        this.accountType = accountType;
        this.accountLicense = accountLicense;
        this.taxCode = taxCode;
        this.registerAddress = registerAddress;
        this.officeAddress = officeAddress;
        this.bankAddress = bankAddress;
        this.active = active;
        this.bankCode = bankCode;
        this.verifyState = verifyState;
        this.verifyCause = verifyCause;
        this.qq = qq;
        this.userAccount = userAccount;
        this.pictures = pictures;
    }



    /**
     * <p>
     * Field serialVersionUID: serialVersionUID
     * </p>
     */
    private static final long serialVersionUID = 1L;

    public enum Type {

        PERNAS, // 国有制
        COLLECTIVE_OWNERSHIP, // 集体所有制
        STOCKHOLDING_SYSTEM, // 股份制
        JOINT_OPERATION, // 联营企业
        WFOE, // 外商独资
        SINO_FOREIGN_JOINT_VENTURE, // 中外合资
        HKMT, // 港澳台合资
        STOCK_COOPERATIVE; // 股份合作
    }

    public enum VerifyStatus {

        PENDING, APPROVED, REJECTED, DISCARD;
    }

    public enum AccountTypes {

        DEBIT_CARD, // 借记卡
        BANK_BOOK; // 存折
    }

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "license", length = 100, nullable = false)
    private String license;

    @Column(name = "contacts", length = 80)
    private String contacts;

    @Column(name = "fax", length = 40)
    private String fax;

    @Column(name = "account_pay", length = 100, nullable = false)
    private String accountPay;

    @Column(name = "account_payee_name", length = 100, nullable = false)
    private String accountPayeeName;

    @Column(name = "bank_account_name", length = 100, nullable = false)
    private String bankAccountName;

    @Column(name = "legal_person_file", length = 200)
    private String legalPersonFile;

    @Column(name = "organization_file", length = 200)
    private String organizationFile;

    @Column(name = "certificate_file", length = 80)
    private String certificateFile;

    @Column(name = "license_file", length = 200)
    private String licenseFile;

    @Column(name = "license_address_file", length = 200)
    private String licenseAddressFile;

    @Enumerated(EnumType.STRING)
    @Column(name = "enterprise_type", length = 20)
    private Type type;

    @Column(name = "organization", length = 80)
    private String organization;

    @Column(name = "legal_person_Card", length = 30)
    private String legalPersonCard;

    @Column(name = "tel", length = 80)
    private String tel;

    @Column(name = "email", length = 80)
    @Email
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", length = 30)
    private AccountTypes accountType;

    @Column(name = "account_license", length = 100)
    private String accountLicense;

    @Column(name = "tax_code", length = 100)
    private String taxCode;

    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name = "street", column = @Column(name = "register_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "register_zipcode")),
        @AttributeOverride(name = "city", column = @Column(name = "register_city"))})
    private Address registerAddress;

    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name = "street", column = @Column(name = "office_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "office_zipcode")),
        @AttributeOverride(name = "city", column = @Column(name = "office_city"))})
    private Address officeAddress;

    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride(name = "street", column = @Column(name = "bank_street")),
        @AttributeOverride(name = "zipcode", column = @Column(name = "bank_zipcode")),
        @AttributeOverride(name = "city", column = @Column(name = "bank_city"))})
    private Address bankAddress;

    @Column(name = "is_active", length = 4)
    private boolean active = true;

    @Column(name = "bank_code", length = 40)
    private String bankCode;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "verify_state", length = 30)
    private VerifyStatus verifyState;

    @Column(name = "verify_cause", length = 100)
    private String verifyCause;

    @Column(name = "qq", length = 20)
    private String qq;

    @OneToOne(optional = true)
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(name = "upload_pictures", joinColumns = @JoinColumn(name = "upload_id"))
    private List<String> pictures = new ArrayList<>();

//    @Embedded
//    private AccountingInfo accountingInfo;

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    /**
     * @return the enterpriseName
     */
    public String getName() {
        return name;
    }

    /**
     * @return the license
     */
    public String getLicense() {
        return license;
    }

    /**
     * @param license the license to set
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * @return the contacts
     */
    public String getContacts() {
        return contacts;
    }

    /**
     * @param contacts the contacts to set
     */
    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }

    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     * @return the enterpriseType
     */
    public Type getEnterpriseType() {
        return type;
    }

    /**
     * @param enterpriseType the enterpriseType to set
     */
    public void setEnterpriseType(Type enterpriseType) {
        this.type = enterpriseType;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the accountLicense
     */
    public String getAccountLicense() {
        return accountLicense;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    /**
     * @param accountLicense the accountLicense to set
     */
    public void setAccountLicense(String accountLicense) {
        this.accountLicense = accountLicense;
    }

    public String getAccountPayeeName() {
        return this.accountPayeeName;
    }

    public void setAccountPayeeName(String accountPayeeName) {
        this.accountPayeeName = accountPayeeName;
    }

    public String getBankAccountName() {
        return this.bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }

    public String getLegalPersonFile() {
        return this.legalPersonFile;
    }

    public void setLegalPersonFile(String legalPersonFile) {
        this.legalPersonFile = legalPersonFile;
    }

    public String getOrganizationFile() {
        return this.organizationFile;
    }

    public void setOrganizationFile(String organizationFile) {
        this.organizationFile = organizationFile;
    }

    public String getCertificateFile() {
        return this.certificateFile;
    }

    public void setCertificateFile(String certificateFile) {
        this.certificateFile = certificateFile;
    }

    public String getOrganization() {
        return this.organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getLegalPersonCard() {
        return this.legalPersonCard;
    }

    public void setLegalPersonCard(String legalPersonCard) {
        this.legalPersonCard = legalPersonCard;
    }

    public String getTel() {
        return this.tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getLicenseFile() {
        return this.licenseFile;
    }

    public void setLicenseFile(String licenseFile) {
        this.licenseFile = licenseFile;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public VerifyStatus getVerifyState() {
        return verifyState;
    }

    public void setVerifyState(VerifyStatus verifyState) {
        this.verifyState = verifyState;
    }

    public String getVerifyCause() {
        return this.verifyCause;
    }

    public void setVerifyCause(String verifyCause) {
        this.verifyCause = verifyCause;
    }

    public String getAccountPay() {
        return this.accountPay;
    }

    public void setAccountPay(String accountPay) {
        this.accountPay = accountPay;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Address getRegisterAddress() {
        return registerAddress;
    }

    public void setRegisterAddress(Address registerAddress) {
        this.registerAddress = registerAddress;
    }

    public Address getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(Address officeAddress) {
        this.officeAddress = officeAddress;
    }

    public Address getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(Address bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getLicenseAddressFile() {
        return licenseAddressFile;
    }

    public void setLicenseAddressFile(String licenseAddressFile) {
        this.licenseAddressFile = licenseAddressFile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQq() {
        return this.qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public AccountTypes getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypes accountType) {
        this.accountType = accountType;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "Enterprise [name=" + name + ", license=" + license + ", contacts=" + contacts + ", fax=" + fax
                + ", accountPay=" + accountPay + ", accountPayeeName=" + accountPayeeName + ", bankAccountName="
                + bankAccountName + ", legalPersonFile=" + legalPersonFile + ", organizationFile=" + organizationFile
                + ", certificateFile=" + certificateFile + ", licenseFile=" + licenseFile + ", licenseAddressFile="
                + licenseAddressFile + ", type=" + type + ", organization=" + organization + ", legalPersonCard="
                + legalPersonCard + ", tel=" + tel + ", email=" + email + ", accountType=" + accountType
                + ", accountLicense=" + accountLicense + ", taxCode=" + taxCode + ", registerAddress="
                + registerAddress + ", officeAddress=" + officeAddress + ", bankAddress=" + bankAddress + ", active="
                + active + ", bankCode=" + bankCode + ", verifyState=" + verifyState + ", verifyCause=" + verifyCause
                + ", qq=" + qq + ", userAccount=" + userAccount + ", pictures=" + pictures + "]";
    }

//    public AccountingInfo getAccountingInfo() {
//        return accountingInfo;
//    }
//
//    public void setAccountingInfo(AccountingInfo accountingInfo) {
//        this.accountingInfo = accountingInfo;
//    }



}
