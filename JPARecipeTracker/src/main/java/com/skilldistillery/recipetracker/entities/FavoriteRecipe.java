package com.skilldistillery.recipetracker.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="favorite_recipe")
public class FavoriteRecipe {
	
	@EmbeddedId
	private userHasFavoriteRecipeId id;
	
	@Column(name="comment")
	private String comment;
	
	@Column(name="date_last_made")
	private Date dateLastMade;
	
	@Column(name="created_at")
	private LocalDateTime createdAt;

	public FavoriteRecipe() {
		super();
			}

	public FavoriteRecipe(userHasFavoriteRecipeId id, String comment, Date dateLastMade, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.comment = comment;
		this.dateLastMade = dateLastMade;
		this.createdAt = createdAt;
	}

	public userHasFavoriteRecipeId getId() {
		return id;
	}

	public void setId(userHasFavoriteRecipeId id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateLastMade() {
		return dateLastMade;
	}

	public void setDateLastMade(Date dateLastMade) {
		this.dateLastMade = dateLastMade;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "FavoriteRecipe [comment=" + comment + ", dateLastMade=" + dateLastMade + ", createdAt=" + createdAt
				+ "]";
	}
	
	
	
	

}
