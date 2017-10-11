package com.oms.order.service;

import com.oms.order.model.domain.Order;
import com.oms.order.model.entity.OrderEntity;
import com.oms.order.model.request.OrderRequest;
import com.oms.order.model.request.OrderStatusUpdate;
import com.oms.order.model.request.OrderUpdate;
import com.oms.order.model.response.OrderResponse;
import com.oms.order.repository.OrderRepository;
import com.oms.order.repository.OrderRepositoryCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    private OrderRepositoryCustom orderRepositoryCustom;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderResponse addOrder(OrderRequest orderRequest) {
        List<Order> orderList = orderRequest.getOrderList();
        List<OrderEntity> orderEntityList = new ArrayList<>();
        orderList.forEach(order -> {
            OrderEntity orderEntity = Order.domainToEntity(order);
            orderEntityList.add(orderEntity);
        });
        List<OrderEntity> orderEntityListInserted = orderRepository.insert(orderEntityList);
        LOGGER.info("message=addOrder{}", orderEntityListInserted);
        return buildOrderResponse(orderEntityListInserted);
    }

    @Override
    public OrderResponse getOrderById(String orderId) {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        Order orderDomain = OrderEntity.entityToDomain(orderEntity);
        List<Order> orderDomainList = new ArrayList<>();
        orderDomainList.add(orderDomain);
        LOGGER.info("message=getOrderById{}", orderDomainList);
        return new OrderResponse().setOrderList(orderDomainList);
    }

    @Override
    public void deleteOrderById(String orderId) {
        orderRepository.delete(orderId);
    }

    @Override
    public OrderResponse getOrderByCustomerId(String customerId) {
        List<OrderEntity> orderEntityListInserted = orderRepository.findByCustomerId(customerId);
        LOGGER.info("message=getOrderByCustomerId{}", orderEntityListInserted);
        return buildOrderResponse(orderEntityListInserted);
    }

    private OrderResponse buildOrderResponse(List<OrderEntity> orderEntities) {
        OrderResponse orderResponse = new OrderResponse();
        List<Order> orderDomainList = new ArrayList<>();
        orderEntities.forEach(orderEntity -> {
            Order orderDomain = OrderEntity.entityToDomain(orderEntity);
            orderDomainList.add(orderDomain);
        });
        LOGGER.info("message=orderDomainList{}", orderDomainList);
        return orderResponse.setOrderList(orderDomainList);
    }

    @Override
    public OrderResponse getOrderByOrderIdCustomerId(String orderId, String customerId) {
        OrderEntity orderEntity = orderRepository.findByOrderIdCustomerId(orderId, customerId);
        Order orderDomain = OrderEntity.entityToDomain(orderEntity);
        List<Order> orderDomainList = new ArrayList<>();
        orderDomainList.add(orderDomain);
        LOGGER.info("message=getOrderByOrderIdCustomerId() orderDomainList{}", orderDomainList);
        return new OrderResponse().setOrderList(orderDomainList);
    }

    @Override
    public void updateOrder(OrderUpdate orderUpdate, String orderId) {
        orderRepositoryCustom.updateOrder(orderUpdate, orderId);
    }

    @Override
    public void updateOrderStatus(OrderStatusUpdate orderStatusUpdate) {
        orderRepositoryCustom.updateOrderStatus(orderStatusUpdate.getOrderId(), orderStatusUpdate.getStatus());
    }
}
