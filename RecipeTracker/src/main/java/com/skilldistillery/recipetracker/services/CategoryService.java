package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.Category;
import com.skilldistillery.recipetracker.entities.CategoryType;

public interface CategoryService {
	
	public Category findCategoryById (int categoryId);
	public List<Category> showAllCategories();
	List<CategoryType> findCategoryTypeByCategoryId(int categoryId);

}
