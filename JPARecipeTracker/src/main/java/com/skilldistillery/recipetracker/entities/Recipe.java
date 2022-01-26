package com.skilldistillery.recipetracker.entities;

import java.time.LocalDateTime;
import java.util.Objects;

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
	
	@Column(name="cook_time")	
	private String cookTime;
	private String description;
	private String instructions;
	private String notes;
	
	@Column(name="photo_link")
	private String imageURL;
	
	@Column(name="web_link")
	private String webLink;
	
//------------------------constructor---------------------	
	public Recipe() {
		super();
	}

//---------------GETTERS/SETTERS---------------------------

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

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
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
//--------------------hashcode/equals-----------------

	@Override
	public int hashCode() {
		return Objects.hash(active, cookTime, dateCreated, description, id, imageURL, instructions, isPublic, name,
				notes, webLink);
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
		return active == other.active && Objects.equals(cookTime, other.cookTime)
				&& Objects.equals(dateCreated, other.dateCreated) && Objects.equals(description, other.description)
				&& id == other.id && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(instructions, other.instructions) && isPublic == other.isPublic
				&& Objects.equals(name, other.name) && Objects.equals(notes, other.notes)
				&& Objects.equals(webLink, other.webLink);
	}

//-----------------------toString---------------------------

	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", dateCreated=" + dateCreated + ", active=" + active
				+ ", isPublic=" + isPublic + ", cookTime=" + cookTime + ", description=" + description
				+ ", instructions=" + instructions + ", notes=" + notes + ", imageURL=" + imageURL + ", webLink="
				+ webLink + "]";
	}




	
	





	
	
	
	
	
	
	
	
	
	
	
	
	

}
