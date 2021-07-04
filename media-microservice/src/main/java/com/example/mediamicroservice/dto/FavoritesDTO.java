package com.example.mediamicroservice.dto;

import java.util.HashSet;
import java.util.Set;

import com.example.mediamicroservice.model.Profile;

public class FavoritesDTO {

	private Long idFavourites;

    private String name;
    
    private Profile profile;

    private Set<Long> posts=new HashSet<Long>();
    
    private String fileName;

    private byte[] imageByte;
    
	public FavoritesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public FavoritesDTO(Long idFavourites, String name, Profile profile, Set<Long> posts, String fileName,
			byte[] imageByte) {
		super();
		this.idFavourites = idFavourites;
		this.name = name;
		this.profile = profile;
		this.posts = posts;
		this.fileName = fileName;
		this.imageByte = imageByte;
	}



	public FavoritesDTO(Long idFavourites, String name, Profile profile, Set<Long> posts, String fileName) {
		super();
		this.idFavourites = idFavourites;
		this.name = name;
		this.profile = profile;
		this.posts = posts;
		this.fileName = fileName;
	}



	public Long getIdFavourites() {
		return idFavourites;
	}

	public void setIdFavourites(Long idFavourites) {
		this.idFavourites = idFavourites;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<Long> getPosts() {
		return posts;
	}

	public void setPosts(Set<Long> posts) {
		this.posts = posts;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public byte[] getImageByte() {
		return imageByte;
	}



	public void setImageByte(byte[] imageByte) {
		this.imageByte = imageByte;
	}

	

	
    
    
    
    
}
