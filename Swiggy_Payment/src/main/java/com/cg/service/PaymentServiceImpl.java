package com.cg.service;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.model.Cart;
import com.cg.model.OrderDetails;
import com.cg.model.Payment;
import com.cg.repository.CartRepository;
import com.cg.repository.OrderRepository;
import com.cg.repository.PaymentRepository;
import com.razorpay.*;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	
	@Autowired
	private PaymentRepository pRepo;
	
	@Autowired
	private CartRepository cRepo;
	
	@Autowired
	private OrderRepository oRepo;

	@Override
	public String placeFood(Payment payment) throws Exception {
		Cart c = cRepo.findById(payment.getCart_id()).get();
		payment.setCart(c);
		pRepo.save(payment);
		double amount = payment.getCart().getAmount();
		
		var client = new RazorpayClient("rzp_test_3NqsJYAcKTqGKa", "OXS0OMjHMQbNUYqId2LOMm0n");
		
		JSONObject ob = new JSONObject();
		ob.put("amount", amount*100);
		ob.put("currency", "INR");
		ob.put("receipt", "txn_683");
		
		
		//creating order
		Order order = client.orders.create(ob);
		
		
		
		
		
		System.out.println(order);
		
		//save in database
		OrderDetails o = new OrderDetails();
		o.setId(payment.getPid());
		o.setStatus(order.get("status"));
		o.setAmount((int)order.get("amount")/100);
		o.setOrderId(order.get("id"));
//		o.setPaymentId(null);
		o.setReceipt(order.get("receipt"));
		o.setUser(c.getUser());
		
		oRepo.save(o);
		
		//Fetch order by id
		
//		Order order2 = client.orders.fetch("order_JTtK7xUIKN0sqW");
//		List<Order> order3 = client.orders.fetchAll();
//		System.out.println(order3);
		
		
		
		return order.toString();
	}

}
