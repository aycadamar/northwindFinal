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

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.entities.concretes.Category;


@RestController
@RequestMapping("/api/v1")
public class CategoriesController {
	
	@Autowired
	ICategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category> getAll(){
		return categoryService.getAll();
	}

	@GetMapping("/categories/{category_id}")
	public Category getById(@PathVariable(value="category_id")Integer categoryId) throws Exception{
		return categoryService.getById(categoryId);
	}
	
	@PostMapping("/categories")
	public Map<String,Boolean> add(@RequestBody Category category){
		return categoryService.add(category);
	}
	
	@PutMapping("/categories/{category_id}")
	public Map<String,Category> update(@PathVariable(value="category_id")Integer categoryId,@RequestBody Category category)throws Exception{
		Category categoryToUpdate = categoryService.getById(categoryId);
		
		return categoryService.update(categoryToUpdate, category);
	}
	
	@DeleteMapping("/categories/{category_id}")
	public Map<String,Category> delete(@PathVariable(value="category_id")Integer categoryId) throws Exception{
		Category categoryToDelete = categoryService.getById(categoryId);
		return categoryService.delete(categoryToDelete);
	}
	
}
