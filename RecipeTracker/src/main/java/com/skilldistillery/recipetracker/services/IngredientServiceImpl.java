package com.skilldistillery.recipetracker.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.repositories.IngredientRepository;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {

	@Autowired
	private IngredientRepository repo;
	
	@Override
	public List<Ingredient> getAll() {
		return repo.findAll();
	}
	
	@Override
	public List<Ingredient> get(int... ids) {
		return repo.findByIdIn(ids);
	}

}
