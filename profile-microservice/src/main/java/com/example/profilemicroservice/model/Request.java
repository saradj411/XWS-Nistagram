package com.example.profilemicroservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Category category;
    
    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "id")
    //@JsonManagedReference
    private Profile username;
    

}
