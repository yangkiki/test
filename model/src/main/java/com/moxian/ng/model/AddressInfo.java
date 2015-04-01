package com.moxian.ng.model;

public class AddressInfo {

    private String street;

    private String zipcode;

    private String city;

    private String state;

    private String country;

    private LocationValue location;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocationValue getLocation() {
        return location;
    }

    public void setLocation(LocationValue location) {
        this.location = location;
    }

}
