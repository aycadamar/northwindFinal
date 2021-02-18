package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.northwind.business.abstracts.ICartService;
import com.example.northwind.dataaccess.concretes.CartRepository;
import com.example.northwind.entities.concretes.Cart;

@Service
public class CartManager implements ICartService {

	@Autowired
	private CartRepository cartRepository;

	@Override
	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	@Override
	public Cart getById(@PathVariable(value = "cart_id") Integer cartId) throws Exception {
		Cart cartInfo = cartRepository.findById(cartId).orElseThrow(() -> new Exception("No cart with id : " + cartId));
		return cartInfo;
	}

	@Override
	public Cart getByCustomerId(@PathVariable(value = "customer_id") String customerId) {
		List<Cart> allCarts = cartRepository.findAll();
		Cart cartInfo = null;
		for (Cart cart : allCarts) {
			if (cart.getCustomerId().equals(customerId)) {
				cartInfo = cart;
			}
		}
		return cartInfo;
	}

	@Override
	public Map<String, Cart> add(Cart cart) throws Exception {
		Cart cartInfo = getByCustomerId(cart.getCustomerId());
		if (cartInfo != null) {
			throw new Exception("Customer allready has a cart");
		}
		cartRepository.save(cart);
		Map<String, Cart> response = new HashMap<>();
		response.put("Added successfully : ", cart);
		return response;

	}

	@Override
	public Map<String, Cart> update(Cart cartToUpdate, Cart cart) throws Exception {
		cartToUpdate.setCustomerId(cart.getCustomerId());
		cartToUpdate.setTotalPrice(cart.getTotalPrice());
		cartRepository.save(cartToUpdate);
		Map<String, Cart> response = new HashMap<>();
		response.put("Cart with id : " + cartToUpdate.getId() + " is updated.", cartToUpdate);
		return response;
	}

	@Override
	public Map<String, Cart> delete(Cart cartToDelete) throws Exception {
		cartRepository.delete(cartToDelete);

		Map<String, Cart> response = new HashMap<>();
		response.put("Cart with id  " + cartToDelete.getId() + " is deleted.", cartToDelete);
		return response;
	}

	@Override
	public void changeTotalPrice(int cartId, double addedPrice) throws Exception {
		getById(cartId).setTotalPrice(addedPrice);
	}

}
