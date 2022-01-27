package com.skilldistillery.recipetracker.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.Category;
import com.skilldistillery.recipetracker.services.CategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class CategoryController {
	
	@Autowired
	private CategoryService svc;
	

	@GetMapping(path="category")
	public List<Category> index() {
		return svc.index();
	}
	

}