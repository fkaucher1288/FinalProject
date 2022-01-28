package com.skilldistillery.recipetracker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.recipetracker.entities.RecipeReview;
import com.skilldistillery.recipetracker.repositories.RecipeReviewRepository;

@Service
public class RecipeReviewServiceImpl implements RecipeReviewService {
	
	@Autowired
	private RecipeReviewRepository rrRepo;
	
	@Override
	public List<RecipeReview> getAllRecipeReviews() {
		

		
		return rrRepo.findAll();
	}

	@Override
	public RecipeReview createRecipeReview(RecipeReview rr) {
		
		return rrRepo.saveAndFlush(rr);
	}

	@Override
	public RecipeReview updateRecipeReview(RecipeReview rr) {
		
		
			return rrRepo.saveAndFlush(rr);
	
	}



}
