package com.cg.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cg.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, Integer> {

}
