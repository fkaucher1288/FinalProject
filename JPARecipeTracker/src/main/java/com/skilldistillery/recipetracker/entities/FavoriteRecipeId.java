package com.skilldistillery.recipetracker.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class FavoriteRecipeId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="recipe_id")
	private int recipeId;
	
	

	public FavoriteRecipeId(int userId, int recipeId) {
		super();
		this.userId = userId;
		this.recipeId = recipeId;
	}



	public FavoriteRecipeId() {
		super();
		
	}
	
	

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	


	@Override
	public int hashCode() {
		return Objects.hash(recipeId, userId);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteRecipeId other = (FavoriteRecipeId) obj;
		return recipeId == other.recipeId && userId == other.userId;
	}



	@Override
	public String toString() {
		return "userHasFavoriteRecipeId [userId=" + userId + ", recipeId=" + recipeId + "]";
	}
	
	
	
	

}
