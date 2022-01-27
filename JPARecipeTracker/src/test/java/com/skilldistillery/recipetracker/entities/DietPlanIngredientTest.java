package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DietPlanIngredientTest extends AbstractTest {

	private DietPlanIngredient dietPlanIngredient;

	@Override
	void setUp() throws Exception {
		dietPlanIngredient = em.find(DietPlanIngredient.class, 1);	
	}

	@Test
	@DisplayName("DietPlanIngredient basic mapping")
	void test_User_entity_mapping() {
		assertNotNull(dietPlanIngredient);
		assertEquals(1, dietPlanIngredient.getId());
		assertEquals(true, dietPlanIngredient.getPurchased());
	}
	@Test
	@DisplayName("DietPlanIngredient relational mapping to ingredient")
	void test2() {
		assertNotNull(dietPlanIngredient.getIngredient());
		assertEquals(1, dietPlanIngredient.getIngredient().getId());
		
	}
}
