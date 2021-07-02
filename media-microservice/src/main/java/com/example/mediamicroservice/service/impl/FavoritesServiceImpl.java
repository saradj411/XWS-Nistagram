package com.example.mediamicroservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.FavoritesRepository;
import com.example.mediamicroservice.service.FavoritesService;

@Service
public class FavoritesServiceImpl implements FavoritesService {

	@Autowired
	FavoritesRepository favoritesRepository;
	
	@Override
	public CollectionFavourites save(Profile profile) {
		CollectionFavourites collection=new CollectionFavourites();
		
		
		collection.setName("All");
		collection.setProfile(profile);
		
		
		
		CollectionFavourites p=favoritesRepository.save(collection);
		
		
		
		return p;
	}

	@Override
	public CollectionFavourites addPost(Post post,String username) {
		System.out.println(username);
		CollectionFavourites collection=favoritesRepository.findByNameAndUser("All",username);
		System.out.println("Kolekcija:"+collection.getIdFavourites());
		/*
		Set<Post> posts=collection.getTags();
		posts.add(post);
		collection.setTags(posts);
		
		CollectionFavourites p=favoritesRepository.save(collection);
		
		
		*/
		return null;
		
		
		
	}

}
