package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.business.abstracts.ICartDetailService;
import com.example.northwind.dataaccess.concretes.CartDetailRepository;
import com.example.northwind.entities.concretes.CartDetail;


@Service

public class CartDetailManager implements ICartDetailService {

	@Autowired
	private CartDetailRepository cartDetailRepository;

	@Autowired
	private CartManager cartManager;

	@Autowired
	private ProductManager productManager;

	@Override
	public List<CartDetail> getAll() {
		return cartDetailRepository.findAll();
	}

	@Override
	public List<CartDetail> getAllByCartId(Integer cartId) {
		return getAll().stream().filter(cartDetail -> cartDetail.getCartId() == cartId).collect(Collectors.toList());
	}

	@Override
	public Map<String, CartDetail> add(@RequestBody CartDetail cartDetail) throws Exception {
		List<CartDetail> cartInfos = getAllByCartId(cartDetail.getCartId());
		double unitPrice = productManager.getById(cartDetail.getProductId()).getUnitPrice();
		cartDetail.setUnitPrice(unitPrice);
		int quantity = 1;
		boolean flag = false;
		Map<String, CartDetail> response = new HashMap<>();
		for (CartDetail cartInfo : cartInfos) {
			if (cartInfo.getProductId() == cartDetail.getProductId()) {	
				quantity = (cartInfo.getQuantity() + 1);
				cartInfo.setQuantity(quantity);
				cartDetailRepository.save(cartInfo);
				response.put("Added successfully : ", cartInfo);
				flag = true;
			}
		}	
		if(!flag) {
			cartDetail.setQuantity(quantity);
			cartDetailRepository.save(cartDetail);
			response.put("Added successfully : ", cartDetail);
		}
		cartManager.changeTotalPrice(cartDetail.getCartId(), unitPrice);	
		return response;
	}

	@Override
	public Map<String, CartDetail> update(CartDetail cartDetailToUpdate, CartDetail cartDetail) throws Exception {
		cartDetailToUpdate.setCartId(cartDetail.getCartId());
		cartDetailToUpdate.setProductId(cartDetail.getProductId());
		cartDetailToUpdate.setQuantity(cartDetail.getQuantity());
		
		Map<String,CartDetail> response = new HashMap<>();
		response.put("Cart details with id : "+cartDetailToUpdate.getId()+" is updated.", cartDetailToUpdate);
		return response;
	}

	@Override
	public Map<String, CartDetail> delete(CartDetail cartDetailToDelete) throws Exception {
		cartDetailRepository.delete(cartDetailToDelete);
		Map<String, CartDetail> response = new HashMap<>();
		response.put("Cart detail with id  " + cartDetailToDelete.getId() + " is deleted.", cartDetailToDelete);
		return response;
	}

	@Override
	public Map<String, CartDetail> deleteCertainAmount(Integer cartId,Integer productId, Integer deleteAmount) {
		List<CartDetail> cartDetails = getAllByCartId(cartId);
		Map<String, CartDetail> response = new HashMap<>();
		for(CartDetail cartDetail : cartDetails) {
			if(cartDetail.getProductId() == productId) {
				cartDetail.setQuantity(cartDetail.getQuantity() - deleteAmount);
				response.put(deleteAmount + " items is deleted.", cartDetail);
			}
		}		
		return response;
	}

	@Override
	public String deleteAllCartDetail(@PathVariable(value = "cart_id") Integer cartId) {
		List<CartDetail> cartDetails = getAllByCartId(cartId);
		for (CartDetail cartDetail : cartDetails) {
			cartDetailRepository.delete(cartDetail);
		}
		String printStatement = "Cart Details with cart id " + cartId + " is deleted";
		return printStatement;
	}

}
