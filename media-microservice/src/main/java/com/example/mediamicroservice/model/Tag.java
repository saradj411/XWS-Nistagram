package com.example.mediamicroservice.model;


import javax.persistence.*;


@Entity
public class Tag {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTag;

    @Column
    private String tagText;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post", referencedColumnName = "idPost")
    //@JsonBackReference
    private Post post;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "story", referencedColumnName = "idStory")
    //@JsonBackReference
    private Story story;

	public Tag(Long idTag, String tagText, Post post) {
		super();
		this.idTag = idTag;
		this.tagText = tagText;
		this.post = post;
	}
	
	

	public Story getStory() {
		return story;
	}



	public void setStory(Story story) {
		this.story = story;
	}



	public Tag(Long idTag, String tagText, Post post, Story story) {
		super();
		this.idTag = idTag;
		this.tagText = tagText;
		this.post = post;
		this.story = story;
	}



	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Tag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tag(Long idTag, String tagText) {
		super();
		this.idTag = idTag;
		this.tagText = tagText;
	}

	public Long getIdTag() {
		return idTag;
	}

	public void setIdTag(Long idTag) {
		this.idTag = idTag;
	}

	public String getTagText() {
		return tagText;
	}

	public void setTagText(String tagText) {
		this.tagText = tagText;
	}
    
}
