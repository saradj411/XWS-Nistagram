package com.example.profilemicroservice.dto;

import com.example.profilemicroservice.model.Category;

public class RequestFrontDTO {

	private String name;
    
	 
    private String lastname;
    
    
	 
    private String username;
  
    private Category category;


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


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}


	public RequestFrontDTO(String name, String lastname, Category category) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.category = category;
	}

	
	

	public RequestFrontDTO(String name, String lastname, String username, Category category) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.category = category;
	}


	public RequestFrontDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
    
    
    
  
}
