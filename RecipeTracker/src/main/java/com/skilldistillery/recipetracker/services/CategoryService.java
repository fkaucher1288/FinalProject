package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.Category;

public interface CategoryService {
	
	public Category findById (int id);
	public List<Category> showAllCategories();

}
