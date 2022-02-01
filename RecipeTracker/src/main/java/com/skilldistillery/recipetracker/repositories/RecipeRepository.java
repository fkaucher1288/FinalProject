package com.skilldistillery.recipetracker.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.skilldistillery.recipetracker.entities.Ingredient;
import com.skilldistillery.recipetracker.entities.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

	List<Recipe> findByNameLike(String keyword);

	/**
	 * Searches for {@link Recipe}'s that have at least <code>minimum</code>
	 * occurrences in the <code>ingredients</code> list.
	 * 
	 * @param ingredients the ingredients.
	 * @param minimum     the minimum ingredients required to select a recipe.
	 * @return a list of matching recipes.
	 */
	@Query("SELECT DISTINCT(r) FROM Recipe r INNER JOIN r.ingredients i WHERE i IN ("
			+ "SELECT ri FROM RecipeIngredient ri WHERE ri.ingredient IN :ingredients"
			+ ") GROUP BY (i.recipe) HAVING COUNT(*) >= :min")
	List<Recipe> findAllByIngredientsIn(@Param("ingredients") Set<Ingredient> ingredients, @Param("min") long minimum);
	
	List<Recipe> findByUser_Id(int userId);
}
