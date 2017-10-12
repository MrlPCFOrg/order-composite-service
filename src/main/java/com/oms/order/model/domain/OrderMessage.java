package com.oms.order.model.domain;

import java.util.Date;

public class OrderMessage {

    private String orderId;
    private String customerName;
    private String customerEmailId;
    private String totalCost;

    private String paymentMode;
    private String paymentStatus;

    private String invoiceNumber;
    private Date invoiceDate;

    private String productId;

    private Date lastModifiedDate;


    public String getOrderId() {
        return orderId;
    }

    public OrderMessage setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public OrderMessage setCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public OrderMessage setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
        return this;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public OrderMessage setTotalCost(String totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public OrderMessage setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
        return this;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public OrderMessage setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
        return this;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public OrderMessage setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public OrderMessage setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
        return this;
    }

    public String getProductId() {
        return productId;
    }

    public OrderMessage setProductId(String productId) {
        this.productId = productId;
        return this;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public OrderMessage setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }
}
