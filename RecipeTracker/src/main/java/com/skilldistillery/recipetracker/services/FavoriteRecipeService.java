package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.FavoriteRecipe;

public interface FavoriteRecipeService {
	
	FavoriteRecipe createRecipeReview(FavoriteRecipe fr);
	
	FavoriteRecipe updateRecipeReview(FavoriteRecipe fr);

	List<FavoriteRecipe> getAllRecipeReviews();
}
