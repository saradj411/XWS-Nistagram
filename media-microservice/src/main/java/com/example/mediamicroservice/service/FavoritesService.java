package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;

public interface FavoritesService {
	CollectionFavourites save(Profile profile);
	CollectionFavourites addNew(String username,String name);
	CollectionFavourites addPost(Post post,String username);
	 
	List<CollectionFavourites> findAllByProfile(String username);
}
