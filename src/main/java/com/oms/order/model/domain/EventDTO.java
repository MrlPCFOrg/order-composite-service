package com.oms.order.model.domain;


import java.util.Date;

public class EventDTO {
    private String id;
    private String eventName;
    private String status;
    private String orderId;
    private Date createdDate;
    private Date lastModifiedDate;


    public String getId() {
        return id;
    }

    public EventDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getEventName() {
        return eventName;
    }

    public EventDTO setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public EventDTO setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public EventDTO setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public EventDTO setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public EventDTO setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }
}
