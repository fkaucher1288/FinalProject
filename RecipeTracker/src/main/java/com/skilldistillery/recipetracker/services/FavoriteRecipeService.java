package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.FavoriteRecipe;

public interface FavoriteRecipeService {
	
	FavoriteRecipe createRecipeFavorite(FavoriteRecipe fr);
	
	FavoriteRecipe updateRecipeFavorite(FavoriteRecipe fr);

	List<FavoriteRecipe> getAllByUserId(int userId);
}
