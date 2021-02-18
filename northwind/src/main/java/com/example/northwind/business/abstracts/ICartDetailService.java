package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.entities.concretes.CartDetail;

public interface ICartDetailService {

	List<CartDetail> getAll();
	List<CartDetail> getAllByCartId(@PathVariable(value="cart_id")Integer cartId) ;
	Map<String,CartDetail> add(@RequestBody CartDetail cartDetail) throws Exception;
	Map<String,CartDetail> update(CartDetail cartDetailToUpdate,@RequestBody CartDetail cartDetail) throws Exception;
	Map<String,CartDetail> delete(CartDetail cartDetailToDelete) throws Exception;
	String deleteAllCartDetail(@PathVariable(value="cart_id")Integer cartId);
	Map<String, CartDetail> deleteCertainAmount(Integer cartId, Integer productId, Integer deleteAmount);

}
