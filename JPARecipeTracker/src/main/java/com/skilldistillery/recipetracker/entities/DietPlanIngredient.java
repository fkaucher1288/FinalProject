package com.skilldistillery.recipetracker.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="dietplan_ingredient")
public class DietPlanIngredient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Boolean purchased;
	@ManyToOne
	@JoinColumn(name="diet_plan_id")
	private DietPlan dietPlan;
	
	@ManyToOne
	@JoinColumn(name="ingredient_id")
	private Ingredient ingredient;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	
	public DietPlanIngredient() {
		super();
	}
	public DietPlanIngredient(int id, Boolean purchased) {
		super();
		this.id = id;
		this.purchased = purchased;
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
