package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.DietPlanRecipe;

public interface DietPlanRecipeService {
	
	DietPlanRecipe createRecipeReview(DietPlanRecipe dpr);
	
	DietPlanRecipe updateRecipeReview(DietPlanRecipe dpr);

	List<DietPlanRecipe> getAllRecipeReviews();
}
