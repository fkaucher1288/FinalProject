package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.skilldistillery.recipetracker.entities.DietPlanIngredient;
import com.skilldistillery.recipetracker.repositories.DietPlanIngredientRepository;

public class DietPlanIngredientServiceImpl implements DietPlanIngredientService {

	@Autowired
	private DietPlanIngredientRepository dpiRepo;
	
	@Override
	public DietPlanIngredient createRecipeReview(DietPlanIngredient dpi) {
		return dpiRepo.saveAndFlush(dpi);
	}

	@Override
	public DietPlanIngredient updateRecipeReview(DietPlanIngredient dpi) {
		// TODO Auto-generated method stub
		return dpiRepo.saveAndFlush(dpi);
	}

	@Override
	public List<DietPlanIngredient> getAllRecipeReviews() {
		// TODO Auto-generated method stub
		return dpiRepo.findAll();
	}

}
