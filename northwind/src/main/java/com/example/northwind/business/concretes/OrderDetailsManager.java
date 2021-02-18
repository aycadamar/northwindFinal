package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.business.abstracts.IOrderDetailsService;
import com.example.northwind.dataaccess.concretes.OrderDetailsRepository;
import com.example.northwind.entities.concretes.OrderDetails;

@Service
public class OrderDetailsManager implements IOrderDetailsService{

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	
	@Override
	public List<OrderDetails> getAll() {
		return orderDetailsRepository.findAll();
	}

	@Override
	public OrderDetails getById(@PathVariable(value="order_details_id")Integer orderDetailsId) throws Exception {
		OrderDetails orderDetailsInfo = orderDetailsRepository
				.findById(orderDetailsId).orElseThrow(()->new Exception ("No order details with id : "+orderDetailsId));
		return orderDetailsInfo;
	}

	@Override
	public Map<String, OrderDetails> add(@RequestBody OrderDetails orderDetails) throws Exception {
		orderDetailsRepository.save(orderDetails);
		Map<String,OrderDetails> response = new HashMap<>();
		response.put("Added succesfully : ", orderDetails);
		return response;
	}

	@Override
	public Map<String, OrderDetails> update(OrderDetails orderDetailsToUpdate, OrderDetails orderDetails) throws Exception {
		orderDetailsToUpdate.setOrderId(orderDetails.getOrderId());
		orderDetailsToUpdate.setProductId(orderDetails.getProductId());
		orderDetailsToUpdate.setUnitPrice(orderDetails.getUnitPrice());
		orderDetailsToUpdate.setQuantity(orderDetails.getQuantity());
		orderDetailsToUpdate.setDiscount(orderDetails.getDiscount());
		
		orderDetailsRepository.save(orderDetailsToUpdate);
		Map<String,OrderDetails> response = new HashMap<>();
		response.put("Order details with id : "+orderDetailsToUpdate.getId()+" is updated.", orderDetailsToUpdate);
		return response;
	}

	@Override
	public Map<String, OrderDetails> delete(OrderDetails orderDetailsToDelete){
		
		orderDetailsRepository.delete(orderDetailsToDelete);
			
	    Map<String,OrderDetails> response = new HashMap<>();
		response.put("Order details with id  "+orderDetailsToDelete.getId()+" is deleted.", orderDetailsToDelete);
		return response;
	}
	
	@Override
	public void deleteOrderDetails(@PathVariable(value="order_id")int orderId) {
		getAll().stream().filter(orderDetail -> orderDetail.getOrderId() == orderId).forEach(orderDetail -> {
			delete(orderDetail);
		});		
	}

	
}
