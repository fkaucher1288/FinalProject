package com.skilldistillery.recipetracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {

	public List<Ingredient> findByIdIn(int[] ids);
	
}
