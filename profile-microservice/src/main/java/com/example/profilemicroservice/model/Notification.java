package com.example.profilemicroservice.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Notification {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @Column
    private String text;

    @ManyToOne
    //@JsonBackReference
    @JoinColumn(name = "fromWhoUsername", referencedColumnName = "id")
    private Profile fromWhoUsername;
    
    @ManyToOne
    @JoinColumn(name = "toWhoUsername", referencedColumnName = "id")
    //@JsonBackReference
    private Profile toWhoUsername;
    
    @Column
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;
    
    @Column 
    @Enumerated(EnumType.STRING)
    private NotificationType type;
    
    @Column 
    private Boolean notificationSeen;
    
    public Notification() {}

	public Notification(String text, Profile fromWhoUsername, Profile toWhoUsername,
			 NotificationType type) {
		
		this.text = text;
		this.fromWhoUsername = fromWhoUsername;
		this.toWhoUsername = toWhoUsername;
		this.date = LocalDateTime.now();
		this.type = type;
		this.notificationSeen = false;
	}
	public Notification(String text, Profile fromWhoUsername, Profile toWhoUsername,
			 NotificationType type, Boolean nofiticationSeen) {
		
		this.text = text;
		this.fromWhoUsername = fromWhoUsername;
		this.toWhoUsername = toWhoUsername;
		this.date = LocalDateTime.now();
		this.type = type;
		this.notificationSeen = notificationSeen;
	}
	
	 		

	public Notification(Long idNotification, String text, Profile fromWhoUsername, Profile toWhoUsername,
			LocalDateTime date, NotificationType type, Boolean notificationSeen) {
		super();
		this.idNotification = idNotification;
		this.text = text;
		this.fromWhoUsername = fromWhoUsername;
		this.toWhoUsername = toWhoUsername;
		this.date = date;
		this.type = type;
		this.notificationSeen = notificationSeen;
	}

	public Long getIdNotification() {
		return idNotification;
	}

	public void setIdNotification(Long idNotification) {
		this.idNotification = idNotification;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Profile getFromWhoUsername() {
		return fromWhoUsername;
	}

	public void setFromWhoUsername(Profile fromWhoUsername) {
		this.fromWhoUsername = fromWhoUsername;
	}

	public Profile getToWhoUsername() {
		return toWhoUsername;
	}

	public void setToWhoUsername(Profile toWhoUsername) {
		this.toWhoUsername = toWhoUsername;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public NotificationType getType() {
		return type;
	}

	public void setType(NotificationType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Notification [idNotification=" + idNotification + ", text=" + text + ", fromWhoUsername="
				+ fromWhoUsername + ", toWhoUsername=" + toWhoUsername + ", date=" + date + ", type=" + type + "]";
	}

	public Boolean getNotificationSeen() {
		return notificationSeen;
	}

	public void setNotificationSeen(Boolean notificationSeen) {
		this.notificationSeen = notificationSeen;
	}
    
    
    

}
