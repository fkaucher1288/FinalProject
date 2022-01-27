package com.skilldistillery.recipetracker.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DietPlanRecipeId implements Serializable {

	private static final long serialVersionUID = -4577406110493852300L;

	@Column(name = "diet_plan_id")
	private int planId;

	@Column(name = "recipe_id")
	private int recipeId;

	public DietPlanRecipeId() {
		super();
	}

	public DietPlanRecipeId(int planId, int recipeId) {
		super();
		this.planId = planId;
		this.recipeId = recipeId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(planId, recipeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DietPlanRecipeId other = (DietPlanRecipeId) obj;
		return planId == other.planId && recipeId == other.recipeId;
	}

	@Override
	public String toString() {
		return "DietPlanRecipeId [planId=" + planId + ", recipeId=" + recipeId + "]";
	}

}
