package com.skilldistillery.recipetracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.recipetracker.entities.RecipeReview;
import com.skilldistillery.recipetracker.entities.RecipeReviewId;

public interface RecipeReviewRepository extends JpaRepository<RecipeReview, RecipeReviewId> {
	
	

	
	
	
}
