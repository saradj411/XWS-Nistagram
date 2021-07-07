package com.example.mediamicroservice.dto;

public class CommntsDTO {
	private String txt;
	private String username;
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public CommntsDTO(String txt, String username) {
		super();
		this.txt = txt;
		this.username = username;
	}
	public CommntsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
