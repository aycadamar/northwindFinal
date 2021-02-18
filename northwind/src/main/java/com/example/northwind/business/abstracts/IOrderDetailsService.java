package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.entities.concretes.OrderDetails;

public interface IOrderDetailsService {

	List<OrderDetails> getAll();
	OrderDetails getById(@PathVariable(value="order_details_id")Integer orderDetailsId) throws Exception;
	Map<String,OrderDetails> add(@RequestBody OrderDetails orderDetails) throws Exception;
	Map<String,OrderDetails> update(OrderDetails orderDetailsToUpdate,@RequestBody OrderDetails orderDetails) throws Exception;
	Map<String,OrderDetails> delete(OrderDetails orderDetailsToDelete) throws Exception;
	void deleteOrderDetails(@PathVariable(value="order_id")int orderId);
}
