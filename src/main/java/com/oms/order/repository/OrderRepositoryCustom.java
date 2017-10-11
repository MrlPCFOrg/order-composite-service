package com.oms.order.repository;


import com.oms.order.model.request.OrderUpdate;

public interface OrderRepositoryCustom {
    void updateOrderStatus(String orderId, String status);

    void updateOrder(OrderUpdate orderUpdate, String orderId);
}
