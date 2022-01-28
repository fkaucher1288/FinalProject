package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.repositories.RecipeIngredientRepository;

public class RecipeIngredientServiceImpl implements RecipeIngredientService {
	@Autowired
	private RecipeIngredientRepository riRepo;

	@Override
	public RecipeIngredient createRecipeReview(RecipeIngredient ri) {
		return riRepo.saveAndFlush(ri);
	}

	@Override
	public RecipeIngredient updateRecipeReview(RecipeIngredient ri) {
		
		return riRepo.saveAndFlush(ri);
	}

	@Override
	public List<RecipeIngredient> getAllRecipeReviews() {
		return riRepo.findAll();
	}

}
