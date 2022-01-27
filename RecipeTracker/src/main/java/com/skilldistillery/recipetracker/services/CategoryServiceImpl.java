package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Category;
import com.skilldistillery.recipetracker.entities.CategoryType;
import com.skilldistillery.recipetracker.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository catRepo;
	
	@Override
	public List<Category> index() {
		return catRepo.findAll();
	}

	@Override
	public List<CategoryType> findCategoryTypeByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

}