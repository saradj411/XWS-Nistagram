package com.example.mediamicroservice.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Post {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idPost;

    @Column
    private String description;

    @Column
    private LocalDate date;
    
    @Column
    private String location;

    @Column
    private Integer postLimit;
    
    @Column 
    private Integer numberOfInappropriateVote;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_tags",
            joinColumns = @JoinColumn(name = "idPost", referencedColumnName = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "profileId", referencedColumnName = "id"))
    private Set<Profile> tags=new HashSet<Profile>();
    
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_shared",
            joinColumns = @JoinColumn(name = "idPost", referencedColumnName = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "profileId", referencedColumnName = "id"))
    private Set<Profile> shared=new HashSet<Profile>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_like",
            joinColumns = @JoinColumn(name = "idPost", referencedColumnName = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "profileId", referencedColumnName = "id"))
    private Set<Profile> like=new HashSet<Profile>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_dislike",
            joinColumns = @JoinColumn(name = "idPost", referencedColumnName = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "profileId", referencedColumnName = "id"))
    private Set<Profile> dislike=new HashSet<Profile>();
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile", referencedColumnName = "id")
    //@JsonBackReference
    private Profile profile;
    
  
    
}
