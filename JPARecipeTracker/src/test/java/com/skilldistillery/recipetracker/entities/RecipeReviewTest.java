package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeReviewTest extends TestTemplate {
	
	private RecipeReview review;
	
	@Override
	void setUp() throws Exception {
		review = em.find(RecipeReview.class, new UserHasRecipeReviewCommentId(1,1));	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(review);
		assertEquals("I absolutely love this, so yummmy!!!", review.getComment());
		
		
		
		
		
		
	}
}
