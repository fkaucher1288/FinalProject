package com.skilldistillery.recipetracker.repositories;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer>{
	
	List<Recipe> findByNameLike(String keyword);
//  List<Recipe> findByIngredients_NameLike(Ingredient ingredient);
//	List<Recipe> findByIngredients_NameLike(Set<Ingredients> ingredients);


}
