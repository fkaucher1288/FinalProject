package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DietPlanTest extends TestTemplate {

	private DietPlan dietPlan;

	@Override
	void setUp() throws Exception {
		dietPlan = em.find(DietPlan.class, 1);	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(dietPlan);
		assertEquals("wolfgangPuck", dietPlan.getPlanName());
	}
}
