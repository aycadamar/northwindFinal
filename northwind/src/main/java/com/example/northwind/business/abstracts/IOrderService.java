package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.entities.concretes.Order;

public interface IOrderService {

	List<Order> getAll();
	Order getById(@PathVariable(value="order_id")Integer orderId) throws Exception;
	Map<String,Order> add(@RequestBody Order order) throws Exception;
	Map<String,Order> update(Order orderToUpdate,@RequestBody Order order) throws Exception;
	Map<String,Order> delete(Order orderToDelete) throws Exception;

}
