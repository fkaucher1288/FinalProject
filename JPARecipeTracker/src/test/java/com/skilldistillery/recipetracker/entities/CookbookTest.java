package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CookbookTest extends AbstractTest {

	private Cookbook cookbook;

	@Override
	void setUp() throws Exception {
		cookbook = em.find(Cookbook.class, 1);
	}

	@Test
	void title_mapping() throws Exception {
		assertNotNull(cookbook);
		assertEquals("On Cooking: A Textbook of Culinary Fundamentals, 6th edition", cookbook.getTitle());
	}

	@Test
	void author_mapping() throws Exception {
		assertNotNull(cookbook);
		assertEquals("Sarah R. Labensky", cookbook.getAuthor());
	}
	
	@Test
	void description_mapping() throws Exception {
		assertNotNull(cookbook);
		assertNotNull(cookbook.getDescription());
		assertTrue(cookbook.getDescription().startsWith("For over two decades"));
	}
	
	@Test
	void image_url_mapping() throws Exception {
		assertNotNull(cookbook);
		assertEquals("https://www.pearson.com/store", cookbook.getImageURL());
	}
	
	@Test
	void recipes_mapping() throws Exception {
		assertNotNull(cookbook);
		assertNotNull(cookbook.getRecipes());
		assertTrue(cookbook.getRecipes().size()>0);
	}
	
	@Test
	void user_mapping() throws Exception {
		assertNotNull(cookbook);
		assertNotNull(cookbook.getUser());
		assertEquals(1,cookbook.getUser().getId());
	}

}
