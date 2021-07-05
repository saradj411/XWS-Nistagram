package com.example.mediamicroservice.model;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class CollectionFavourites {

	

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idFavourites;

    @Column
    private String name;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile", referencedColumnName = "id")
    //@JsonBackReference
    private Profile profile;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "post_favourite",
            joinColumns = @JoinColumn(name = "idFavourites", referencedColumnName = "idFavourites"),
            inverseJoinColumns = @JoinColumn(name = "idPost", referencedColumnName = "idPost"))
    private Set<Post> tags=new HashSet<Post>();
    
    
    
    public CollectionFavourites() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CollectionFavourites(Long idFavourites, String name, Profile profile, Set<Post> tags) {
		super();
		this.idFavourites = idFavourites;
		this.name = name;
		this.profile = profile;
		this.tags = tags;
	}


	public Long getIdFavourites() {
		return idFavourites;
	}


	public void setIdFavourites(Long idFavourites) {
		this.idFavourites = idFavourites;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Profile getProfile() {
		return profile;
	}


	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public Set<Post> getTags() {
		return tags;
	}


	public void setTags(Set<Post> tags) {
		this.tags = tags;
	}

}
