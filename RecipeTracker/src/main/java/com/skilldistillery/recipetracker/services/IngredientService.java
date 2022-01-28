package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.Ingredient;

public interface IngredientService {
	List<Ingredient> getAll();
	List<Ingredient> get(int...ids);
}
