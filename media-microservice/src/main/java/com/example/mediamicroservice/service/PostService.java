package com.example.mediamicroservice.service;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;

public interface PostService {

	Post addNewPost(PostDTO postDTO);
}
