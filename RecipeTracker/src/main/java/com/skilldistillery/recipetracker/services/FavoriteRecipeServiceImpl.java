package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.recipetracker.entities.FavoriteRecipe;
import com.skilldistillery.recipetracker.repositories.FavoriteRecipeRepository;

public class FavoriteRecipeServiceImpl implements FavoriteRecipeService {
	
	@Autowired
	private FavoriteRecipeRepository frRepo;
	
	@Override
	public FavoriteRecipe createRecipeReview(FavoriteRecipe fr) {
		return frRepo.saveAndFlush(fr);
	}

	@Override
	public FavoriteRecipe updateRecipeReview(FavoriteRecipe fr) {
		return frRepo.saveAndFlush(fr);
	}

	@Override
	public List<FavoriteRecipe> getAllRecipeReviews() {
		return frRepo.findAll();
	}

}
