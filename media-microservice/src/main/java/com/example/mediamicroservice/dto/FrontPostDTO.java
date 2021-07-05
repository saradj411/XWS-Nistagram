package com.example.mediamicroservice.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



public class FrontPostDTO {

    private Long idPost;
    
    private Integer numberOfInappropriateVote;

    private String description;

    private LocalDate date;

    private String location;

    private List<FrontMediaDTO> media=new ArrayList<FrontMediaDTO>();
    
    private List<FrontTagDTO> tags=new ArrayList<FrontTagDTO>();


    private String username;
    
    private Integer numberOfLikes;
    
   
    private Integer numberOfDislikes;

	public FrontPostDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public List<FrontTagDTO> getTags() {
		return tags;
	}


	public void setTags(List<FrontTagDTO> tags) {
		this.tags = tags;
	}


	public FrontPostDTO(Long idPost, String description, LocalDate date, String location, List<FrontMediaDTO> media,
			List<FrontTagDTO> tags, String username, Integer numberOfLikes, Integer numberOfDislikes) {
		super();
		this.idPost = idPost;
		this.description = description;
		this.date = date;
		this.location = location;
		this.media = media;
		this.tags = tags;
		this.username = username;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
	}


	public Integer getNumberOfLikes() {
		return numberOfLikes;
	}


	public void setNumberOfLikes(Integer numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}


	public Integer getNumberOfDislikes() {
		return numberOfDislikes;
	}


	public void setNumberOfDislikes(Integer numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}


	public FrontPostDTO(Long idPost, String description, LocalDate date, String location, List<FrontMediaDTO> media,
			List<FrontTagDTO> tags, String username) {
		super();
		this.idPost = idPost;
		this.description = description;
		this.date = date;
		this.location = location;
		this.media = media;
		this.tags = tags;
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


	public Integer getNumberOfInappropriateVote() {
		return numberOfInappropriateVote;
	}


	public void setNumberOfInappropriateVote(Integer numberOfInappropriateVote) {
		this.numberOfInappropriateVote = numberOfInappropriateVote;
	}
    
    
}

