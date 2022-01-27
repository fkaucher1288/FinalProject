
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
@Table(name="recipe_review")
public class RecipeReview {
	
	
	@EmbeddedId
	private UserHasRecipeReviewCommentId id;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="active")
	private boolean active;
	
	@ManyToOne
	@JoinColumn(name="recipe_id")
	@MapsId("recipeId")
	private Recipe recipe;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	@MapsId("userId")
	private User user;

	public RecipeReview() {
		super();
	}

	public RecipeReview(UserHasRecipeReviewCommentId id, LocalDateTime createdOn, String comment, boolean active,
			Recipe recipe, User user) {
		super();
		this.id = id;
		this.createdOn = createdOn;
		this.comment = comment;
		this.active = active;
		this.recipe = recipe;
		this.user = user;
	}

	public UserHasRecipeReviewCommentId getId() {
		return id;
	}

	public void setId(UserHasRecipeReviewCommentId id) {
		this.id = id;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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
		RecipeReview other = (RecipeReview) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RecipeReview [id=" + id + ", createdOn=" + createdOn + ", comment=" + comment + ", active=" + active
				+ ", recipe=" + recipe + ", user=" + user + "]";
	}
	
	
	
	

}