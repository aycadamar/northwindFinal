package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.business.abstracts.IOrderService;
import com.example.northwind.dataaccess.concretes.OrderDetailsRepository;
import com.example.northwind.dataaccess.concretes.OrderRepository;
import com.example.northwind.entities.concretes.Order;
import com.example.northwind.entities.concretes.OrderDetails;

@Service
public class OrderManager implements IOrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartDetailManager cartDetailManager;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Autowired
	private CartManager cartManager;
	@Autowired
	private OrderDetailsManager orderDetailsManager;
	

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order getById(@PathVariable(value = "order_id") Integer orderId) throws Exception {
		Order orderInfo = orderRepository.findById(orderId)
				.orElseThrow(() -> new Exception("No order with id : " + orderId));
		return orderInfo;
	}

	@Override
	public Map<String, Order> add(@RequestBody Order order) throws Exception {
		orderRepository.save(order);
		int cartId = cartManager.getByCustomerId(order.getCustomerId()).getId();
		cartDetailManager.getAllByCartId(cartId).forEach(cartDetail -> {
			OrderDetails orderDetails = new OrderDetails();
			orderDetails.setOrderId(order.getId());
			orderDetails.setProductId(cartDetail.getProductId());
			orderDetails.setProductId(cartDetail.getProductId());
			orderDetails.setQuantity(cartDetail.getQuantity());
			orderDetails.setUnitPrice(cartDetail.getUnitPrice());
			orderDetailsRepository.save(orderDetails);
			
		});
		cartDetailManager.deleteAllCartDetail(cartId);
		Map<String, Order> response = new HashMap<>();
		response.put("Added succesfully : ", order);
		return response;
	}

	@Override
	public Map<String, Order> update(Order orderToUpdate, Order order) throws Exception {
		orderToUpdate.setCustomerId(order.getCustomerId());
		orderToUpdate.setEmployeeId(order.getEmployeeId());

		orderRepository.save(orderToUpdate);
		Map<String, Order> response = new HashMap<>();
		response.put("Order with id : " + orderToUpdate.getId() + " is updated.", orderToUpdate);
		return response;
	}

	@Override
	public Map<String, Order> delete(Order orderToDelete){

		orderRepository.delete(orderToDelete);
		orderDetailsManager.deleteOrderDetails(orderToDelete.getId());
		Map<String, Order> response = new HashMap<>();
		response.put("Order with id  " + orderToDelete.getId() + " is deleted.", orderToDelete);
		return response;
	}

}
