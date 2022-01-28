package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.RecipeIngredient;

public interface RecipeIngredientService {

	RecipeIngredient createRecipeIngredient(RecipeIngredient ri);
	
	RecipeIngredient updateRecipeIngredient(RecipeIngredient ri);

	List<RecipeIngredient> getAllRecipeIngredient();
}
