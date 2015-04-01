package com.fenglianfinance.bill.domain;

import static com.fenglianfinance.bill.domain.UserAccount.Gender.NA;
import static com.fenglianfinance.bill.domain.UserAccount.Type.USER;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fenglianfinance.bill.domain.support.AuditableEntity;
import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
//import java.util.ListIterator;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(//
        name = "users",//
        uniqueConstraints = {//

            @UniqueConstraint(columnNames = {"username","mobile_number"})//
        }//
)
@Access(AccessType.FIELD)
public class UserAccount extends AuditableEntity<UserAccount, Long> implements UserDetails {

    public enum Gender {

        NA, //
        MALE, //
        FAMALE;//
    }

    public enum Type {

        USER, //
        ENTERPRISE, //
        STAFF//
    }

    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender = NA;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private Type type = USER;

    @Column(name = "is_locked")
    private boolean locked = false;

    @Embedded
    @AttributeOverride(name = "verifiedDate", column = @Column(name = "id_verified_date"))
    private IdCardVerification idCardVerification;

    @Embedded
    @AttributeOverride(name = "verifiedDate", column = @Column(name = "mobile_verified_date"))
    private MobileNumberVerification mobileNumberVerification;

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(//
            name = "user_roles", //
            joinColumns = @JoinColumn(name = "user_id")//
    )
    private List<String> roles = new ArrayList<>();

    @Column(name = "total_order_amount")
    private BigDecimal totalOrderAmount = BigDecimal.ZERO;

    @Column(name = "total_payment_amount")
    private BigDecimal totalPaymentAmount = BigDecimal.ZERO;

    @Embedded
    private AccountingInfo accountingInfo;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_bank_cards",
//            joinColumns = {
//                @JoinColumn(name = "user_id")
//            }
//    )
//    private List<BankCardInfo> cards = new ArrayList<>();
    public UserAccount() {
    }

    public UserAccount(String username, String password, String name, boolean locked, UserAccount.Type type, String... roles) {
        super();
        this.username = username;
        this.password = password;
        this.name = name;
        this.roles = Arrays.asList(roles);
        this.locked = locked;
        this.type = type;
    }

//    public List<BankCardInfo> getCards() {
//        return cards;
//    }
//
//    public void setCards(List<BankCardInfo> cards) {
//        this.cards = cards;
//    }
//
//    public void addBankCardInfo(BankCardInfo bankCardInfo) {
//        if (!this.cards.contains(bankCardInfo)) {
//            this.cards.add(bankCardInfo);
//        }
//    }
//
//    public void removeBankCardInfo(String bankId) {
//        ListIterator<BankCardInfo> cardsIterator = this.cards.listIterator();
//        while (cardsIterator.hasNext()) {
//            if (cardsIterator.next().getBankId().equals(bankId)) {
//                cardsIterator.remove();
//            }
//        }
//
//    }
    public AccountingInfo getAccountingInfo() {
        return accountingInfo;
    }

    public void setAccountingInfo(AccountingInfo accountingInfo) {
        this.accountingInfo = accountingInfo;
    }

    public BigDecimal getTotalOrderAmount() {
        return totalOrderAmount;
    }

    public void setTotalOrderAmount(BigDecimal totalOrderedAmount) {
        this.totalOrderAmount = totalOrderedAmount;
    }

    public BigDecimal getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(BigDecimal totalPurchasedAmount) {
        this.totalPaymentAmount = totalPurchasedAmount;
    }

    public synchronized void addRole(String r) {
        if (!this.roles.contains(r)) {
            this.roles.add(r);
        }
    }

    public synchronized void removeRole(String r) {
        if (this.roles.contains(r)) {
            this.roles.remove(r);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public boolean hasUserRole() {
        for (String r : this.getRoles()) {
            if ("USER".equals(r)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return (!this.locked);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public IdCardVerification getIdCardVerification() {
        return idCardVerification;
    }

    public void setIdCardVerification(IdCardVerification idCardVerification) {
        this.idCardVerification = idCardVerification;
    }

    public MobileNumberVerification getMobileNumberVerification() {
        return mobileNumberVerification;
    }

    public void setMobileNumberVerification(MobileNumberVerification mobileNumberVerification) {
        this.mobileNumberVerification = mobileNumberVerification;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

}