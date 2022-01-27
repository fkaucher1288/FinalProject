package com.skilldistillery.recipetracker.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RecipeTest extends AbstractTest {

	private Recipe recipe;

	@Override
	void setUp() throws Exception {
		recipe = em.find(Recipe.class, 1);
	}

	@Test
	void basic_mappings() {
		assertNotNull(recipe);
		assertEquals("Mom's Best Lasagna", recipe.getName());
		assertEquals(2022, recipe.getDateCreated().getYear());
		assertEquals(1, recipe.getDateCreated().getMonthValue());
		assertEquals(true, recipe.isActive());
		assertEquals(true, recipe.isPublic());
		assertEquals("20m", recipe.getPrepTime());
		assertEquals("1hr45m", recipe.getCookTime());
		assertNotNull(recipe.getDescription());
		assertNotNull(recipe.getInstructions());
		assertEquals("https://www.thewholesomedish.com/wp-content/uploads/2018/07/Best-Lasagna-550-500x375.jpg",
				recipe.getImageURL());
	}

	@Test
	void user_mapping() {
		assertNotNull(recipe);
		assertNotNull(recipe.getUser());
		assertEquals(1, recipe.getUser().getId());
	}

	@Test
	void weblink_mapping() throws Exception {
		assertNotNull(recipe);
		assertEquals("https://cafedelites.com/best-lasagna/", recipe.getWebLink());
	}

	@Test
	void categories_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getCategories());
		assertTrue(recipe.getCategories().size() > 0);
	}

	@Test
	void cookbooks_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getCookbooks());
		assertTrue(recipe.getCookbooks().size() > 0);
	}

	@Test
	void dietplans_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getDietPlans());
		assertTrue(recipe.getDietPlans().size() > 0);
	}

	@Test
	void favorites_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getFavorites());
		assertTrue(recipe.getFavorites().size() > 0);
	}

	@Test
	void reviews_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getReviews());
		assertTrue(recipe.getReviews().size() > 0);
	}

	@Test
	void ratings_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getRatings());
		assertTrue(recipe.getRatings().size() > 0);
	}

	@Test
	void ingredients_mapping() throws Exception {
		assertNotNull(recipe);
		assertNotNull(recipe.getIngredients());
		assertTrue(recipe.getIngredients().size() > 0);
	}

}
