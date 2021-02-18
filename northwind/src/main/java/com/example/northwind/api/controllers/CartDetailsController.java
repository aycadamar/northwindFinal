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

import com.example.northwind.business.abstracts.ICartDetailService;
import com.example.northwind.entities.concretes.CartDetail;

@RestController
@RequestMapping("/api/v1")
public class CartDetailsController {

	@Autowired
	ICartDetailService cartDetailService;
	
	@GetMapping("/cart_details")
	public List<CartDetail> getAll(){
		return cartDetailService.getAll();
	}
	
	@GetMapping("/cart_details/{cart_id}")
	public List<CartDetail> getAllByCartId(@PathVariable(value="cart_id")Integer cartId){
		return cartDetailService.getAllByCartId(cartId);		
	}
	
	@PostMapping("/cart_details")
	public Map<String, CartDetail> add(@RequestBody CartDetail cartDetail) throws Exception {
		return cartDetailService.add(cartDetail);
	}
	
	@PutMapping("/cart_details/{cart_details_id}")
	public Map<String, CartDetail> update(@PathVariable(value="cart_details_id")Integer cartDetailId,@RequestBody CartDetail cartDetail)throws Exception{
		List<CartDetail> cartInfos = cartDetailService.getAll();
		CartDetail cartDetailToUpdate = null;
		for(CartDetail cartInfo : cartInfos) {
			if(cartInfo.getId() == cartDetailId) {
				cartDetailToUpdate = cartInfo;
			}
		}
		return cartDetailService.update(cartDetailToUpdate, cartDetail);
	}
	
	@DeleteMapping("/cart_details/{cart_id}/{product_id}/{amount}")
	public Map<String, CartDetail> deleteCertainAmount(@PathVariable(value="cart_id")Integer cartId,@PathVariable(value="product_id")Integer productId,@PathVariable(value="amount") Integer deleteAmount){
		return cartDetailService.deleteCertainAmount(cartId,productId, deleteAmount);
	}
	
	@DeleteMapping("/cart_details/{cart_id}")
	public String deleteAllCartDetail(@PathVariable(value = "cart_id") Integer cartId) {
		return cartDetailService.deleteAllCartDetail(cartId);
	}
	
}
