package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.recipetracker.entities.DietPlanRecipe;
import com.skilldistillery.recipetracker.repositories.DietPlanRecipeRepository;

public class DietPlanRecipeServiceImpl implements DietPlanRecipeService {
	
	@Autowired
	private DietPlanRecipeRepository dprRepo;
	
	@Override
	public DietPlanRecipe createRecipeReview(DietPlanRecipe dpr) {
		return dprRepo.saveAndFlush(dpr);
	}

	@Override
	public DietPlanRecipe updateRecipeReview(DietPlanRecipe dpr) {
		return dprRepo.saveAndFlush(dpr);
	}

	@Override
	public List<DietPlanRecipe> getAllRecipeReviews() {
		return dprRepo.findAll();
	}

}
