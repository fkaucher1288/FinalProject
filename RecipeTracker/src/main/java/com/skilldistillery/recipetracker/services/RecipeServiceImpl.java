package com.skilldistillery.recipetracker.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.entities.RecipeRating;
import com.skilldistillery.recipetracker.repositories.RecipeRepository;

@Transactional
@Service
public class RecipeServiceImpl implements RecipeService {
	
	
	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private AuthService authServ;
	
		
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
	public Recipe updateRecipe(Recipe recipe) {
		Recipe updatedRecipe = recipeRepo.findById(recipe.getId()).get();
		if(updatedRecipe != null) {			
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

	@Override
	public List<Recipe> findByIngredients(List<Ingredient> ingredients) {

			//create empty arrayList for recipes
		//iterate over ingredients
		//calling findby ingredients from repo
		//recipes.addALL
		//loop
		//return recipe list
		
		return null;
	}

	@Override
	public List<RecipeRating> getAllRatings(Recipe recipe) {
		return recipe.getRatings();
	}

//	@Override
//	public Recipe addRating(Recipe recipe, RecipeRating recipeRating, Principal principal) {
//		recipeRating.setUser(authServ.findUserByName(principal.getName()));
//		recipeRating.setRecipe(this.getById(recipe.getId()));
//		
//		
//		return null;
//	}
	
	
	
	
	
	
	
	

}
