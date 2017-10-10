package com.oms.order.repository;

import com.oms.order.model.entity.OrderEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {

    List<OrderEntity> findByCustomerId(String customerId);

    OrderEntity findByOrderId(String orderId);

    @Query(value = "{'orderId':?0, 'customerId':?1}")
    OrderEntity findByOrderIdCustomerId(String orderId, String customerId);
}
