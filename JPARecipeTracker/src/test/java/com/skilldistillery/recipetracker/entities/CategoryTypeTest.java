package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Table;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CategoryTypeTest extends TestTemplate {

	private CategoryType categoryType;

	@Override
	void setUp() throws Exception {
		categoryType = em.find(CategoryType.class, 1);
	}
	
	@Test
	void test_CategoryType_basic_mappings() {
		assertNotNull(categoryType);

	}

}
