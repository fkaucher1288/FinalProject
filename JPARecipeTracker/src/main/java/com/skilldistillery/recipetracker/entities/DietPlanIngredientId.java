package com.skilldistillery.recipetracker.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class DietPlanIngredientId implements Serializable {

	private static final long serialVersionUID = 8094727562125191511L;

	private int planId;
	private int ingredientId;
	
	public DietPlanIngredientId() {
		super();
	}

	public DietPlanIngredientId(int planId, int ingredientId) {
		super();
		this.planId = planId;
		this.ingredientId = ingredientId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ingredientId, planId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DietPlanIngredientId other = (DietPlanIngredientId) obj;
		return ingredientId == other.ingredientId && planId == other.planId;
	}

	@Override
	public String toString() {
		return "DietPlanIngredientId [planId=" + planId + ", ingredientId=" + ingredientId + "]";
	}

}
