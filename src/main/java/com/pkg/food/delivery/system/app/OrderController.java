package com.pkg.food.delivery.system.app;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pkg.food.delivery.system.app.exception.OrderDetailsNotFoundException;
import com.pkg.food.delivery.system.app.model.OrderEntity;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderRepository orderRepository;

	// Method to get all order details
	@RequestMapping(path = "/getAllOrders", consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderEntity> getAllOrders() {
		return orderRepository.findAll();

	}

	// Method to get order detail based on order id
	@RequestMapping(path = "/orders/{orderId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public OrderEntity getOrderById(@PathVariable int orderId) {
		OrderEntity orderItem = orderRepository.findOne(orderId);
		if (orderItem == null)
			throw new OrderDetailsNotFoundException("Order doesnot exist with this order id " + orderId);
		return orderItem;

	}

	// method to place new order.
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.POST, value = "/addOrder", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity addOrder(@RequestBody OrderEntity order) {
		orderRepository.save(order);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(order.getOrderId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
