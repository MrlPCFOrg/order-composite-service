package com.oms.order.model.request;

import com.oms.order.model.domain.Order;

import java.util.List;

public class OrderRequest {

    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public OrderRequest setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        return this;
    }
}
