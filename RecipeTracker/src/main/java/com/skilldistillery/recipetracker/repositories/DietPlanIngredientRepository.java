package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.DietPlanIngredient;
import com.skilldistillery.recipetracker.entities.DietPlanIngredientId;

public interface DietPlanIngredientRepository extends JpaRepository<DietPlanIngredient, DietPlanIngredientId> {

}
