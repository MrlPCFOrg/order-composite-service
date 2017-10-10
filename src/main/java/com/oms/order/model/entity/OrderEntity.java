package com.oms.order.model.entity;

import com.google.common.base.MoreObjects;
import com.oms.order.model.domain.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "order")
public class OrderEntity {

    @Id
    private String orderId;
    private String orderStatus;

    @DateTimeFormat
    private Date createdDate;

    @DateTimeFormat
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

    public OrderEntity setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public OrderEntity setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public OrderEntity setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getCustomerId() {
        return customerId;
    }

    public OrderEntity setCustomerId(String customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public OrderEntity setTotalCost(String totalCost) {
        this.totalCost = totalCost;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public OrderEntity setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public OrderEntity setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
        return this;
    }

    public List<Shipping> getShippingList() {
        return shippingList;
    }

    public OrderEntity setShippingList(List<Shipping> shippingList) {
        this.shippingList = shippingList;
        return this;
    }

    public List<Tracking> getTrackingList() {
        return trackingList;
    }

    public OrderEntity setTrackingList(List<Tracking> trackingList) {
        this.trackingList = trackingList;
        return this;
    }

    public List<Invoice> getInvoiceList() {
        return invoiceList;
    }

    public OrderEntity setInvoiceList(List<Invoice> invoiceList) {
        this.invoiceList = invoiceList;
        return this;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public OrderEntity setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
        return this;
    }

    public static Order entityToDomain(OrderEntity orderEntity) {
        return new Order()
                .setOrderId(orderEntity.getOrderId())
                .setCreatedDate(orderEntity.getCreatedDate())
                .setCustomerId(orderEntity.getCustomerId())
                .setTotalCost(orderEntity.getTotalCost())
                .setUpdatedDate(orderEntity.getUpdatedDate())
                .setOrderStatus(orderEntity.getOrderStatus())
                .setPaymentList(orderEntity.getPaymentList())
                .setShippingList(orderEntity.getShippingList())
                .setTrackingList(orderEntity.getTrackingList())
                .setInvoiceList(orderEntity.getInvoiceList());
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
