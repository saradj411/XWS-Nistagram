package com.example.profilemicroservice.model;


import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "confirmation_tokens")
public class ConfirmationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "datetime_created", nullable = false)
    private Date datetimeCreated;

    @Column(name = "used", nullable = false)
    private boolean used;

    @OneToOne
    private User user;

    public ConfirmationToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.datetimeCreated = new Date();
        this.used = false;
        this.user = user;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDatetimeCreated() {
		return datetimeCreated;
	}

	public void setDatetimeCreated(Date datetimeCreated) {
		this.datetimeCreated = datetimeCreated;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean used) {
		this.used = used;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ConfirmationToken() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConfirmationToken(Long id, String token, Date datetimeCreated, boolean used, User user) {
		super();
		this.id = id;
		this.token = token;
		this.datetimeCreated = datetimeCreated;
		this.used = used;
		this.user = user;
	}
    
}
