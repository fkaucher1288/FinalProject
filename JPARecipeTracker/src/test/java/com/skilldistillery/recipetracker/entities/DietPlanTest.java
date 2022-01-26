package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DietPlanTest extends TestTemplate {

	private DietPlan dietPlan;

	@Override
	void setUp() throws Exception {
		dietPlan = em.find(DietPlan.class, 1);	
	}

	@Test
	@DisplayName("Basic Map testing")
	void test_User_entity_mapping() {
		assertNotNull(dietPlan);
		assertEquals("Wolfgang's Cleanse", dietPlan.getPlanName());
		assertEquals("get fit quick!", dietPlan.getDescription());
		assertEquals(true, dietPlan.isActive());
	}
	@Test
	@DisplayName("Relationship Map between dietplan and dietplan ingredient")
	void test2() {
		assertNotNull(dietPlan);
		assertEquals(1 , dietPlan.getDietPlanIngredients().size());
		assertEquals(true, dietPlan.getDietPlanIngredients().get(0).getPurchased());
		
	}
	@Test
	@DisplayName("Relationship Map between dietplan and dietplan recipe")
	void test3() {
		assertNotNull(dietPlan);
		assertEquals(1 , dietPlan.getDietPlanRecipes().size());
		assertEquals(DayOfWeek.Monday, dietPlan.getDietPlanRecipes().get(0).getDayName());
		
	}
}
