package com.skilldistillery.recipetracker.services;

import java.util.List;

import com.skilldistillery.recipetracker.entities.Cookbook;

public interface CookbookService {

	List<Cookbook> index();
	Cookbook findCookbookById (int cookbookId);
	Cookbook createCookbook(Cookbook cookbook);
	Cookbook updateCookbook(int cookbookId, Cookbook cookbook);
	boolean deleteCookbook(int cookbookId);

	
}
