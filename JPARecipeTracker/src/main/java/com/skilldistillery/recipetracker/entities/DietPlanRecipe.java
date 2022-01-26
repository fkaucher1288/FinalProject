package com.skilldistillery.recipetracker.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dietplan_recipe")
public class DietPlanRecipe {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int sequenceNumber;
	@Column(name="day_name")
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayName;

}
