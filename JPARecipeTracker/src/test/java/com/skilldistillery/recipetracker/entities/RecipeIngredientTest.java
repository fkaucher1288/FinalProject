package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class RecipeIngredientTest extends AbstractTest {

	private RecipeIngredient ingredient;
	
	@Override
	void setUp() throws Exception {
		ingredient = em.find(RecipeIngredient.class, new RecipeIngredientId(1,1));
	}
	
	@Test
	void ingredient_mapping() throws Exception {
		assertNotNull(ingredient);
		assertNotNull(ingredient.getIngredient());
		assertEquals(1,ingredient.getIngredient().getId());
	}
	
	@Test
	void recipe_mapping() throws Exception {
		assertNotNull(ingredient);
		assertNotNull(ingredient.getRecipe());
		assertEquals(1,ingredient.getRecipe().getId());
	}
	
	@Test
	void quantity_mapping() throws Exception {
		assertNotNull(ingredient);
		assertEquals(4, ingredient.getQuantity());
	}
	
	@Test
	void remarks_mapping() throws Exception {
		assertNotNull(ingredient);
		assertEquals("minced", ingredient.getRemarks());
	}

}
