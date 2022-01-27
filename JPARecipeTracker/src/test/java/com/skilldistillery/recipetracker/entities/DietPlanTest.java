package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DietPlanTest extends AbstractTest {

	private DietPlan plan;

	@Override
	void setUp() throws Exception {
		plan = em.find(DietPlan.class, 1);
	}

	@Test
	@DisplayName("Basic Map testing")
	void test_User_entity_mapping() {
		assertNotNull(plan);
		assertEquals("Wolfgang's Cleanse", plan.getPlanName());
		assertEquals("get fit quick!", plan.getDescription());
		assertEquals(true, plan.isActive());
	}

	@Test
	@DisplayName("Relationship Map between dietplan and dietplan ingredient")
	void test2() {
		assertNotNull(plan);
		assertEquals(1, plan.getDietPlanIngredients().size());
		assertEquals(true, plan.getDietPlanIngredients().get(0).getPurchased());

	}

	@Test
	@DisplayName("Relationship Map between dietplan and dietplan recipe")
	void test3() {
		assertNotNull(plan);
		assertEquals(1, plan.getDietPlanRecipes().size());
		assertEquals(DayOfWeek.Monday, plan.getDietPlanRecipes().get(0).getDayName());

	}
}
