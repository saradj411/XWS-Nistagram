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

	public Long getIdComment() {
		return idComment;
	}

	public void setIdComment(Long idComment) {
		this.idComment = idComment;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Comment(Long idComment, String description, Profile profile, Post post) {
		super();
		this.idComment = idComment;
		this.description = description;
		this.profile = profile;
		this.post = post;
	}

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}
