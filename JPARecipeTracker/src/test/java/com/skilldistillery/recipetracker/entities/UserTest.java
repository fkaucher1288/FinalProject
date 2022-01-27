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

class UserTest extends AbstractTest {

	private User user;

	@Override
	void setUp() throws Exception {
		user = em.find(User.class, 1);	
	}

	@Test
	void test_User_entity_mapping() {
		assertNotNull(user);
		assertEquals("wolfgangPuck", user.getUsername());
		assertEquals("wolfgangPuck@gmail.com", user.getEmail());
		assertEquals("Wolfgang", user.getFirstName());
		assertEquals("Puck", user.getLastName());
		
	}
	
	@Test
	void test_User_to_Recipe_mappings() {
		assertNotNull(user);
		assertNotNull(user.getRecipes().size());
		
	}

}
