package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DietPlanIngredientTest extends TestTemplate {

	private DietPlanIngredient dietPlanIngredient;

	@Override
	void setUp() throws Exception {
		dietPlanIngredient = em.find(DietPlanIngredient.class, 1);	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(dietPlanIngredient);
		assertEquals(1, dietPlanIngredient.getId());
		assertEquals(true, dietPlanIngredient.getPurchased());
	}
}
