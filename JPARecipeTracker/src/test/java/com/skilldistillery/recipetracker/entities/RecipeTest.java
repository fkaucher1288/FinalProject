package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

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


}


