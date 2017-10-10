package com.oms.order.model.domain;

import com.google.common.base.MoreObjects;

public class Tracking {
    String company;
    String trackingNumber;
    String status;
    String estimatedDelivery;

    public String getCompany() {
        return company;
    }

    public Tracking setCompany(String company) {
        this.company = company;
        return this;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Tracking setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Tracking setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public Tracking setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
        return this;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("company", company)
                .add("trackingNumber", trackingNumber)
                .add("status", status)
                .add("estimatedDelivery", estimatedDelivery)
                .toString();
    }
}
