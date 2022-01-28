package com.skilldistillery.recipetracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.entities.RecipeIngredientId;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientId>{
	
	@Query("SELECT i FROM RecipeIngredient i WHERE i.ingredient.id IN :ids")
	public List<RecipeIngredient> findAllByIngredientIdIn(@Param("ids") int[] ids);

}