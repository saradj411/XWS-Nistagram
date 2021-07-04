package com.example.mediamicroservice.dto;

import java.util.List;


public class PostDTO {

	
	    private String fileName;
	    private String description;
	    private String location;
	    private List<String> tags;

		public PostDTO() {
			super();
			// TODO Auto-generated constructor stub
		}



		public  List<String> getTags() {
			return tags;
		}



		public void setTags( List<String> tags) {
			this.tags = tags;
		}



		public PostDTO(String fileName, String description, String location,  List<String> tags) {
			super();
			this.fileName = fileName;
			this.description = description;
			this.location = location;
			this.tags = tags;
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
	    
	    
}
