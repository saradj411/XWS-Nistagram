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

	public PostContent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostContent(Long idContent, String url, Post post) {
		super();
		this.idContent = idContent;
		this.url = url;
		this.post = post;
	}

	public Long getIdContent() {
		return idContent;
	}

	public void setIdContent(Long idContent) {
		this.idContent = idContent;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
    
    
}
