package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeRatingTest extends AbstractTest {
	
	private RecipeRating rr;
	
	@Override
	void setUp() throws Exception {
		rr = em.find(RecipeRating.class, new UserRecipeReviewId(1,1));	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(rr);
		assertEquals(5, rr.getRating());
		
		
		
		
	}
}
