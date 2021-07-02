package com.example.mediamicroservice.service;

import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;

public interface FavoritesService {
	CollectionFavourites save(Profile profile);
	CollectionFavourites addPost(Post post,String username);
}
