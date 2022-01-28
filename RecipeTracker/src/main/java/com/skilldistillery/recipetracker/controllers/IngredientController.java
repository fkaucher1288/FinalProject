package com.skilldistillery.recipetracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.services.IngredientService;

@RequestMapping("api/ingredients")
@CrossOrigin({"*", "http://localhost:4200"})
@RestController
public class IngredientController {

	@Autowired
	private IngredientService service;
	
	@GetMapping
	public List<Ingredient> index() {
		return service.getAll();
	}
	
	@GetMapping("specific")
	public List<Ingredient> ids(@RequestBody int[] ids) {
		return service.get(ids);
	}
	
}
