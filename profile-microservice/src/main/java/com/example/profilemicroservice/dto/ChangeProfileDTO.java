package com.example.profilemicroservice.dto;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.example.profilemicroservice.model.Category;

public class ChangeProfileDTO {
	
	
	   private String usernameOfLoggedUser;
	 
	    private String name;
	    
	    private Long id;


	    private String surname;

	   
	    private String username;

	    
	    private String address;

	  
	    private String city;

	    
	    private String country;

	    
	    private String email;

	    
	    private String phone;
	    
	    
	  
	    

	    private String password;
	    
	    private String oldUsername;
	    
	
	    private Boolean privateProfil;
	    
	   
	    private Boolean messageFromUnfollowes;
	    
	   
	    private Boolean tagFromUnfollowers;

	    
	    private String biography;
	    
	    
	    private String webSite;


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getSurname() {
			return surname;
		}


		public void setSurname(String surname) {
			this.surname = surname;
		}


		public String getUsername() {
			return username;
		}


		public void setUsername(String username) {
			this.username = username;
		}


		public String getAddress() {
			return address;
		}


		public void setAddress(String address) {
			this.address = address;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getCountry() {
			return country;
		}


		public void setCountry(String country) {
			this.country = country;
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public String getPassword() {
			return password;
		}


		public void setPassword(String password) {
			this.password = password;
		}


		public String getOldUsername() {
			return oldUsername;
		}


		public void setOldUsername(String oldUsername) {
			this.oldUsername = oldUsername;
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


		public ChangeProfileDTO(String name, String surname, String username, String address, String city,
				String country, String email, String phone, String password, String oldUsername, Boolean privateProfil,
				Boolean messageFromUnfollowes, Boolean tagFromUnfollowers, String biography, String webSite) {
			super();
			this.name = name;
			this.surname = surname;
			this.username = username;
			this.address = address;
			this.city = city;
			this.country = country;
			this.email = email;
			this.phone = phone;
			this.password = password;
			this.oldUsername = oldUsername;
			this.privateProfil = privateProfil;
			this.messageFromUnfollowes = messageFromUnfollowes;
			this.tagFromUnfollowers = tagFromUnfollowers;
			this.biography = biography;
			this.webSite = webSite;
		}


		public String getUsernameOfLoggedUser() {
			return usernameOfLoggedUser;
		}


		public void setUsernameOfLoggedUser(String usernameOfLoggedUser) {
			this.usernameOfLoggedUser = usernameOfLoggedUser;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public ChangeProfileDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
	    
	    
	    


}
