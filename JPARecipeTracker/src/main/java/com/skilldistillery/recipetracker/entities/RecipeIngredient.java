package com.skilldistillery.recipetracker.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

/**
 * Entity implementation class for Entity: RecipeIngredient
 *
 */
@Entity
public class RecipeIngredient implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecipeIngredientId id;
	
	@ManyToOne
	@MapsId("recipeId")
	private Recipe recipe;
	
	@ManyToOne
	@MapsId("ingredientId")
	private Ingredient ingredient;
	
	private double quantity;
	
	@Column(name="measurement_unit")
	private String measurementUnit;
	
	private String remarks;
	
	public RecipeIngredient() {
		super();
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(String measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
