
package com.skilldistillery.recipetracker.entities;

import java.time.LocalDate;
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
@Table(name="favorite_recipe")
public class FavoriteRecipe {
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	@MapsId("recipeId")
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId("userId")
	private User user;
	
	@EmbeddedId
	private UserHasFavoriteRecipeId id;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date_last_made")
	private LocalDate dateLastMade;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	public FavoriteRecipe() {
		super();
			}

	public FavoriteRecipe(UserHasFavoriteRecipeId id, String comment, LocalDate dateLastMade, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.comment = comment;
		this.dateLastMade = dateLastMade;
		this.createdAt = createdAt;
	}

	public UserHasFavoriteRecipeId getId() {
		return id;
	}

	public void setId(UserHasFavoriteRecipeId id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getDateLastMade() {
		return dateLastMade;
	}

	public void setDateLastMade(LocalDate dateLastMade) {
		this.dateLastMade = dateLastMade;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
		FavoriteRecipe other = (FavoriteRecipe) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "FavoriteRecipe [comment=" + comment + ", dateLastMade=" + dateLastMade + ", createdAt=" + createdAt
				+ "]";
	}
	
	
	
	
	
	
	

}
