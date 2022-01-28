package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.RecipeReview;

public interface RecipeReviewService {
	
	RecipeReview createRecipeReview(RecipeReview rr);
	
	RecipeReview updateRecipeReview(RecipeReview rr);

	List<RecipeReview> getAllRecipeReviews();
	
}
