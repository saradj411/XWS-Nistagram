package com.example.mediamicroservice.model;


import javax.persistence.*;


@Entity
public class Comment {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idComment;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile", referencedColumnName = "id")
    //@JsonBackReference
    private Profile profile;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post", referencedColumnName = "idPost")
    //@JsonBackReference
    private Post post;
}
