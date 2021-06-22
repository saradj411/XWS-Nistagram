package com.example.mediamicroservice.dto;

public class FrontTagDTO {

	 private Long id;

	    
	    private String tagText;

	   
	    private Long idPost;


		public FrontTagDTO() {
			super();
			// TODO Auto-generated constructor stub
		}


		public FrontTagDTO(Long id, String tagText, Long idPost) {
			super();
			this.id = id;
			this.tagText = tagText;
			this.idPost = idPost;
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getTagText() {
			return tagText;
		}


		public void setTagText(String tagText) {
			this.tagText = tagText;
		}


		public Long getIdPost() {
			return idPost;
		}


		public void setIdPost(Long idPost) {
			this.idPost = idPost;
		}
	    
	    
}
