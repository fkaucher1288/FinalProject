package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.FavoriteRecipe;
import com.skilldistillery.recipetracker.repositories.FavoriteRecipeRepository;
@Service
public class FavoriteRecipeServiceImpl implements FavoriteRecipeService {
	
	@Autowired
	private FavoriteRecipeRepository frRepo;
	
	@Override
	public FavoriteRecipe createRecipeFavorite(FavoriteRecipe fr) {
		return frRepo.saveAndFlush(fr);
	}

	@Override
	public FavoriteRecipe updateRecipeFavorite(FavoriteRecipe fr) {
		return frRepo.saveAndFlush(fr);
	}


	@Override
	public List<FavoriteRecipe> getAllByUserId(int userId) {		
		return frRepo.getAllByUserId(userId);
	}

}
