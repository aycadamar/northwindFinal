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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.northwind.business.abstracts.IOrderDetailsService;
import com.example.northwind.entities.concretes.OrderDetails;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailsController {

	@Autowired
	IOrderDetailsService orderDetailsService;
	
	@GetMapping("/orders_details")
	public List<OrderDetails> getAll() {
		return orderDetailsService.getAll();
	}

	@GetMapping("/orders_details/{order_details_id}")
	public OrderDetails getById(@PathVariable(value="order_details_id")Integer orderId) throws Exception {
		return orderDetailsService.getById(orderId);
	}

	@PostMapping("/orders_details")
	public Map<String, OrderDetails> add(@RequestBody OrderDetails orderDetails) throws Exception {
		return orderDetailsService.add(orderDetails);
	}
		
	@PutMapping("/orders_details/{order_details_id}")
	public Map<String, OrderDetails> update(@PathVariable(value="order_details_id")Integer orderDetailsId,@RequestBody OrderDetails orderDetails)throws Exception{
		OrderDetails orderDetailsToUpdate = orderDetailsService.getById(orderDetailsId);
		return orderDetailsService.update(orderDetailsToUpdate, orderDetails);
	}
	
	@DeleteMapping("/orders_details/{order_details_id}")
	public Map<String, OrderDetails> delete(@PathVariable(value="order_details_id")Integer orderDetailsId) throws Exception {
		OrderDetails orderDetailsToDelete = orderDetailsService.getById(orderDetailsId);
		return orderDetailsService.delete(orderDetailsToDelete);
	}
	
	@DeleteMapping("/orders_details")
	public void deleteOrderDetails(@RequestParam(value = "order_id")@PathVariable(value="order_id")int orderId) {
		orderDetailsService.deleteOrderDetails(orderId);
	}
}
