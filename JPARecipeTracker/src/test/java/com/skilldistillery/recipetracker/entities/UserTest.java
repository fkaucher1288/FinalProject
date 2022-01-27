package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserTest extends AbstractTest {

	private User user;

	@Override
	void setUp() throws Exception {
		user = em.find(User.class, 1);
	}

	@Test
	void basic_mapping() {
		assertNotNull(user);
		assertEquals("wolfgangPuck", user.getUsername());
		assertEquals("wolfgangPuck@gmail.com", user.getEmail());
		assertEquals("wolfgangPuck", user.getPassword());
		assertTrue(user.getEnabled());
		assertEquals("admin", user.getRole());
		assertEquals("Wolfgang", user.getFirstName());
		assertEquals("Puck", user.getLastName());
	}

	@Test
	void recipes_mapping() {
		assertNotNull(user);
		assertNotNull(user.getRecipes());
		assertTrue(user.getRecipes().size() > 0);
	}

	@Test
	void cookbooks_mapping() throws Exception {
		assertNotNull(user);
		assertNotNull(user.getCookbooks());
		assertTrue(user.getCookbooks().size() > 0);
	}

	@Test
	void diet_plans_mapping() throws Exception {
		assertNotNull(user);
		assertNotNull(user.getDietPlans());
		assertTrue(user.getDietPlans().size() > 0);
	}

	@Test
	void favorites_mapping() throws Exception {
		assertNotNull(user);
		assertNotNull(user.getFavorites());
		assertTrue(user.getFavorites().size() > 0);

	}

	@Test
	void reviews_mapping() throws Exception {
		assertNotNull(user);
		assertNotNull(user.getReviews());
		assertTrue(user.getReviews().size() > 0);
	}

	@Test
	void ratings_mapping() throws Exception {
		assertNotNull(user);
		assertNotNull(user.getRatings());
		assertTrue(user.getRatings().size() > 0);

	}

}
