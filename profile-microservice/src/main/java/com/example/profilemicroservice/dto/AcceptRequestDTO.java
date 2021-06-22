package com.example.profilemicroservice.dto;

import com.example.profilemicroservice.model.Category;

public class AcceptRequestDTO {

	
	private String username;
	
	private Category category;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public AcceptRequestDTO(String username, Category category) {
		super();
		this.username = username;
		this.category = category;
	}
	
	
	public AcceptRequestDTO() {
		
	}
	
}
