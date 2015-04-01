package com.moxian.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class HotelForm implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private IdValue category;

    private IdValue brand;

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

    private IdValue provider;

    private int builtYear;
    
    private int lastDecoratedYear;

    private BigDecimal price;

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

    public IdValue getCategory() {
        return category;
    }

    public void setCategory(IdValue category) {
        this.category = category;
    }

    public IdValue getBrand() {
        return brand;
    }

    public void setBrand(IdValue brand) {
        this.brand = brand;
    }

    public IdValue getProvider() {
        return provider;
    }

    public void setProvider(IdValue provider) {
        this.provider = provider;
    }

    public int getLastDecoratedYear() {
        return lastDecoratedYear;
    }

    public void setLastDecoratedYear(int lastDecoratedYear) {
        this.lastDecoratedYear = lastDecoratedYear;
    }

    public String getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(String starLevel) {
        this.starLevel = starLevel;
    }

    @Override
    public String toString() {
        return "HotelForm [category=" + category + ", brand=" + brand + ", name=" + name + ", starLevel=" + starLevel
                + ", address=" + address + ", landlineNumber=" + landlineNumber + ", coverPicture=" + coverPicture
                + ", pictures=" + pictures + ", description=" + description + ", feature=" + feature + ", facility="
                + facility + ", amenity=" + amenity + ", notes=" + notes + ", transportation=" + transportation
                + ", managingOrganization=" + managingOrganization + ", provider=" + provider + ", builtYear="
                + builtYear + ", lastDecoratedYear=" + lastDecoratedYear + ", price=" + price + "]";
    }
    
    
}
