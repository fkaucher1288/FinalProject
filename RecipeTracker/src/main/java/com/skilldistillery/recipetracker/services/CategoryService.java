package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.Category;
import com.skilldistillery.recipetracker.entities.CategoryType;

public interface CategoryService {
	
	public List<Category> index();
	List<CategoryType> findCategoryTypeByCategoryId(int categoryId);

}
