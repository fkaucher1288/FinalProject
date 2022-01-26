package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Test;

class FavoriteRecipeTest extends TestTemplate {

	private FavoriteRecipe fr;

	@Override
	void setUp() throws Exception {
		fr = em.find(FavoriteRecipe.class, 1);	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(fr);
		assertEquals("Wolfgang love’s this lasangna and want’s to favorite it!", fr.getComment());
		assertEquals("2022-01-01T00:00:00", fr.getCreatedAt());
		assertEquals("2022-01-01", fr.getDateLastMade());
		
	}

}
