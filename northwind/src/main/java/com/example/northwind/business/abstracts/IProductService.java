package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.entities.concretes.Product;

public interface IProductService {
	
	List<Product> getAll();
	Product getById(@PathVariable(value="product_id")Integer productId) throws Exception;
	Map<String, Product> add(@RequestBody Product product) throws Exception;
	Map<String,Product> update(Product productToUpdate,@RequestBody Product product) throws Exception;
	Map<String,Product> delete(Product productToDelete) throws Exception;

}
