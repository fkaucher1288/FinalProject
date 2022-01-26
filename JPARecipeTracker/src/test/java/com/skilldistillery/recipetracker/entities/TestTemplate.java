package com.skilldistillery.recipetracker.entities;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

abstract class TestTemplate {

	private static EntityManagerFactory factory;
	protected EntityManager em;

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
		this.setUp();
	}

	@AfterEach
	void tearDownBase() throws Exception {
		em.close();
	}
	
	abstract void setUp() throws Exception;	

}
