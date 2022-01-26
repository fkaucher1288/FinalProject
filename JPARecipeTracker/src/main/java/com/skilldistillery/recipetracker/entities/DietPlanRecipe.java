package com.skilldistillery.recipetracker.entities;

import java.util.Objects;

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
	@Column(name="sequence_number")
	private int sequenceNumber;
	@Column(name="day_name")
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	public DayOfWeek getDayName() {
		return dayName;
	}
	public void setDayName(DayOfWeek dayName) {
		this.dayName = dayName;
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
		DietPlanRecipe other = (DietPlanRecipe) obj;
		return id == other.id;
	}
	public DietPlanRecipe(int id, int sequenceNumber, DayOfWeek dayName) {
		super();
		this.id = id;
		this.sequenceNumber = sequenceNumber;
		this.dayName = dayName;
	}
	public DietPlanRecipe() {
		super();
	}
	@Override
	public String toString() {
		return "DietPlanRecipe [id=" + id + ", sequenceNumber=" + sequenceNumber + ", dayName=" + dayName + "]";
	}

	
}
