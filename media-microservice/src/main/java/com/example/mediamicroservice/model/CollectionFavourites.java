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
}
