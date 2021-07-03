package com.example.mediamicroservice.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.FavoritesDTO;
import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.service.FavoritesService;
import com.example.mediamicroservice.service.impl.PostServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/favorites")
public class FavoritesController {

	@Autowired
	FavoritesService favoritesService;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PostServiceImpl postImpl;
	
	@PostMapping("/saveInFavorites/{username}/{post}")
	public ResponseEntity<CollectionFavourites> saveInFavorites(@PathVariable String username,@PathVariable Long post){
		Post post1=postRepository.getOne(post);
		CollectionFavourites collection=favoritesService.addPost(post1,username);
		
		
		return  collection == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<CollectionFavourites>(collection,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/findAllByProfile/{username}")
    public ResponseEntity<List<FavoritesDTO>> findAllByProfile(@PathVariable String username) {
		System.out.println("uslooo");
        List<CollectionFavourites> favorites=favoritesService.findAllByProfile(username);
        List<FavoritesDTO> favoritesDTO =new ArrayList<FavoritesDTO>();
        List<FavoritesDTO> newFavoritesDTO =new ArrayList<FavoritesDTO>();
        
        for(CollectionFavourites collection: favorites) {
        	FavoritesDTO dto=new FavoritesDTO();
        	dto.setIdFavourites(collection.getIdFavourites());
        	dto.setName(collection.getName());
        	dto.setProfile(collection.getProfile());
        	Set<Long> posts=new HashSet<Long>();
        	for(Post post:collection.getTags()) {
        	
        		posts.add(post.getIdPost());
        		
        	}
        	dto.setPosts(posts);
        		dto.setFileName("collection.png");
        	favoritesDTO.add(dto);
        }
        newFavoritesDTO=postImpl.getPicturesFiles(favoritesDTO);
        return newFavoritesDTO == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(newFavoritesDTO);
    }
}
