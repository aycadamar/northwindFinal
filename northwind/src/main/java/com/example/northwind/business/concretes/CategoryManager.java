package com.example.northwind.business.concretes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.northwind.business.abstracts.ICategoryService;
import com.example.northwind.dataaccess.concretes.CategoryRepository;
import com.example.northwind.entities.concretes.Category;


@Service
public class CategoryManager implements ICategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public List<Category> getAll() {

		return categoryRepository.findAll();
	}

	@Override
	public Category getById(@PathVariable(value="category_id")Integer categoryId)throws Exception{
		Category categoryInfo = categoryRepository.findById(categoryId)
				.orElseThrow(()->new Exception ("No category with id : "+categoryId));
		return categoryInfo;
	}

	@Override
	public Map<String,Boolean> add(@RequestBody Category category) {

		categoryRepository.save(category);
		Map<String,Boolean> response = new HashMap<>();
		response.put(category.getCategoryName()+" is added with category id : " + category.getId(),Boolean.TRUE);
		return response;
	}
	
	@Override
	public Map<String,Category> update(Category categoryToUpdate,@RequestBody Category category)throws Exception{

		categoryToUpdate.setCategoryName(category.getCategoryName());
		categoryToUpdate.setDescription(category.getDescription());
		
		categoryRepository.save(categoryToUpdate);
		Map<String,Category> response = new HashMap<>();
		response.put("Category with id : "+categoryToUpdate.getId()+" is updated.", categoryToUpdate);
		return response;
	}
	
	@Override
	public Map<String,Category> delete(Category categoryToDelete) {
		
		categoryRepository.delete(categoryToDelete);
		
		Map<String,Category> response = new HashMap<>();
		response.put("Category with id "+categoryToDelete.getId()+" is deleted.", categoryToDelete);
		return response;
	}
	
}
