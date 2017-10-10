package com.oms.order.model.domain;

public class Payment {

    String paymentMode;
    String paymentStatus;

    public String getPaymentMode() {
        return paymentMode;
    }

    public Payment setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public Payment setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }
}
