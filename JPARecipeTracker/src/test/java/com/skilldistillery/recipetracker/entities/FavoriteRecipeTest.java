package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class FavoriteRecipeTest extends AbstractTest {

	private FavoriteRecipe fr;

	
	@Override
	void setUp() throws Exception {
		fr = em.find(FavoriteRecipe.class, new FavoriteRecipeId(1,1));	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(fr);
		assertEquals("Wolfgang love's this lasangna and want's to favorite it!", fr.getComment());
		assertEquals(2022, fr.getCreatedAt().getYear());
		assertEquals(01, fr.getDateLastMade().getMonthValue());
		
		
		
	}

}
