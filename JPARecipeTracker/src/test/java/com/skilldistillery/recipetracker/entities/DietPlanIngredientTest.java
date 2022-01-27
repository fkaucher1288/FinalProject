package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DietPlanIngredientTest extends AbstractTest {

	private DietPlanIngredient ingredient;

	@Override
	void setUp() throws Exception {
		ingredient = em.find(DietPlanIngredient.class, new DietPlanIngredientId(1,1));	
	}

	@Test
	void diet_plan_mapping() throws Exception {
		assertNotNull(ingredient);
		assertNotNull(ingredient.getDietPlan());
		assertEquals(1, ingredient.getDietPlan().getId());
	}
	
	@Test
	void ingredient_mapping() throws Exception {
		assertNotNull(ingredient);
		assertNotNull(ingredient.getIngredient());
		assertEquals(1, ingredient.getIngredient().getId());
	}

	
	@Test
	void purchased_mapping() throws Exception {
		assertNotNull(ingredient);
		assertTrue(ingredient.getPurchased());
	}
}
