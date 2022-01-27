package com.skilldistillery.recipetracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.Recipe;
import com.skilldistillery.recipetracker.services.RecipeService;

@RestController
@RequestMapping("api")
//@CrossOrigin({"*", "http://localhost:4300"})
public class RecipeController {
	
	@Autowired
	private RecipeService recipeServ;
	
	@GetMapping("recipes")
	public List<Recipe> allRecipes(){
		return recipeServ.getAllRecipes();
	}

}
