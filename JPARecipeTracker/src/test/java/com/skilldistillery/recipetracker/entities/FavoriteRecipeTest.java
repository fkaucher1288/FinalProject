package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FavoriteRecipeTest extends AbstractTest {

	private FavoriteRecipe favorite;

	
	@Override
	void setUp() throws Exception {
		favorite = em.find(FavoriteRecipe.class, new FavoriteRecipeId(1,1));	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(favorite);
		assertEquals("Wolfgang love's this lasangna and want's to favorite it!", favorite.getComment());
		assertEquals(2022, favorite.getCreatedAt().getYear());
		assertEquals(01, favorite.getDateLastMade().getMonthValue());
	}
	
	@Test
	void recipe_mapping() throws Exception {
		assertNotNull(favorite);
		assertNotNull(favorite.getRecipe());
		assertEquals(1,favorite.getRecipe().getId());
	}
	
	@Test
	void user_mapping() throws Exception {
		assertNotNull(favorite);
		assertNotNull(favorite.getUser());
		assertEquals(1,favorite.getUser().getId());
	}

}
