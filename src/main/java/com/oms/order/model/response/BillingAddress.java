package com.oms.order.model.response;

public class BillingAddress {

    private String id;
    private String address;
    private String city;
    private String state;
    private String country;
    private String phoneNo;

    public String getId() {
        return id;
    }

    public BillingAddress setId(String id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BillingAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getState() {
        return state;
    }

    public BillingAddress setState(String state) {
        this.state = state;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public BillingAddress setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public BillingAddress setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }
}
