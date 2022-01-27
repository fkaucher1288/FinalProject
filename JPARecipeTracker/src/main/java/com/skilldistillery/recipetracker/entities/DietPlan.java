package com.skilldistillery.recipetracker.entities;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dietplan")
public class DietPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "plan_name")
	private String planName;

	private String description;
	private boolean active;
	@JsonIgnore
	@OneToMany(mappedBy = "dietPlan")
	private List<DietPlanRecipe> recipes;
	@JsonIgnore
	@OneToMany(mappedBy = "dietPlan")
	private List<DietPlanIngredient> ingredients;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public List<DietPlanIngredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<DietPlanIngredient> dietPlanIngredients) {
		this.ingredients = dietPlanIngredients;
	}

	public List<DietPlanRecipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<DietPlanRecipe> dietPlanRecipes) {
		this.recipes = dietPlanRecipes;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DietPlan(int id, String planName, String description, boolean active) {
		super();
		this.id = id;
		this.planName = planName;
		this.description = description;
		this.active = active;
	}

	public DietPlan() {
		super();
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
		DietPlan other = (DietPlan) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "DietPlan [id=" + id + ", planName=" + planName + ", description=" + description + ", active=" + active
				+ "]";
	}

}
