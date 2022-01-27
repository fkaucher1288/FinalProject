package com.skilldistillery.recipetracker.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Ingredient
 *
 */
@Entity
public class Ingredient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private String brand;
	@Column(name="measurement_unit")
	private String measurement;

	private String category;
	@OneToMany(mappedBy="ingredient")
	private List<DietPlanIngredient> dietPlanIngredients;
	
	@OneToMany(mappedBy="ingredient")
	private List<RecipeIngredient> recipeIngredients; 
	
	public Ingredient() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	

	public List<DietPlanIngredient> getDietPlanIngredients() {
		return dietPlanIngredients;
	}

	public void setDietPlanIngredients(List<DietPlanIngredient> dietPlanIngredients) {
		this.dietPlanIngredients = dietPlanIngredients;
	}

	public List<RecipeIngredient> getRecipeIngredients() {
		return recipeIngredients;
	}

	public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
		this.recipeIngredients = recipeIngredients;
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
		Ingredient other = (Ingredient) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Ingredient [id=" + id + ", name=" + name + ", brand=" + brand + ", amount=" + measurement + ", category="
				+ category + "]";
	}

}
