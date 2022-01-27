package com.skilldistillery.recipetracker.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	@Column(name = "date_created")
	private LocalDateTime dateCreated;
	private boolean active;

	@ManyToOne
	@JoinColumn(name = "creator_id")
	private User user;

	@Column(name = "is_public")
	private boolean isPublic;

	@Column(name = "prep_time")
	private String prepTime;

	@Column(name = "cook_time")
	private String cookTime;
	private String description;
	private String instructions;
	private String notes;

	@Column(name = "photo_link")
	private String imageURL;

	@Column(name = "web_link")
	private String webLink;

	@ManyToMany(mappedBy = "recipes")
	private List<Category> categories;

	@ManyToMany(mappedBy = "recipes")
	private List<Cookbook> cookbooks;

	@OneToMany(mappedBy = "recipe")
	private List<DietPlanRecipe> dietPlanRecipe;

	@OneToMany(mappedBy = "recipe")
	private List<FavoriteRecipe> favoriteRecipes;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeReview> recipeReviews;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeRating> recipeRatings;

	@OneToMany(mappedBy = "recipe")
	private List<RecipeIngredient> recipeIngredients;

	public Recipe() {
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

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(String prepTime) {
		this.prepTime = prepTime;
	}

	public String getCookTime() {
		return cookTime;
	}

	public void setCookTime(String cookTime) {
		this.cookTime = cookTime;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getWebLink() {
		return webLink;
	}

	public void setWebLink(String webLink) {
		this.webLink = webLink;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Cookbook> getCookbooks() {
		return cookbooks;
	}

	public void setCookbooks(List<Cookbook> cookbooks) {
		this.cookbooks = cookbooks;
	}

	public List<DietPlanRecipe> getDietPlanRecipe() {
		return dietPlanRecipe;
	}

	public void setDietPlanRecipe(List<DietPlanRecipe> dietPlanRecipe) {
		this.dietPlanRecipe = dietPlanRecipe;
	}

	public List<FavoriteRecipe> getFavoriteRecipes() {
		return favoriteRecipes;
	}

	public void setFavoriteRecipes(List<FavoriteRecipe> favoriteRecipes) {
		this.favoriteRecipes = favoriteRecipes;
	}

	public List<RecipeReview> getRecipeReviews() {
		return recipeReviews;
	}

	public void setRecipeReviews(List<RecipeReview> recipeReviews) {
		this.recipeReviews = recipeReviews;
	}

	public List<RecipeRating> getRecipeRatings() {
		return recipeRatings;
	}

	public void setRecipeRatings(List<RecipeRating> recipeRatings) {
		this.recipeRatings = recipeRatings;
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
		Recipe other = (Recipe) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", dateCreated=" + dateCreated + ", active=" + active
				+ ", isPublic=" + isPublic + ", prepTime=" + prepTime + ", cookTime=" + cookTime + ", description="
				+ description + ", instructions=" + instructions + ", notes=" + notes + ", imageURL=" + imageURL
				+ ", webLink=" + webLink + "]";
	}

}
