package com.example.northwind.business.abstracts;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.entities.concretes.Category;

public interface ICategoryService {

	List<Category> getAll();
	Category getById(@PathVariable(value="category_id")Integer categoryId) throws Exception;
	Map<String,Boolean> add(@RequestBody Category category);
	Map<String,Category> update(Category categoryToUpdate,@RequestBody Category category) throws Exception;
	Map<String,Category> delete(Category categoryToDelete) throws Exception;
}
