package com.cg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.model.OrderDetails;

public interface OrderRepository extends MongoRepository<OrderDetails, Integer> {
	OrderDetails findByOrderId(String order_id);
}
