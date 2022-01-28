package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.RecipeRating;

public interface RecipeRatingService {
	
	RecipeRating createRecipeRating(RecipeRating rating);
	RecipeRating updateRecipeRating(RecipeRating rating);
	List<RecipeRating> getAllRecipeRatings();

}
