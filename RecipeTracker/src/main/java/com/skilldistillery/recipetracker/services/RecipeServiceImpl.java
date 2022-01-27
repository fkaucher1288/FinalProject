package com.skilldistillery.recipetracker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
	
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	
	@Override
	public List<Recipe> getAllRecipes() {
		return recipeRepo.findAll();
	}

	@Override
	public Recipe getById(int recipeId) {
		Optional<Recipe> op = recipeRepo.findById(recipeId);
		if(op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public Recipe addNewRecipe(Recipe recipe) {
		Recipe newRecipe = recipeRepo.save(recipe);
		return newRecipe;
	}

	@Override
	public Recipe updateRecipe(Recipe recipe, int recipeId) {
		recipe.setId(recipeId);
		if(recipeRepo.existsById(recipeId)) {
			return recipeRepo.save(recipe);
		}
		return null;
	}

	@Override
	public boolean deleteRecipe(int recipeId) {
		boolean isDeleted = false;
		Optional<Recipe> recipeOp = recipeRepo.findById(recipeId);
		if(recipeOp.isPresent()) {
			recipeRepo.deleteById(recipeId);
			isDeleted = true;
		}
		return false;
	}

	
	@Override
	public List<Recipe> findRecipeByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return recipeRepo.findByNameLike(keyword);
	}


	@Override
	public List<RecipeIngredient> findRecipeIngredients(Recipe recipe) {
		List<RecipeIngredient> ingredients = recipe.getIngredients();
		return ingredients;
	}
	
	
	
	
	
	
	
	

}
