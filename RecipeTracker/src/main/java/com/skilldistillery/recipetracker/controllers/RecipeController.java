package com.skilldistillery.recipetracker.controllers;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.repositories.RecipeIngredientRepository;
import com.skilldistillery.recipetracker.repositories.RecipeRepository;
import com.skilldistillery.recipetracker.services.IngredientService;
import com.skilldistillery.recipetracker.services.RecipeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class RecipeController {

	@Autowired
	private RecipeService recipeServ;

	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private RecipeIngredientRepository recipeIngredients;

	@Autowired
	private RecipeRepository recipes;

	@GetMapping("recipes")
	public List<Recipe> allRecipes() {
		return recipeServ.getAllRecipes();
	}

	@PostMapping("recipes")
	public Recipe addNewRecipe(@RequestBody Recipe recipe, HttpServletResponse res) {
		try {
			Recipe newRecipe = recipeServ.addNewRecipe(recipe);
			if (newRecipe != null) {
				res.setStatus(201);
				return newRecipe;
			}
		} catch (Exception e) {
			e.printStackTrace();
			res.setStatus(400);
		}
		return null;
	}

	@GetMapping("recipes/{recipeId}")
	public Recipe getRecipeById(@PathVariable Integer recipeId, HttpServletResponse res) {
		Recipe recipe = recipeServ.getById(recipeId);
		if (recipe == null) {
			res.setStatus(404);
			return null;
		}
		return recipe;
	}

	@PutMapping("recipes/{recipeId}")
	public Recipe updateMovie(@RequestBody Recipe recipe, @PathVariable Integer recipeId) {
		Recipe updatedRecipe = recipeServ.updateRecipe(recipe);
		return updatedRecipe;
	}

	@GetMapping("recipes/search/{keyword}")
	public List<Recipe> getRecipesByKeyword(@RequestBody String keyword) {
		List<Recipe> recipes = recipeServ.findRecipeByKeyword(keyword);
		return recipes;
	}

	@GetMapping("recipes/containing")
	public List<Recipe> containing(@RequestBody Set<Ingredient> ingredients) {
		return recipeServ.findAllByIngredientsIn(ingredients);
	}

}
