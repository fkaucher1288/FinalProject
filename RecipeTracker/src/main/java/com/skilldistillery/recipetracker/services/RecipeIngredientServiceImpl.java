package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.repositories.RecipeIngredientRepository;

public class RecipeIngredientServiceImpl implements RecipeIngredientService {
	@Autowired
	private RecipeIngredientRepository riRepo;

	@Override
	public RecipeIngredient createRecipeIngredient(RecipeIngredient ri) {
		return riRepo.saveAndFlush(ri);
	}

	@Override
	public RecipeIngredient updateRecipeIngredient(RecipeIngredient ri) {
		
		return riRepo.saveAndFlush(ri);
	}

	@Override
	public List<RecipeIngredient> getAllRecipeIngredient() {
		return riRepo.findAll();
	}

}
