package com.skilldistillery.recipetracker.entities;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dietplan_ingredient")
public class DietPlanIngredient {

	@EmbeddedId
	private DietPlanIngredientId id;
	@JsonIgnore
	@ManyToOne
	@MapsId("planId")
	@JoinColumn(name = "diet_plan_id")
	private DietPlan dietPlan;// @JoinColumn necessary else it resolves to `dietPlan_id`
	@JsonIgnore
	@ManyToOne
	@MapsId("ingredientId")
	private Ingredient ingredient; // @JoinColumn -not- necessary because it resolves to `ingredient_id`

	private Boolean purchased;

	public DietPlanIngredient() {
		super();
	}

	public DietPlanIngredient(DietPlanIngredientId id, Boolean purchased, DietPlan dietPlan, Ingredient ingredient) {
		super();
		this.id = id;
		this.purchased = purchased;
		this.dietPlan = dietPlan;
		this.ingredient = ingredient;
	}

	public Boolean getPurchased() {
		return purchased;
	}

	public void setPurchased(Boolean purchased) {
		this.purchased = purchased;
	}

	public DietPlan getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(DietPlan dietPlan) {
		this.dietPlan = dietPlan;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
		DietPlanIngredient other = (DietPlanIngredient) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "DietPlanIngredient [id=" + id + ", purchased=" + purchased + "]";
	}

}
