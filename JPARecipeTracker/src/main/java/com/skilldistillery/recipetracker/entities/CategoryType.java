package com.skilldistillery.recipetracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CategoryType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String ethnicity;
	private String flavors;
	@Column(name="common_allergies")
	private String commonAllergies;
	private String lifestyle;

}
