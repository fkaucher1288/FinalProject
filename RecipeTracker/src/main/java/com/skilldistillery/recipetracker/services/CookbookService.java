package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.Cookbook;

public interface CookbookService {

	public Cookbook findById (int id);
	public List<Cookbook> showAllCookbooks();

	
}
