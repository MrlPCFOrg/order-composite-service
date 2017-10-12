package com.oms.order.controller;

import com.oms.order.model.request.OrderRequest;
import com.oms.order.model.request.OrderUpdate;
import com.oms.order.model.response.OrderResponse;
import com.oms.order.service.OrderService;
import com.oms.order.service.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse addOrder(@RequestBody OrderRequest orderRequest) {
        LOGGER.info("message=addOrder{}", orderRequest);
        return orderService.addOrder(orderRequest);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(@PathVariable String orderId) {
        LOGGER.info("message=getOrderById{}", orderId);
        return orderService.getOrderById(orderId);
    }

    @DeleteMapping("/delete/{orderId}")
    public void deleteOrderById(@PathVariable String orderId) {
        LOGGER.info("message=deleteOrderById{}", orderId);
        orderService.deleteOrderById(orderId);
    }

    @GetMapping("/customer/{customerId}")
    public OrderResponse getOrderByCustomerId(@PathVariable String customerId) {
        LOGGER.info("message=getOrderByCustomerId{}", customerId);
        return orderService.getOrderByCustomerId(customerId);
    }

    @GetMapping("/{orderId}/customer/{customerId}")
    public OrderResponse getOrderByOrderIdCustomerId(@PathVariable String orderId, @PathVariable String customerId) {
        LOGGER.info("message=getOrderByOrderId{} and CustomerId{}", orderId, customerId);
        return orderService.getOrderByOrderIdCustomerId(orderId, customerId);
    }

    @PatchMapping("/{orderId}/update")
    public OrderResponse updateOrder(@RequestBody OrderUpdate orderUpdate){
        LOGGER.info("message=updateOrder() orderUpdate{}", orderUpdate);
        return orderService.updateOrder(orderUpdate);
    }


}
