package com.example.mediamicroservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class PostContent {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idContent;

    @Column
    private String url;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post", referencedColumnName = "idPost")
    //@JsonBackReference
    private Post post;
}
