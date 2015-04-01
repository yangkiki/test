package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HotelDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private NameValue category;

    private NameValue brand;

    private String name;

    private String starLevel;

    private AddressInfo address;

    private String landlineNumber;

    private String coverPicture;

    private List<String> pictures = new ArrayList<>();

    private String description;

    private String feature;

    private String facility;

    private String amenity;

    private String notes;

    private String transportation;

    private String managingOrganization;

    private NameValue provider;

    private int builtYear;

    private BigDecimal price;

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

    public AddressInfo getAddress() {
        return address;
    }

    public void setAddress(AddressInfo address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getBuiltYear() {
        return builtYear;
    }

    public void setBuiltYear(int builtYear) {
        this.builtYear = builtYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getLandlineNumber() {
        return landlineNumber;
    }

    public void setLandlineNumber(String landlineNumber) {
        this.landlineNumber = landlineNumber;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTransportation() {
        return transportation;
    }

    public void setTransportation(String transportation) {
        this.transportation = transportation;
    }

    public String getManagingOrganization() {
        return managingOrganization;
    }

    public void setManagingOrganization(String managingOrganization) {
        this.managingOrganization = managingOrganization;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    public NameValue getCategory() {
        return category;
    }

    public void setCategory(NameValue category) {
        this.category = category;
    }

    public NameValue getBrand() {
        return brand;
    }

    public void setBrand(NameValue brand) {
        this.brand = brand;
    }

    public NameValue getProvider() {
        return provider;
    }

    public void setProvider(NameValue provider) {
        this.provider = provider;
    }

}
