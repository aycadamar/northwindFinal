package com.example.northwind.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.entities.concretes.Order;


@RestController
@RequestMapping("/api/v1")
public class OrdersController {
	
	@Autowired
	IOrderService orderService;
	
	@GetMapping("/orders")
	public List<Order> getAll() {
		return orderService.getAll();
	}

	@GetMapping("/orders/{order_id}")
	public Order getById(@PathVariable(value="order_id")Integer orderId) throws Exception {
		return orderService.getById(orderId);
	}

	@PostMapping("/orders")
	public Map<String, Order> add(@RequestBody Order order) throws Exception {
		return orderService.add(order);
	}
		
	@PutMapping("/orders/{order_id}")
	public Map<String, Order> update(@PathVariable(value="order_id")Integer orderId,@RequestBody Order order)throws Exception{
		Order orderToUpdate = orderService.getById(orderId);
		return orderService.update(orderToUpdate, order);
	}
	
	@DeleteMapping("/orders/{order_id}")
	public Map<String, Order> delete(@PathVariable(value="order_id")Integer orderId) throws Exception {
		Order orderToDelete = orderService.getById(orderId);
		return orderService.delete(orderToDelete);
	}
}
