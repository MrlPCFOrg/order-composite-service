package com.oms.order.model.response;

import com.oms.order.model.domain.Order;


import java.util.List;

public class OrderResponse {
    private List<Order> orderList;

    public List<Order> getOrderList() {
        return orderList;
    }

    public OrderResponse setOrderList(List<Order> orderList) {
        this.orderList = orderList;
        return this;
    }
}
