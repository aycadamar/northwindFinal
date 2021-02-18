package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.entities.concretes.Cart;

public interface ICartService {

	List<Cart> getAll();
	Cart getById(@PathVariable(value="cart_id")Integer cartId) throws Exception;
	Cart getByCustomerId(@PathVariable(value="customer_id")String customerId) ;
	Map<String,Cart> add(@RequestBody Cart cart) throws Exception;
	Map<String,Cart> update(Cart cartToUpdate,@RequestBody Cart cart) throws Exception;
	Map<String,Cart> delete(Cart cartToDelete) throws Exception;
	void changeTotalPrice(int cartId, double addedPrice) throws Exception;
}
