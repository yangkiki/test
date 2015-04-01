package com.fenglianfinance.bill.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class Location implements Serializable {

    private double latitude;

    private double longitude;

    public Location(){
        this.latitude=0.0d;
        this.longitude=0.0d;
    }
    public Location(double lat, double lon) {
        super();
        this.latitude = lat;
        this.longitude = lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
