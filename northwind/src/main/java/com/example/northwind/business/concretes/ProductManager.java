package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.northwind.business.abstracts.IProductService;
import com.example.northwind.dataaccess.concretes.ProductRepository;
import com.example.northwind.entities.concretes.Product;

@Service
public class ProductManager implements IProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> getAll() {
		
		return productRepository.findAll();
	}

	@Override
	public Product getById(@PathVariable(value="product_id")Integer productId) throws Exception {
		Product productInfo = productRepository.findById(productId)
				.orElseThrow(()->new Exception ("No product with id : "+productId));
		return productInfo;
	}

	@Override
	public Map<String, Product> add(Product product) throws Exception {
		if(product.getProductName().length()<2) {
			throw new Exception("Product name lenght can't be less than 2");
		}
		List<Product> products = productRepository.findAll();
		int counter = 0;
		for(Product control : products) {
			if(control.getCategoryId() == product.getCategoryId()) {
				counter++;
			}
		}
		if(counter > 9) {
			throw new Exception("There can be at most 10 products in each category");
			
		}
		productRepository.save(product);
		Map<String,Product> response = new HashMap<>();
		response.put("Added successfully : ", product);
		return response;
	}

	@Override
	public Map<String, Product> update(Product productToUpdate, Product product) throws Exception {
		
		productToUpdate.setCategoryId(product.getCategoryId());
		productToUpdate.setProductName(product.getProductName());
		productToUpdate.setUnitPrice(product.getUnitPrice());
		productToUpdate.setQuantityPerUnit(product.getQuantityPerUnit());
		
		productRepository.save(productToUpdate);
		Map<String,Product> response = new HashMap<>();
		response.put("Product with id : "+productToUpdate.getId()+" is updated.", product);
		return response;
	}

	@Override
	public Map<String, Product> delete(Product productToDelete) throws Exception {
		
		productRepository.delete(productToDelete);
		
		Map<String,Product> response = new HashMap<>();
		response.put("Product with id  "+productToDelete.getId()+" is deleted.", productToDelete);
		return response;
	}

}
