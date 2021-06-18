package com.example.mediamicroservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.iservices.IPostService;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.repositories.PostRepository;

@Service
public class PostService implements IPostService{

	@Autowired
	PostRepository postRepository;
	
	@Override
	public Post save(PostDTO post) {
		Post d = new Post();
        d.setName(post.getName());
        postRepository.save(d);
        
        return d;
	}

}
