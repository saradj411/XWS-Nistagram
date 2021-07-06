package com.example.mediamicroservice.service;

import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.model.Post;

public interface PostService {

	Post addNewPost(PostDTO postDTO,String username);

	Comment commentPost(CommentDTO dto);
	
	
}
