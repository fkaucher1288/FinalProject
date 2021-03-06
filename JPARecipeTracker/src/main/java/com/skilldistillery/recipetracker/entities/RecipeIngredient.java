package com.skilldistillery.recipetracker.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipe_ingredient")
//@SecondaryTable(name="ingredient", pkJoinColumns = @PrimaryKeyJoinColumn(name="ingredient_id"))
public class RecipeIngredient implements Serializable {

	private static final long serialVersionUID = 8909455029739107935L;

	@EmbeddedId
	private RecipeIngredientId id;
	@JsonIgnore
	@ManyToOne
	@MapsId("recipeId")
	private Recipe recipe;
	@ManyToOne
	@MapsId("ingredientId")
	private Ingredient ingredient;

	private double quantity;

	private String remarks;
	
//	@Column(name="name", table="ingredient")
//	private String name;
	
	

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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}

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
		RecipeIngredient other = (RecipeIngredient) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "RecipeIngredient [id=" + id + ", quantity=" + quantity + ", remarks=" + remarks + "]";
	}

}
