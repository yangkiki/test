package com.fenglianfinance.bill.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class RoomForm implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public enum BreakfastType {

        NA, SINGLE, DOUBLE;
    }

    private String name;

    private IdValue bedType;

    private double area;

    private boolean allowSmoking;

    private boolean provideInternetService;

    private String breakfastType;

    //private BigDecimal breakfastPrice ;
    private BigDecimal price;

    private BigDecimal weekendPrice;

    private BigDecimal holidayPrice;

    private int availables = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

//    public BigDecimal getBreakfastPrice() {
//        return breakfastPrice;
//    }
//
//    public void setBreakfastPrice(BigDecimal breakfastPrice) {
//        this.breakfastPrice = breakfastPrice;
//    }
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

    public IdValue getBedType() {
        return bedType;
    }

    public void setBedType(IdValue bedType) {
        this.bedType = bedType;
    }

    public String getBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(String breakfastType) {
        this.breakfastType = breakfastType;
    }

    @Override
    public String toString() {
        return "RoomForm{" + "name=" + name + ", bedType=" + bedType + ", area=" + area + ", allowSmoking=" + allowSmoking + ", provideInternetService=" + provideInternetService + ", breakfastType=" + breakfastType + ", price=" + price + ", weekendPrice=" + weekendPrice + ", holidayPrice=" + holidayPrice + ", availables=" + availables  + '}';
    }

}
