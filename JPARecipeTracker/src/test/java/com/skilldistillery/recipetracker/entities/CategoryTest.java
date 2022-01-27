package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CategoryTest extends AbstractTest {

	private Category category;

	@Override
	void setUp() throws Exception {
		category = em.find(Category.class, 1);
	}

	@Test
	void name_mapping() throws Exception {
		assertNotNull(category);
		assertEquals("Italian", category.getName());
	}

	@Test
	void description_mapping() throws Exception {
		assertNotNull(category);
		assertEquals("Sicilian Italian", category.getDescription());
	}

	@Test
	void type_mapping() throws Exception {
		assertNotNull(category);
		assertNotNull(category.getType());
		assertEquals(1, category.getType().getId());
	}

	@Test
	void recipes_mapping() throws Exception {
		assertNotNull(category);
		assertNotNull(category.getRecipes());
		assertTrue(category.getRecipes().size() > 0);
	}

}
