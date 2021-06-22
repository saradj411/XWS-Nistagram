package com.example.profilemicroservice.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.profilemicroservice.model.Category;
import com.example.profilemicroservice.model.Profile;

public class RequestDTO {



   
    private String name;
    
 
    private String lastname;

    
    private String image;
    
    
    @Enumerated(EnumType.STRING)
    private Category category;
    
  
    private String username;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public RequestDTO(String name, String lastname, String image, Category category, String username) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.image = image;
		this.category = category;
		this.username = username;
	}

	public RequestDTO() {
		
	}

	@Override
	public String toString() {
		return "RequestDTO [name=" + name + ", lastname=" + lastname + ", image=" + image + ", category=" + category
				+ ", username=" + username + "]";
	}
    
    
	
}
