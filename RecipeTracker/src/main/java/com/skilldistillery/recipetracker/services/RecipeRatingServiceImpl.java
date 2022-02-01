package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.RecipeRating;
import com.skilldistillery.recipetracker.repositories.RecipeRatingRepository;

@Service
public class RecipeRatingServiceImpl implements RecipeRatingService {
	
	@Autowired
	private RecipeRatingRepository rrRepo;

	@Override
	public RecipeRating createRecipeRating(RecipeRating rating) {
		return rrRepo.saveAndFlush(rating);
	}

	@Override
	public RecipeRating updateRecipeRating(RecipeRating rating) {
		return rrRepo.saveAndFlush(rating);
	}

	@Override
	public List<RecipeRating> getAllRecipeRatings() {
		return rrRepo.findAll();
	}

}
