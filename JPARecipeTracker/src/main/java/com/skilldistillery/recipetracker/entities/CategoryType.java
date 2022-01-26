package com.skilldistillery.recipetracker.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category_type")
public class CategoryType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String ethnicity;
	private String flavors;
	
	@Column(name="common_allergies")
	private String commonAllergies;
	private String lifestyle;
	
	
	public CategoryType() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getEthnicity() {
		return ethnicity;
	}


	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}


	public String getFlavors() {
		return flavors;
	}


	public void setFlavors(String flavors) {
		this.flavors = flavors;
	}


	public String getCommonAllergies() {
		return commonAllergies;
	}


	public void setCommonAllergies(String commonAllergies) {
		this.commonAllergies = commonAllergies;
	}


	public String getLifestyle() {
		return lifestyle;
	}


	public void setLifestyle(String lifestyle) {
		this.lifestyle = lifestyle;
	}


	@Override
	public int hashCode() {
		return Objects.hash(commonAllergies, ethnicity, flavors, id, lifestyle);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CategoryType other = (CategoryType) obj;
		return Objects.equals(commonAllergies, other.commonAllergies) && Objects.equals(ethnicity, other.ethnicity)
				&& Objects.equals(flavors, other.flavors) && id == other.id
				&& Objects.equals(lifestyle, other.lifestyle);
	}


	@Override
	public String toString() {
		return "CategoryType [id=" + id + ", ethnicity=" + ethnicity + ", flavors=" + flavors + ", commonAllergies="
				+ commonAllergies + ", lifestyle=" + lifestyle + "]";
	}
	
	
	
	
	
	

}
