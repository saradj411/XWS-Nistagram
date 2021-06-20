package com.example.mediamicroservice.dto;

public class ProfileDTO {

	private String newPassword;
	
	private String oldPassword;

	public ProfileDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProfileDTO(String newPassword, String oldPassword) {
		super();
		this.newPassword = newPassword;
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	
}
