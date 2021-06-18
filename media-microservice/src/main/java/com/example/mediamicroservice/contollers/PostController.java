package com.example.mediamicroservice.contollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.services.PostService;

@RestController
@CrossOrigin
@RequestMapping(value="/post")

public class PostController {

	@Autowired
	PostService postService;
	
	 @PostMapping("/create")
	 public ResponseEntity<Post> addPost(@RequestBody PostDTO postDTO) {

		 Post post = postService.save(postDTO);
	        return new ResponseEntity<>(post, HttpStatus.CREATED);
	    }
}
