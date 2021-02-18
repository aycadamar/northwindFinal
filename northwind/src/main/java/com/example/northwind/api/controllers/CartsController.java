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

import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.entities.concretes.Cart;

@RestController
@RequestMapping("/api/v1")
public class CartsController {

	@Autowired
	ICartService cartService;
	
	@GetMapping("/carts")
	public List<Cart> getAll() {
		return cartService.getAll();
	}
	
	@GetMapping("/carts/{cart_id}")
	public Cart getById(@PathVariable(value="cart_id")Integer cartId) throws Exception {
		return cartService.getById(cartId);
	}

	@PostMapping("/carts")
	public Map<String, Cart> add(@RequestBody Cart cart) throws Exception {
		return cartService.add(cart);
	}
		
	@PutMapping("/carts/{cart_id}")
	public Map<String, Cart> update(@PathVariable(value="cart_id")Integer cartId,@RequestBody Cart cart)throws Exception{
		Cart cartToUpdate = cartService.getById(cartId);
		return cartService.update(cartToUpdate, cart);
	}
	
	@DeleteMapping("/carts/{cart_id}")
	public Map<String, Cart> delete(@PathVariable(value="cart_id")Integer cartId) throws Exception {
		Cart cartToDelete = cartService.getById(cartId);
		return cartService.delete(cartToDelete);
	}
}
