package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.DietPlan;
import com.skilldistillery.recipetracker.entities.DietPlanIngredient;
import com.skilldistillery.recipetracker.entities.DietPlanRecipe;

public interface DietPlanService {
	List<DietPlan> index();
	DietPlan findDietPlanById(int dietPlanId);
	DietPlan createDietPlan(DietPlan dietPlan);
	DietPlan updateDietPlan(int dietPlanId, DietPlan dietPlan);
	boolean deleteDietPlan(int dietPlanId);
	List<DietPlanRecipe> findDietPlanRecipeByDietPlanId(int dietPlanId);
	List<DietPlanIngredient> findDietPlanIngredientByDietPlanId(int dietPlanId);
}
