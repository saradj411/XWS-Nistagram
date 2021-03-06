package com.example.profilemicroservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Request {

	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRequest;

    @Column
    private String name;
    
    @Column
    private String lastname;

    @Column
    private String image;
    
    @Column
    private Boolean processed ;
    
    
    @Column
    @Enumerated(EnumType.STRING)
    private Category category;
    
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "username", referencedColumnName = "id")
	 * //@JsonManagedReference
	 */    
    
    @Column
    private String username;

	public Long getIdRequest() {
		return idRequest;
	}

	public void setIdRequest(Long idRequest) {
		this.idRequest = idRequest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Request(Long idRequest, String name, String lastname, String image, Category category, String username) {
		super();
		this.idRequest = idRequest;
		this.name = name;
		this.lastname = lastname;
		this.image = image;
		this.category = category;
		this.username = username;
	}
	public Request() {
		
	}

	@Override
	public String toString() {
		return "Request [idRequest=" + idRequest + ", name=" + name + ", lastname=" + lastname + ", image=" + image
				+ ", category=" + category + ", username=" + username + "]";
	}

	public Boolean getProcessed() {
		return processed;
	}

	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
    

}
