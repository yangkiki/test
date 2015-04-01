/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.fenglianfinance.bill.domain;

import com.fenglianfinance.bill.domain.support.AuditableEntity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author hansy
 */
@Entity
@Table(name = "banks")
public class Bank extends AuditableEntity<UserAccount, Long> {

    public enum Type {
        NA,//
        NATIONAL, //
        SHAREHOLDING
    }

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "bank_type")
    private Type type;

    @Column(name = "bank_code")
    private String code;

    @Embedded
    private Address address;

    @Column(name = "province")
    private String province;

    @Column(name = "landline_number")
    private String landlineNumber;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "contact_person")
    private String contactPerson;

    @Column(name = "notes")
    @Size(max = 2000)
    private String notes;

    @Column(name = "is_active")
    private boolean active = true;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    
    @Override
    public String toString() {
        return "Bank{" + "name=" + name + ", type=" + type + ", code=" + code + ", address=" + address + ", province="
                + province + ", landlineNumber=" + landlineNumber + ", contactPerson=" + contactPerson + ", notes="
                + notes + '}';
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + (active ? 1231 : 1237);
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((contactPerson == null) ? 0 : contactPerson.hashCode());
        result = prime * result + ((landlineNumber == null) ? 0 : landlineNumber.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((notes == null) ? 0 : notes.hashCode());
        result = prime * result + ((province == null) ? 0 : province.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Bank other = (Bank) obj;
        if (active != other.active) {
            return false;
        }
        if (address == null) {
            if (other.address != null) {
                return false;
            }
        } else if (!address.equals(other.address)) {
            return false;
        }
        if (code == null) {
            if (other.code != null) {
                return false;
            }
        } else if (!code.equals(other.code)) {
            return false;
        }
        if (contactPerson == null) {
            if (other.contactPerson != null) {
                return false;
            }
        } else if (!contactPerson.equals(other.contactPerson)) {
            return false;
        }
        if (landlineNumber == null) {
            if (other.landlineNumber != null) {
                return false;
            }
        } else if (!landlineNumber.equals(other.landlineNumber)) {
            return false;
        }
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (notes == null) {
            if (other.notes != null) {
                return false;
            }
        } else if (!notes.equals(other.notes)) {
            return false;
        }
        if (province == null) {
            if (other.province != null) {
                return false;
            }
        } else if (!province.equals(other.province)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

}
