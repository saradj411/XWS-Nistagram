package com.example.profilemicroservice.dto;

public class ProfileeDTO {

	
	private int id;
		
	private String username;
	
    private Boolean privateProfil;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getPrivateProfil() {
		return privateProfil;
	}

	public void setPrivateProfil(Boolean privateProfil) {
		this.privateProfil = privateProfil;
	}

	public ProfileeDTO(int id, String username, Boolean privateProfil) {
		super();
		this.id = id;
		this.username = username;
		this.privateProfil = privateProfil;
	}
    
    
}
