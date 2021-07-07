package com.example.profilemicroservice.dto;

import com.example.profilemicroservice.model.Category;

public class RequestFrontDTO {

	private String name;
    
    private String lastname;
    
    private String username;
  
    private Category category;
    
    private String image;

    private byte[] imageByte;

    
	public RequestFrontDTO(String name, String lastname, Category category) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.category = category;
	}

	
	

	public RequestFrontDTO(String name, String lastname, String username, Category category, String image,
			byte[] imageByte) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.category = category;
		this.image = image;
		this.imageByte = imageByte;
	}




	public RequestFrontDTO(String name, String lastname, String username, Category category, String image) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.username = username;
		this.category = category;
		this.image = image;
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

	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}

	public byte[] getImageByte() {
		return imageByte;
	}

	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
    
    
    
  
}
