package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RecipeRatingTest extends AbstractTest {

	private RecipeRating rating;

	@Override
	void setUp() throws Exception {
		rating = em.find(RecipeRating.class, new RecipeRatingId(1, 1));
	}

	@Test
	void rating_mapping() {
		assertNotNull(rating);
		assertEquals(5, rating.getRating());
	}

	@Test
	void recipe_apping() throws Exception {
		assertNotNull(rating);
		assertNotNull(rating.getRecipe());
		assertEquals(1, rating.getRecipe().getId());
	}

	@Test
	void user_mapping() throws Exception {
		assertNotNull(rating);
		assertNotNull(rating.getUser());
		assertEquals(1, rating.getUser().getId());
	}
}
