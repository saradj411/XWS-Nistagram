package com.example.mediamicroservice.dto;

public class CommentDTO {
	
	private String usernameTo;  // kome komentarisem
	//private String fileName;
	private String usernameFrom;  //ko komentarise
	private String comment;
	private Long postId;
	public String getUsernameTo() {
		return usernameTo;
	}
	public void setUsernameTo(String usernameTo) {
		this.usernameTo = usernameTo;
	}
	
	public String getUsernameFrom() {
		return usernameFrom;
	}
	public void setUsernameFrom(String usernameFrom) {
		this.usernameFrom = usernameFrom;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getPostId() {
		return postId;
	}
	public void setPostId(Long postId) {
		this.postId = postId;
	}

	
	
	public CommentDTO(String usernameTo,String usernameFrom, String comment, Long postId) {
		super();
		this.usernameTo = usernameTo;
		this.usernameFrom = usernameFrom;
		this.comment = comment;
		this.postId = postId;
	}
	public CommentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
		
}
