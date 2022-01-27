package com.skilldistillery.recipetracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Category;
import com.skilldistillery.recipetracker.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repo;
	
	@Override
	public Category findById(int id) {
		Optional<Category> op = repo.findById(id);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public List<Category> showAllCategories() {
		return repo.findAll();
	}

}