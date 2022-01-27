package com.skilldistillery.recipetracker.controllers;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.Category;
import com.skilldistillery.recipetracker.entities.CategoryType;
import com.skilldistillery.recipetracker.services.CategoryService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class CategoryController {
	
	@Autowired
	private CategoryService catSvc;
	

	@GetMapping(path="category")
	public List<Category> index() {
		return catSvc.index();
	}
	
//	@GetMapping("category/{categoryId}/category_type")
//	public CategoryType getCategoryTypes(@PathVariable int categoryId, HttpServletResponse res, Principal principal){
//		Category category = catSvc.findCategoryTypeByCategoryId(categoryId);
//		return category.getType();
//	}
}