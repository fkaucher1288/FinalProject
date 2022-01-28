package com.skilldistillery.recipetracker.services;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.repositories.RecipeIngredientRepository;
import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.entities.RecipeRating;
import com.skilldistillery.recipetracker.repositories.RecipeRepository;

@Transactional
@Service
public class RecipeServiceImpl implements RecipeService {

	@Autowired
	private RecipeRepository recipes;

	@Autowired
	private RecipeIngredientRepository ingredients;

	@Autowired
	private RecipeRepository recipeRepo;
	
	@Autowired
	private AuthService authServ;
	
	@Override
	public List<Recipe> getAllRecipes() {
		return recipes.findAll();
	}

	@Override
	public Recipe getById(int recipeId) {
		Optional<Recipe> op = recipes.findById(recipeId);
		if (op.isPresent()) {
			return op.get();
		}
		return null;
	}

	@Override
	public Recipe addNewRecipe(Recipe recipe) {
		Recipe newRecipe = recipes.save(recipe);
		return newRecipe;
	}

	@Override
	public Recipe updateRecipe(Recipe recipe) {
		Recipe updatedRecipe = recipes.findById(recipe.getId()).get();
		if (updatedRecipe != null) {
			return recipes.save(recipe);
		}
		return null;
	}

	@Override
	public boolean deleteRecipe(int recipeId) {
		boolean isDeleted = false;
		Optional<Recipe> recipeOp = recipes.findById(recipeId);
		if (recipeOp.isPresent()) {
			recipes.deleteById(recipeId);
			isDeleted = true;
		}
		return false;
	}

	@Override
	public List<Recipe> findRecipeByKeyword(String keyword) {
		keyword = "%" + keyword + "%";
		return recipes.findByNameLike(keyword);
	}

	@Override
	public List<Recipe> findAllByIngredientsIn(Set<Ingredient> ingredients) {
		return recipes.findAllByIngredientsIn(ingredients, ingredients.size());
	}

	@Override
	public List<RecipeRating> getAllRatings(Recipe recipe) {
		return recipe.getRatings();
	}
}
