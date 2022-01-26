package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipeTest {

	private static EntityManagerFactory factory;
	protected EntityManager em;
	private Recipe recipe;

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
		recipe = em.find(Recipe.class, 1);
		
	}

	@AfterEach
	void tearDownBase() throws Exception {
		em.close();
	}
	
	@Test
	void test_Movie_title_mappings() {
		assertNotNull(recipe);
		assertEquals("Mom's Best Lasagna", recipe.getName());
		assertEquals("2022", recipe.getDateCreated().getYear());
		assertEquals("1", recipe.getDateCreated().getMonthValue());
		assertEquals(true, recipe.isActive());
		assertEquals(true, recipe.isPublic());
		assertEquals(0.2, recipe.getPrepTime());
		assertEquals(1.45, recipe.getCookTime());
		assertNotNull(recipe.getDescription());
		assertNotNull(recipe.getInstructions());
		assertEquals(" https://www.thewholesomedish.com/wp-content/uploads/2018/07/Best-Lasagna-550-500x375.jpg", recipe.getImageURL());
	}


}


