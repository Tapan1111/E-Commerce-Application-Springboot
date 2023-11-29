package com.ecommerce.project5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.project5.model.Category;
import com.ecommerce.project5.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	public void createCategory(Category category) {
		categoryRepository.save(category);

	}

	public List<Category> listCategory() {
		return categoryRepository.findAll();
	}

	public void editCategory(int categoryId, Category category) {
		Category editableCategory = categoryRepository.getReferenceById(categoryId);
		editableCategory.setCategoryName(category.getCategoryName());
		editableCategory.setDescription(category.getDescription());
		editableCategory.setImageUrl(category.getImageUrl());
		categoryRepository.save(editableCategory);
	}

	public boolean findById(int categoryId) {
		return categoryRepository.findById(categoryId).isPresent();
	}

}
