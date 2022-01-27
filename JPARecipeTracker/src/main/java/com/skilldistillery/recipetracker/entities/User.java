package com.skilldistillery.recipetracker.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String username;
	private String email;
	private String password;
	private Boolean enabled;
	private String role;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_created")
	@CreationTimestamp
	private LocalDateTime dateCreated;

	@Column(name = "date_updated")
	@UpdateTimestamp
	private LocalDateTime dateUpdated;

	@Column(name = "image_url")
	private String imageURL;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Recipe> recipes;

	@OneToMany(mappedBy = "user")
	private List<Cookbook> cookbooks;

	@OneToMany(mappedBy = "user")
	private List<DietPlan> dietPlans;

	@OneToMany(mappedBy = "user")
	private List<FavoriteRecipe> favoriteRecipes;

	@OneToMany(mappedBy = "user")
	private List<RecipeReview> recipeReviews;

	@OneToMany(mappedBy = "user")
	private List<RecipeRating> recipeRatings;

	public User() {
		super();

	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String url) {
		this.imageURL = url;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDateTime getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(LocalDateTime dateCreated) {
		this.dateCreated = dateCreated;
	}

	public LocalDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(LocalDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public List<Cookbook> getCookbooks() {
		return cookbooks;
	}

	public void setCookbooks(List<Cookbook> cookbooks) {
		this.cookbooks = cookbooks;
	}

	public List<DietPlan> getDietPlans() {
		return dietPlans;
	}

	public void setDietPlans(List<DietPlan> dietPlans) {
		this.dietPlans = dietPlans;
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
		User other = (User) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", enabled=" + enabled
				+ ", role=" + role + "]";
	}

}
