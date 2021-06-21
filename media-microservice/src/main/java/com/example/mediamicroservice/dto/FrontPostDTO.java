package com.example.mediamicroservice.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Profile;

public class FrontPostDTO {

    private Long idPost;


    private String description;

 
    private LocalDate date;
    

    private String location;

    private List<FrontMediaDTO> media=new ArrayList<FrontMediaDTO>();

    private String username;

	public FrontPostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FrontPostDTO(Long idPost, String description, LocalDate date, String location, List<FrontMediaDTO> media,
			String username) {
		super();
		this.idPost = idPost;
		this.description = description;
		this.date = date;
		this.location = location;
		this.media = media;
		this.username = username;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<FrontMediaDTO> getMedia() {
		return media;
	}

	public void setMedia(List<FrontMediaDTO> media) {
		this.media = media;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
}

