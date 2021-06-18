package com.example.mediamicroservice.dto;

public class PostDTO {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PostDTO(String name) {
		super();
		this.name = name;
	}

	public PostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
