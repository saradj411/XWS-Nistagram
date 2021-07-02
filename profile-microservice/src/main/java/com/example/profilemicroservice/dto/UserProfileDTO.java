package com.example.profilemicroservice.dto;

import javax.persistence.Column;

public class UserProfileDTO {
	
	
	private String username;	
	
    private Boolean privateProfil;    
   
    private Boolean messageFromUnfollowes;    
   
    private Boolean tagFromUnfollowers;
   
    private String biography;
    
    private String webSite;
    
    public UserProfileDTO() {}

	public UserProfileDTO(String username, Boolean privateProfil, Boolean messageFromUnfollowes,
			Boolean tagFromUnfollowers, String biography, String webSite) {
		super();
		this.username = username;
		this.privateProfil = privateProfil;
		this.messageFromUnfollowes = messageFromUnfollowes;
		this.tagFromUnfollowers = tagFromUnfollowers;
		this.biography = biography;
		this.webSite = webSite;
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

	public Boolean getMessageFromUnfollowes() {
		return messageFromUnfollowes;
	}

	public void setMessageFromUnfollowes(Boolean messageFromUnfollowes) {
		this.messageFromUnfollowes = messageFromUnfollowes;
	}

	public Boolean getTagFromUnfollowers() {
		return tagFromUnfollowers;
	}

	public void setTagFromUnfollowers(Boolean tagFromUnfollowers) {
		this.tagFromUnfollowers = tagFromUnfollowers;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	@Override
	public String toString() {
		return "UserProfileDTO [username=" + username + ", privateProfil=" + privateProfil + ", messageFromUnfollowes="
				+ messageFromUnfollowes + ", tagFromUnfollowers=" + tagFromUnfollowers + ", biography=" + biography
				+ ", webSite=" + webSite + "]";
	}
   
    
    

}
