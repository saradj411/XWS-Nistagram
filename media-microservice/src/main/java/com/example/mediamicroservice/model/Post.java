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
    
    @Column 
    private Integer numberOfLikes;
    
    @Column 
    private Integer numberOfDislikes;
    
    /*
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<PostContent> content = new HashSet<PostContent>();
    */
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonBackReference
    private Set<Media> media = new HashSet<Media>();
    
    @OneToMany(mappedBy = "post", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //@JsonBackReference
    private Set<Tag> tags = new HashSet<Tag>();
    
    /*
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "profile_tags",
            joinColumns = @JoinColumn(name = "idPost", referencedColumnName = "idPost"),
            inverseJoinColumns = @JoinColumn(name = "profileId", referencedColumnName = "id"))
    private Set<Profile> tags=new HashSet<Profile>();
    */
   
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
    private Profile profile;//ciji je post

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(Long idPost, String description, LocalDate date, String location, Integer postLimit,
			Integer numberOfInappropriateVote, Set<PostContent> content, Set<Tag> tags, Set<Profile> shared,
			Set<Profile> like, Set<Profile> dislike, Profile profile) {
		super();
		this.idPost = idPost;
		this.description = description;
		this.date = date;
		this.location = location;
		this.postLimit = postLimit;
		this.numberOfInappropriateVote = numberOfInappropriateVote;
		//this.content = content;
		this.tags = tags;
		this.shared = shared;
		this.like = like;
		this.dislike = dislike;
		this.profile = profile;
	}

	public Post(Long idPost, String description, LocalDate date, String location, Integer postLimit,
			Integer numberOfInappropriateVote, Integer numberOfLikes, Integer numberOfDislikes, Set<Media> media,
			Set<Tag> tags, Set<Profile> shared, Set<Profile> like, Set<Profile> dislike, Profile profile) {
		super();
		this.idPost = idPost;
		this.description = description;
		this.date = date;
		this.location = location;
		this.postLimit = postLimit;
		this.numberOfInappropriateVote = numberOfInappropriateVote;
		this.numberOfLikes = numberOfLikes;
		this.numberOfDislikes = numberOfDislikes;
		this.media = media;
		this.tags = tags;
		this.shared = shared;
		this.like = like;
		this.dislike = dislike;
		this.profile = profile;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(Integer numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public Integer getNumberOfDislikes() {
		return numberOfDislikes;
	}

	public void setNumberOfDislikes(Integer numberOfDislikes) {
		this.numberOfDislikes = numberOfDislikes;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPostLimit() {
		return postLimit;
	}

	public void setPostLimit(Integer postLimit) {
		this.postLimit = postLimit;
	}

	public Integer getNumberOfInappropriateVote() {
		return numberOfInappropriateVote;
	}

	public void setNumberOfInappropriateVote(Integer numberOfInappropriateVote) {
		this.numberOfInappropriateVote = numberOfInappropriateVote;
	}
/*Set<PostContent> getContent() {
		return content;
	}

	public void setContent(Set<PostContent> content) {
		this.content = content;
	}
*/
	
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	
	public Set<Profile> getShared() {
		return shared;
	}

	public void setShared(Set<Profile> shared) {
		this.shared = shared;
	}

	public Set<Profile> getLike() {
		return like;
	}

	public void setLike(Set<Profile> like) {
		this.like = like;
	}

	public Set<Profile> getDislike() {
		return dislike;
	}

	public void setDislike(Set<Profile> dislike) {
		this.dislike = dislike;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Set<Media> getMedia() {
		return media;
	}

	public void setMedia(Set<Media> media) {
		this.media = media;
	}
    
  
    
}
