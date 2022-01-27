package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class IngredientTest extends AbstractTest {

	private Ingredient ingredient;
	
	@Override
	void setUp() throws Exception {
		ingredient = em.find(Ingredient.class, 1);
	}
	
	@Test
	void name_mapping() throws Exception {
		assertNotNull(ingredient);
		assertEquals("Garlic", ingredient.getName());
	}
	
	@Test
	void measurement_unit_mapping() throws Exception {
		assertNotNull(ingredient);
		assertEquals("Clove", ingredient.getMeasurementUnit());
	}
	
	@Test
	void brand_mapping() throws Exception {
		assertNotNull(ingredient);
		assertNull(ingredient.getBrand());
	}
	
	@Test
	void category_mapping() throws Exception {
		assertNotNull(ingredient);
		assertEquals("Vegetable", ingredient.getCategory());
	}

}
