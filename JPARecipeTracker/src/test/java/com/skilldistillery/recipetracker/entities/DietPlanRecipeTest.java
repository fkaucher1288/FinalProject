package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DietPlanRecipeTest extends AbstractTest {

	private DietPlanRecipe recipe;

	@Override
	void setUp() throws Exception {
		recipe = em.find(DietPlanRecipe.class, new DietPlanRecipeId(1,1));	
	}

	@Test
	void diet_plan_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getDietPlan());
		assertEquals(1,recipe.getDietPlan().getId());
	}
	
	@Test
	void recipe_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getRecipe());
		assertEquals(1,recipe.getRecipe().getId());
	}
	
	@Test
	void sequence_number_mapping() throws Exception {
		assertNotNull(recipe);
		assertEquals(1, recipe.getSequenceNumber());
	}
	
	@Test
	void day_name_mapping() throws Exception {
		assertNotNull(recipe);
		assertEquals(DayOfWeek.Monday, recipe.getDayName());
	}
}
