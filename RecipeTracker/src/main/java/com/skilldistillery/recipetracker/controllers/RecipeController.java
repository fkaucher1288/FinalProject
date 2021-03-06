package com.skilldistillery.recipetracker.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.FavoriteRecipe;
import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.entities.RecipeIngredient;
import com.skilldistillery.recipetracker.entities.RecipeRating;
import com.skilldistillery.recipetracker.entities.RecipeReview;
import com.skilldistillery.recipetracker.entities.User;
import com.skilldistillery.recipetracker.services.AuthService;
import com.skilldistillery.recipetracker.services.FavoriteRecipeService;
import com.skilldistillery.recipetracker.services.RecipeIngredientService;
import com.skilldistillery.recipetracker.services.RecipeRatingService;
import com.skilldistillery.recipetracker.services.RecipeReviewService;
import com.skilldistillery.recipetracker.services.RecipeService;

@RestController
@RequestMapping("api")
@CrossOrigin({ "*", "http://localhost:4300" })
public class RecipeController {

	@Autowired
	private RecipeService recipeServ;
	
	@Autowired
	private RecipeReviewService rrServ;
	
	@Autowired
	private RecipeRatingService ratingServ;
	
	@Autowired
	private RecipeIngredientService rIngredientSvc;
	
	@Autowired
	private FavoriteRecipeService favSvc;
	
	@Autowired
	private AuthService authSvc;
	
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
	public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable Integer recipeId, HttpServletResponse res) {
		Recipe updatedRecipe = recipeServ.updateRecipe(recipe);
		if(recipe == null) {
			res.setStatus(404);
		}
		
		return updatedRecipe;
	}

	@GetMapping("recipes/search/{keyword}")
	public List<Recipe> getRecipesByKeyword(@PathVariable String keyword, HttpServletResponse res){
		List<Recipe> recipes = recipeServ.findRecipeByKeyword(keyword);
		if(recipes == null) {
			res.setStatus(404);
			return null;
		}		
		return recipes;
	}

	@GetMapping("recipes/containing")
	public List<Recipe> containing(@RequestBody Set<Ingredient> ingredients) {
		return recipeServ.findAllByIngredientsIn(ingredients);
	}
	
	@PostMapping("recipes/reviews")
	public RecipeReview createNewRecipeReview(@RequestBody RecipeReview rr) throws Exception{
		 return rrServ.createRecipeReview(rr);

	}
	
	@PutMapping("recipes/reviews")
	public RecipeReview updateRecipeReview(@RequestBody RecipeReview rr) throws Exception {
		return rrServ.updateRecipeReview(rr);
	}
	
	@GetMapping("recipes/reviews")
	public List<RecipeReview> getAllRecipeReviews() {
		return rrServ.getAllRecipeReviews();
	}


	
	@GetMapping("recipes/ratings")
	public List<RecipeRating> getAllRecipeRatings() {
		return ratingServ.getAllRecipeRatings();
	}
	
	
	
	@PostMapping("recipes/ratings")
	public RecipeRating addNewRecipeRating(@RequestBody RecipeRating rating, Principal principal, HttpServletResponse res) throws IOException {
		User user = authSvc.findUserByName(principal.getName());
		if(user == null) {
			res.sendError(HttpStatus.UNAUTHORIZED.value());
			return null;
		}
		rating.getId().setUserId(user.getId());
		return ratingServ.createRecipeRating(rating);
	}
	
	
	@GetMapping("recipes/ingredients")
	public List<RecipeIngredient> indexRIngredients(@RequestBody RecipeIngredient ingredient) {
		return rIngredientSvc.getAllRecipeIngredient();
	}
	
	@PostMapping("recipes/ingredients")
	public RecipeIngredient addRecipeIngredient(@RequestBody RecipeIngredient ingredient) {
		return rIngredientSvc.createRecipeIngredient(ingredient);
	}
	
	@PutMapping("recipes/ingredients")
	public RecipeIngredient updateRecipeIngredient(@RequestBody RecipeIngredient ingredient) {
		return rIngredientSvc.updateRecipeIngredient(ingredient);
	}
	
	@GetMapping("recipes/favorites/{userId}")
	public List<FavoriteRecipe> getFavoriteRecipes(@PathVariable int userId) {
		
		return favSvc.getAllByUserId(userId);
	}
	@GetMapping("recipes/userrecipes/{userId}")
	public List<Recipe> getUserRecipes(@PathVariable int userId){
		return recipeServ.getUsersRecipes(userId);
	}
	
	@GetMapping("recipes/count")
	public Long getCount() {
		return recipeServ.getCount();
	}

}
