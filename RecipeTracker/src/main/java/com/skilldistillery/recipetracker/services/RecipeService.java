package com.skilldistillery.recipetracker.services;

import java.util.List;
import java.util.Set;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.entities.RecipeRating;

public interface RecipeService {

	List<Recipe> getAllRecipes();

	Recipe getById(int recipeId);

	Recipe addNewRecipe(Recipe recipe);

	Recipe updateRecipe(Recipe recipe);

	boolean deleteRecipe(int recipeId);

	List<Recipe> findRecipeByKeyword(String keyword);

	List<Recipe> findAllByIngredientsIn(Set<Ingredient> ingredients);
	
//	Recipe addRating(Recipe recipe, RecipeRating recipeRating);
	List<RecipeRating> getAllRatings(Recipe recipe);
	

}
