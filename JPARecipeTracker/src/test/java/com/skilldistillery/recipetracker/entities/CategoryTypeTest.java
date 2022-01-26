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


class CategoryTypeTest {

	private static EntityManagerFactory factory;
	protected EntityManager em;
	private CategoryType categoryType;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		factory = Persistence.createEntityManagerFactory("JPARecipeTracker");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		factory.close();
	}

	@BeforeEach
	void setUpBase() throws Exception {
		em = factory.createEntityManager();
		categoryType = em.find(CategoryType.class, 1);
		
	}

	@AfterEach
	void tearDownBase() throws Exception {
		em.close();
	}
	
	@Test
	void test_CategoryType_basic_mappings() {
		assertNotNull(categoryType);
		assertEquals("Italian", categoryType.getEthnicity());
		assertEquals("Savory", categoryType.getFlavors());
	}

}
