package com.example.mediamicroservice.model;


import java.time.LocalDate;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Story {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idStory;

    @Column
    private LocalDate date;
    
    @Column
    private String description;
    
    @Column
    private String location;
   
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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mediaId", referencedColumnName = "id")
    private Media media;
    
    @OneToMany(mappedBy = "story", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonBackReference
    private Set<Tag> tags = new HashSet<Tag>();
    
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "highlightsId", referencedColumnName = "idHighlights")
    //@JsonBackReference
    private Highlights highlights;


	public Story() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Story(Long idStory, LocalDate date, String description, String location, Boolean visible24h,
			Boolean visibleHighlights, Boolean visibleForCloseFriends, Profile profile, Media media, Set<Tag> tags,
			Highlights highlights) {
		super();
		this.idStory = idStory;
		this.date = date;
		this.description = description;
		this.location = location;
		this.visible24h = visible24h;
		this.visibleHighlights = visibleHighlights;
		this.visibleForCloseFriends = visibleForCloseFriends;
		this.profile = profile;
		this.media = media;
		this.tags = tags;
		this.highlights = highlights;
	}





	public Long getIdStory() {
		return idStory;
	}


	public void setIdStory(Long idStory) {
		this.idStory = idStory;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Boolean getVisible24h() {
		return visible24h;
	}


	public void setVisible24h(Boolean visible24h) {
		this.visible24h = visible24h;
	}


	public Boolean getVisibleHighlights() {
		return visibleHighlights;
	}


	public void setVisibleHighlights(Boolean visibleHighlights) {
		this.visibleHighlights = visibleHighlights;
	}


	public Boolean getVisibleForCloseFriends() {
		return visibleForCloseFriends;
	}


	public void setVisibleForCloseFriends(Boolean visibleForCloseFriends) {
		this.visibleForCloseFriends = visibleForCloseFriends;
	}


	public Profile getProfile() {
		return profile;
	}


	public void setProfile(Profile profile) {
		this.profile = profile;
	}


	public Media getMedia() {
		return media;
	}


	public void setMedia(Media media) {
		this.media = media;
	}


	public Set<Tag> getTags() {
		return tags;
	}


	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}


	public Highlights getHighlights() {
		return highlights;
	}


	public void setHighlights(Highlights highlights) {
		this.highlights = highlights;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
  
    
}


