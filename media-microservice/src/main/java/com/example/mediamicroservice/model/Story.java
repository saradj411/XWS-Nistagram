package com.example.mediamicroservice.model;


import java.time.LocalDate;

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
public class Story {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idHighlights;

    @Column
    private LocalDate date;
   
    @Column
    private Boolean visible24h;
    
    @Column
    private Boolean visibleHighlights;
    
    @Column
    private Boolean visibleForCloseFriends;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile", referencedColumnName = "id")
    //@JsonBackReference
    private Profile profile;
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "highlightsId", referencedColumnName = "idHighlights")
    //@JsonBackReference
    private Highlights highlights;
}


