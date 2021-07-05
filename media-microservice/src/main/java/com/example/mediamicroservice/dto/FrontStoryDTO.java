package com.example.mediamicroservice.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FrontStoryDTO {

	 private Long idStory;

	    private String description;

	    private LocalDate date;

	    private String location;

	    private FrontMediaDTO media;
	    
	    private List<FrontTagDTO> tags=new ArrayList<FrontTagDTO>();

	    private String username;

		public FrontStoryDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public FrontStoryDTO(Long idStory, String description, LocalDate date, String location,
				FrontMediaDTO media, List<FrontTagDTO> tags, String username) {
			super();
			this.idStory = idStory;
			this.description = description;
			this.date = date;
			this.location = location;
			this.media = media;
			this.tags = tags;
			this.username = username;
		}

		public Long getIdStory() {
			return idStory;
		}

		public void setIdStory(Long idStory) {
			this.idStory = idStory;
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

		public FrontMediaDTO getMedia() {
			return media;
		}

		public void setMedia(FrontMediaDTO media) {
			this.media = media;
		}

		public List<FrontTagDTO> getTags() {
			return tags;
		}

		public void setTags(List<FrontTagDTO> tags) {
			this.tags = tags;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	    
	    
	    
}
