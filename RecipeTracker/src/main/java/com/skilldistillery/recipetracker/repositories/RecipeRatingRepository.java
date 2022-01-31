package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.skilldistillery.recipetracker.entities.RecipeRating;
import com.skilldistillery.recipetracker.entities.RecipeRatingId;

public interface RecipeRatingRepository extends JpaRepository<RecipeRating, RecipeRatingId>{
	@Query("SELECT AVG(r.rating) FROM RecipeRating r WHERE r.recipe.user.id = ?1")
	double getAvgRatingByRecipe_User_Id(int userId);
	
}
