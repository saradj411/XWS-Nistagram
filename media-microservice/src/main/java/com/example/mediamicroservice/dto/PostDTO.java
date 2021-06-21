package com.example.mediamicroservice.dto;

public class PostDTO {

	
	    private String fileName;

		public PostDTO() {
			super();
			// TODO Auto-generated constructor stub
		}

		public PostDTO(String fileName) {
			super();
			this.fileName = fileName;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
	    
	    
}
