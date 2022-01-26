package com.skilldistillery.recipetracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;


	@Entity
	@Table(name="recipe_rating")
	public class RecipeRating {
		
		
		@EmbeddedId
		private UserRecipeReviewId id;
		
		@Column(name="rating")
		private int rating;
		
		@Column(name="created_on")
		private LocalDateTime createdOn;
		
		@ManyToOne
		@JoinColumn(name="recipe_id")
		@MapsId("recipeId")
		private Recipe recipe;
		
		@ManyToOne
		@JoinColumn(name="user_id")
		@MapsId("userId")
		private User user;

		public RecipeRating() {
			super();
					}

		public RecipeRating(UserRecipeReviewId id, int rating, LocalDateTime createdOn, Recipe recipe, User user) {
			super();
			this.id = id;
			this.rating = rating;
			this.createdOn = createdOn;
			this.recipe = recipe;
			this.user = user;
		}

		public UserRecipeReviewId getId() {
			return id;
		}

		public void setId(UserRecipeReviewId id) {
			this.id = id;
		}

		public int getRating() {
			return rating;
		}

		public void setRating(int rating) {
			this.rating = rating;
		}

		public LocalDateTime getCreatedOn() {
			return createdOn;
		}

		public void setCreatedOn(LocalDateTime createdOn) {
			this.createdOn = createdOn;
		}

		public Recipe getRecipe() {
			return recipe;
		}

		public void setRecipe(Recipe recipe) {
			this.recipe = recipe;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		@Override
		public int hashCode() {
			return Objects.hash(id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			RecipeRating other = (RecipeRating) obj;
			return Objects.equals(id, other.id);
		}

		@Override
		public String toString() {
			return "RecipeRating [id=" + id + ", rating=" + rating + ", createdOn=" + createdOn + ", recipe=" + recipe
					+ ", user=" + user + "]";
		}
		
		
		
		
		
		

}
