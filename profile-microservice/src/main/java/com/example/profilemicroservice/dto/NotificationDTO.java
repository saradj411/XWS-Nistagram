package com.example.profilemicroservice.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.profilemicroservice.model.NotificationType;
import com.example.profilemicroservice.model.Profile;
import com.fasterxml.jackson.annotation.JsonFormat;

public class NotificationDTO {
	
	private String text;   
    private String toWhoUsername;    
    private NotificationType type;
    
    public NotificationDTO() {}
    
	public NotificationDTO(String text,  String toWhoUsername, NotificationType type) {
		super();
		this.text = text;		
		this.toWhoUsername = toWhoUsername;
		this.type = type;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	

	public String getToWhoUsername() {
		return toWhoUsername;
	}

	public void setToWhoUsername(String toWhoUsername) {
		this.toWhoUsername = toWhoUsername;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

    
}
