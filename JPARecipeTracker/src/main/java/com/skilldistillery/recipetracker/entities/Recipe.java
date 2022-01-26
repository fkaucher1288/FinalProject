package com.skilldistillery.recipetracker.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="date_created")
	private LocalDateTime dateCreated;
	private boolean active;
	
	//private User user;
	
	@Column(name="is_public")
	private boolean isPublic;
	
	@Column(name="prep_time")	
	private String prepTime;
	private String instructions;
	
	@Column(name="photo_link")
	private String imageURL;
	
	@Column(name="web_link")
	private String webLink;
	private String description;
	
	
	public Recipe() {
		super();
	}
	
	
	
	
	
	
	
	

}
