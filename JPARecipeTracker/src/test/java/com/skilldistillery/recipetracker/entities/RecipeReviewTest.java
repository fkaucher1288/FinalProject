package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RecipeReviewTest extends AbstractTest {

	private RecipeReview review;

	@Override
	void setUp() throws Exception {
		review = em.find(RecipeReview.class, new RecipeReviewId(1, 1));
	}

	@Test
	void user_mapping() throws Exception {
		assertNotNull(review);
		assertNotNull(review.getUser());
		assertEquals(1, review.getUser().getId());
	}

	@Test
	void recipe_mapping() throws Exception {
		assertNotNull(review);
		assertNotNull(review.getRecipe());
		assertEquals(1, review.getRecipe().getId());
	}
	
	@Test
	void active_mapping() throws Exception {
		assertNotNull(review);
		assertTrue(review.isActive());
	}

	@Test
	void comment_mapping() {
		assertNotNull(review);
		assertEquals("I absolutely love this, so yummmy!!!", review.getComment());
	}

}
