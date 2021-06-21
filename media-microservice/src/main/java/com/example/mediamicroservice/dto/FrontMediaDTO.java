package com.example.mediamicroservice.dto;


public class FrontMediaDTO {

	 private Long id;

	    
	    private String fileName;

	   
	    private Long idPost;

	    private byte[] imageByte;

		public FrontMediaDTO(Long id, String fileName, Long idPost) {
			super();
			this.id = id;
			this.fileName = fileName;
			this.idPost = idPost;
		}


		public FrontMediaDTO(Long id, String fileName, Long idPost, byte[] imageByte) {
			super();
			this.id = id;
			this.fileName = fileName;
			this.idPost = idPost;
			this.imageByte = imageByte;
		}


		public byte[] getImageByte() {
			return imageByte;
		}


		public void setImageByte(byte[] imageByte) {
			this.imageByte = imageByte;
		}


		public FrontMediaDTO() {
			super();
			// TODO Auto-generated constructor stub
		}


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getFileName() {
			return fileName;
		}


		public void setFileName(String fileName) {
			this.fileName = fileName;
		}


		public Long getIdPost() {
			return idPost;
		}


		public void setIdPost(Long idPost) {
			this.idPost = idPost;
		}
	    
	    
}
