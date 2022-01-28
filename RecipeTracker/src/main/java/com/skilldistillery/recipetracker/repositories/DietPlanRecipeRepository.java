package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.DietPlanRecipe;
import com.skilldistillery.recipetracker.entities.DietPlanRecipeId;

public interface DietPlanRecipeRepository extends JpaRepository<DietPlanRecipe, DietPlanRecipeId> {

}
