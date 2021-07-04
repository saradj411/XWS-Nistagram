package com.example.mediamicroservice.service;

import java.util.List;
import java.util.Set;

import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;

public interface FavoritesService {
	CollectionFavourites save(Profile profile);
	CollectionFavourites addNew(String username,String name);
	CollectionFavourites addPost(Post post,String username);
	CollectionFavourites addPostInOtherCollection(Post post,String username,String collectionName);

	 
	List<CollectionFavourites> findAllByProfile(String username);
	
	Set<Post> findAllPosts(Long idFavourites);
}
