package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.DietPlanIngredient;

public interface DietPlanIngredientService {
	DietPlanIngredient createRecipeReview(DietPlanIngredient dpi);
	
	DietPlanIngredient updateRecipeReview(DietPlanIngredient dpi);

	List<DietPlanIngredient> getAllRecipeReviews();
}
