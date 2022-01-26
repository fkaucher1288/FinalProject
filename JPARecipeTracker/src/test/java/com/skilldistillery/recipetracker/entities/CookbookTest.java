package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class CookbookTest extends TestTemplate {

	private Cookbook cookbook;
	@Override
	void setUp() throws Exception {
		cookbook = em.find(Cookbook.class, 1);
	}

	@Test
	void test_Cookbook_entity_mapping() {
		assertNotNull(cookbook);
		assertEquals("On Cooking: A Textbook of Culinary Fundamentals, 6th edition", cookbook.getTitle());
		assertEquals("Sarah R. Labensky", cookbook.getAuthor());

	}

}
