package com.skilldistillery.recipetracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.FavoriteRecipe;
import com.skilldistillery.recipetracker.entities.FavoriteRecipeId;

public interface FavoriteRecipeRepository extends JpaRepository<FavoriteRecipe, FavoriteRecipeId> {
	
	List <FavoriteRecipe> getAllByUserId (int userId);  

}
