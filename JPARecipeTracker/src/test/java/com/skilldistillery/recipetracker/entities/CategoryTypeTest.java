package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CategoryTypeTest extends AbstractTest {

	private CategoryType type;

	@Override
	void setUp() throws Exception {
		type = em.find(CategoryType.class, 1);
	}

	@Test
	void name_mapping() {
		assertNotNull(type);
		assertEquals("ethnic", type.getName());
	}

	@Test
	void categories_mapping() throws Exception {
		assertNotNull(type);
		assertNotNull(type.getCategories());
		assertTrue(type.getCategories().size() > 0);
	}

}
