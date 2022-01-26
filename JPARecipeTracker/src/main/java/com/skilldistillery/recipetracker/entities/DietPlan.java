package com.skilldistillery.recipetracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class DietPlan {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="plan_name")
	private String planName;
	
	private String description;
	private boolean active;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public DietPlan(int id, String planName, String description, boolean active) {
		super();
		this.id = id;
		this.planName = planName;
		this.description = description;
		this.active = active;
	}
	public DietPlan() {
		super();
	}
	@Override
	public String toString() {
		return "DietPlan [id=" + id + ", planName=" + planName + ", description=" + description + ", active=" + active
				+ "]";
	}
	
	
}
