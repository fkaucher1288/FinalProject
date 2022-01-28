package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.RecipeIngredient;

public interface RecipeIngredientService {

	RecipeIngredient createRecipeReview(RecipeIngredient ri);
	
	RecipeIngredient updateRecipeReview(RecipeIngredient ri);

	List<RecipeIngredient> getAllRecipeReviews();
}
