package com.example.mediamicroservice.dto;

import java.util.List;


public class StoryDTO {

	private String fileName;
    private String description;
    private String location;
    private List<String> tags;
    private Boolean visibleHighlights;
    private Boolean visibleForCloseFriends;
    
    
	public StoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public StoryDTO(String fileName, String description, String location, List<String> tags, Boolean visibleHighlights,
			Boolean visibleForCloseFriends) {
		super();
		this.fileName = fileName;
		this.description = description;
		this.location = location;
		this.tags = tags;
		this.visibleHighlights = visibleHighlights;
		this.visibleForCloseFriends = visibleForCloseFriends;
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Boolean getVisibleHighlights() {
		return visibleHighlights;
	}
	public void setVisibleHighlights(Boolean visibleHighlights) {
		this.visibleHighlights = visibleHighlights;
	}
	public Boolean getVisibleForCloseFriends() {
		return visibleForCloseFriends;
	}
	public void setVisibleForCloseFriends(Boolean visibleForCloseFriends) {
		this.visibleForCloseFriends = visibleForCloseFriends;
	}
    
    
    
    
}
