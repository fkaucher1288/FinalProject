package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.RecipeRating;
import com.skilldistillery.recipetracker.entities.RecipeRatingId;

public interface RecipeRatingRepository extends JpaRepository<RecipeRating, RecipeRatingId>{

}
