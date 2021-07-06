package com.example.mediamicroservice.model;


import javax.persistence.*;


@Entity
public class Tag {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTag;

    @Column
    private String tagText;//ovo je username u slucaju tagovanih profila
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "post", referencedColumnName = "idPost")
    //@JsonBackReference
    private Post post;

	public Tag(Long idTag, String tagText, Post post) {
		super();
		this.idTag = idTag;
		this.tagText = tagText;
		this.post = post;
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
