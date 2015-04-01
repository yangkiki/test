package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class RoomDetails implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private NameValue bedType;

    private int beds;

    private double area;

    private boolean allowSmoking;

    private boolean provideInternetService;

    private String breakfastType;

    private BigDecimal breakfastPrice = BigDecimal.ZERO;

    private BigDecimal price = BigDecimal.ZERO;

    private BigDecimal weekendPrice = BigDecimal.ZERO;

    private BigDecimal holidayPrice = BigDecimal.ZERO;

    private int availables = 0;

    private NameValue hotel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public boolean isAllowSmoking() {
        return allowSmoking;
    }

    public void setAllowSmoking(boolean allowSmoking) {
        this.allowSmoking = allowSmoking;
    }

    public boolean isProvideInternetService() {
        return provideInternetService;
    }

    public void setProvideInternetService(boolean provideInternetService) {
        this.provideInternetService = provideInternetService;
    }

    public BigDecimal getBreakfastPrice() {
        return breakfastPrice;
    }

    public void setBreakfastPrice(BigDecimal breakfastPrice) {
        this.breakfastPrice = breakfastPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getWeekendPrice() {
        return weekendPrice;
    }

    public void setWeekendPrice(BigDecimal weekendPrice) {
        this.weekendPrice = weekendPrice;
    }

    public BigDecimal getHolidayPrice() {
        return holidayPrice;
    }

    public void setHolidayPrice(BigDecimal holidayPrice) {
        this.holidayPrice = holidayPrice;
    }

    public int getAvailables() {
        return availables;
    }

    public void setAvailables(int availables) {
        this.availables = availables;
    }

    public String getBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(String breakfastType) {
        this.breakfastType = breakfastType;
    }

    public NameValue getBedType() {
        return bedType;
    }

    public void setBedType(NameValue bedType) {
        this.bedType = bedType;
    }

    public NameValue getHotel() {
        return hotel;
    }

    public void setHotel(NameValue hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "RoomDetails{" + "id=" + id + ", name=" + name + ", bedType=" + bedType + ", beds=" + beds + ", area=" + area + ", allowSmoking=" + allowSmoking + ", provideInternetService=" + provideInternetService + ", breakfastType=" + breakfastType + ", breakfastPrice=" + breakfastPrice + ", price=" + price + ", weekendPrice=" + weekendPrice + ", holidayPrice=" + holidayPrice + ", availables=" + availables + ", hotel=" + hotel + '}';
    }

}
