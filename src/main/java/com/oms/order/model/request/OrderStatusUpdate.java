package com.oms.order.model.request;

public class OrderStatusUpdate {
    private String orderId;
    private String orderStatus;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return orderStatus;
    }

    public void setStatus(String status) {
        this.orderStatus = status;
    }
}
