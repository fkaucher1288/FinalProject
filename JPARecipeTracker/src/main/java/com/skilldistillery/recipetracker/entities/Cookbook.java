package com.skilldistillery.recipetracker.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cookbook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String title;
	
	private String author;
	private String description;
	@Column(name = "image_url")
	private String url;

//	@ManyToMany
//	@JoinTable(name = "cookbook_has_recipe", joinColumns = @JoinColumn(name = "cookbook_id"), inverseJoinColumns = @JoinColumn(name = "recipe_id"))
//	private List<Recipe> recipes;
	
	public Cookbook() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
		Cookbook other = (Cookbook) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Cookbook [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", url=" + url + "]";
	}


	
}
