package com.example.mediamicroservice.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.service.FavoritesService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/favorites")
public class FavoritesController {

	@Autowired
	FavoritesService favoritesService;
	
	@Autowired
	PostRepository postRepository;
	
	@PostMapping("/saveInFavorites/{username}/{post}")
	public ResponseEntity<CollectionFavourites> saveInFavorites(@PathVariable String username,@PathVariable Long post){
		Post post1=postRepository.getOne(post);
		CollectionFavourites collection=favoritesService.addPost(post1,username);
		
		
		return  collection == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<CollectionFavourites>(collection,HttpStatus.CREATED);
	}
}
