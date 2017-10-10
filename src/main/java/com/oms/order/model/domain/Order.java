package com.oms.order.model.domain;

import com.google.common.base.MoreObjects;
import com.oms.order.model.entity.OrderEntity;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderId;
    private String orderStatus;
    private Date createdDate;
    private Date updatedDate;
    private String customerId;
    private String totalCost;

    private List<Product> products;
    private List<Payment> paymentList;
    private List<Shipping> shippingList;
    private List<Tracking> trackingList;
    private List<Invoice> invoiceList;

    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Order setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Order setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Order setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public Order setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
        return this;
    }

    public List<Shipping> getShippingList() {
        return shippingList;
    }

    public Order setShippingList(List<Shipping> shippingList) {
        this.shippingList = shippingList;
        return this;
    }

    public List<Tracking> getTrackingList() {
        return trackingList;
    }

    public Order setTrackingList(List<Tracking> trackingList) {
        this.trackingList = trackingList;
        return this;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public Order setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public Order setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public Order setTotalCost(String totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public Order setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public static OrderEntity domainToEntity(Order orderDomain) {
        OrderEntity orderEntity = new OrderEntity()
                .setOrderStatus(orderDomain.orderStatus)
                .setCreatedDate(new Date())
                .setCustomerId(orderDomain.getCustomerId())
                .setTotalCost(orderDomain.getTotalCost())
                .setPaymentList(orderDomain.getPaymentList())
                .setShippingList(orderDomain.getShippingList())
                .setTrackingList(orderDomain.getTrackingList())
                .setInvoiceList(orderDomain.getInvoiceList());
        return orderEntity;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orderId", orderId)
                .add("orderStatus", orderStatus)
                .add("createdDate", createdDate)
                .add("customerId", customerId)
                .add("totalCost", totalCost)
                .add("products", products)
                .add("paymentList", paymentList)
                .add("shippingList", shippingList)
                .add("trackingList", trackingList)
                .add("invoiceList", invoiceList)
                .add("updatedDate", updatedDate)
                .toString();
    }
}
