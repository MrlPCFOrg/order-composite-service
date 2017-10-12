package com.oms.order.model.response;

public class CustomerCustom {

    private String customerName;
    private String email;
    private String phoneNo;


    public String getCustomerName() {
        return customerName;
    }

    public CustomerCustom setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public CustomerCustom setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public CustomerCustom setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
        return this;
    }
}
