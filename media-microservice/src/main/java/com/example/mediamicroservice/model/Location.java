package com.example.mediamicroservice.model;

import javax.persistence.*;


@Entity
public class Location {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idLocation;

    @Column
    private String location;

	public Location() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Location(Long idLocation, String location) {
		super();
		this.idLocation = idLocation;
		this.location = location;
	}

	public Long getIdLocation() {
		return idLocation;
	}

	public void setIdLocation(Long idLocation) {
		this.idLocation = idLocation;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
    
}
