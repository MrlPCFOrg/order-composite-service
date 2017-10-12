package com.oms.order.service;

import com.oms.order.model.domain.Order;
import com.oms.order.model.domain.OrderMessage;
import com.oms.order.model.entity.OrderEntity;
import com.oms.order.model.request.OrderRequest;
import com.oms.order.model.request.OrderUpdate;
import com.oms.order.model.response.CustomerCustom;
import com.oms.order.model.response.CustomerResponse;
import com.oms.order.model.response.OrderResponse;
import com.oms.order.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    RestTemplate restTemplate;

    MessageDelegate messageDelegate;

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Value("${url.customerServiceURL}")
    String customerServiceURL;

    public OrderServiceImpl(OrderRepository orderRepository, MessageDelegate messageDelegate) {
        this.orderRepository = orderRepository;
        this.messageDelegate = messageDelegate;
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
        OrderEntity insteredOrder = orderEntityListInserted.get(0);
        LOGGER.info("message=addOrder{}", orderEntityListInserted);

        OrderMessage orderMessage = new OrderMessage()
                .setOrderId(insteredOrder.getOrderId())
                .setCustomerName(getCustomerDetails(insteredOrder.getCustomerId()).getCustomerName())
                .setCustomerEmailId(getCustomerDetails(insteredOrder.getCustomerId()).getEmail())
                .setTotalCost(insteredOrder.getTotalCost())
                .setPaymentMode(insteredOrder.getPaymentList().get(0).getPaymentMode())
                .setPaymentStatus(insteredOrder.getPaymentList().get(0).getPaymentStatus());

        messageDelegate.sendMessage(orderMessage);

        return buildOrderResponse(orderEntityListInserted);
    }

    private CustomerCustom getCustomerDetails(String customerId) {
        CustomerCustom customerCustom = restTemplate.getForObject(customerServiceURL, CustomerCustom.class, customerId);
            customerCustom.getCustomerName();
            customerCustom.getPhoneNo();
            customerCustom.getEmail();
        return customerCustom;
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
    public OrderResponse updateOrder(OrderUpdate orderUpdate) {
        LOGGER.info("message=updateOrder()");
        return null;
    }

}
