package com.skilldistillery.recipetracker.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dietplan_recipe")
public class DietPlanRecipe {

	@EmbeddedId
	private DietPlanRecipeId id;

	@Column(name = "sequence_number")
	private int sequenceNumber;

	@Column(name = "day_name")
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayName;
	@JsonIgnore
	@ManyToOne
	@MapsId("planId")
	@JoinColumn(name="diet_plan_id")
	private DietPlan dietPlan;
	@JsonIgnore
	@ManyToOne
	@MapsId("recipeId")
	private Recipe recipe;
	
	public DietPlanRecipe() {
		super();
	}

	public DietPlanRecipe(DietPlanRecipeId id, int sequenceNumber, DayOfWeek dayName, DietPlan dietPlan,
			Recipe recipe) {
		super();
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.dayName = dayName;
		this.dietPlan = dietPlan;
		this.recipe = recipe;
	}

	public int getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public DayOfWeek getDayName() {
		return dayName;
	}

	public void setDayName(DayOfWeek dayName) {
		this.dayName = dayName;
	}

	public DietPlan getDietPlan() {
		return dietPlan;
	}

	public void setDietPlan(DietPlan dietPlan) {
		this.dietPlan = dietPlan;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
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
		DietPlanRecipe other = (DietPlanRecipe) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "DietPlanRecipe [id=" + id + ", sequenceNumber=" + sequenceNumber + ", dayName=" + dayName + "]";
	}

}
