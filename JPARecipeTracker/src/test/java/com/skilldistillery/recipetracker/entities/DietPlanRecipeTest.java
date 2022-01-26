package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DietPlanRecipeTest extends TestTemplate {

	private DietPlanRecipe dietPlanRecipe;

	@Override
	void setUp() throws Exception {
		dietPlanRecipe = em.find(DietPlanRecipe.class, 1);	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(dietPlanRecipe);
		assertEquals(DayOfWeek.Monday, dietPlanRecipe.getDayName());
		assertEquals(1, dietPlanRecipe.getSequenceNumber());
	}
}
