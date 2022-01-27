package com.skilldistillery.recipetracker.controllers;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.recipetracker.entities.Cookbook;
import com.skilldistillery.recipetracker.services.CookbookService;

@RestController
@RequestMapping("api")
@CrossOrigin({"*", "http://localhost:4300"})
public class CookbookController {
	
	@Autowired
	private CookbookService svc;
	
	@GetMapping(path="cookbook/{id}")
	public Cookbook getCookbook(@PathVariable Integer id, HttpServletResponse rsp) {
		Cookbook cookbook = svc.findById(id);
		if (cookbook == null) {
			rsp.setStatus(404);
		} else {
			rsp.setStatus(201);
		}
		return cookbook;
	}
	
	@GetMapping(path="cookbook")
	public List<Cookbook> allCookbook() {
		return svc.showAllCookbooks();
	}
	

}