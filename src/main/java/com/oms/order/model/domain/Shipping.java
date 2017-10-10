package com.oms.order.model.domain;

import com.google.common.base.MoreObjects;

public class Shipping {

    String customerName;
    String address;
    String landmark;
    String city;
    String region;
    String state;
    String country;

    public String getCustomerName() {
        return customerName;
    }

    public Shipping setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Shipping setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getLandmark() {
        return landmark;
    }

    public Shipping setLandmark(String landmark) {
        this.landmark = landmark;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Shipping setCity(String city) {
        this.city = city;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public Shipping setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getState() {
        return state;
    }

    public Shipping setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Shipping setCountry(String country) {
        this.country = country;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("customerName", customerName)
                .add("address", address)
                .add("landmark", landmark)
                .add("city", city)
                .add("region", region)
                .add("state", state)
                .add("country", country)
                .toString();
    }
}
