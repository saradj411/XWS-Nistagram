package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.dto.CommntsDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.model.Post;

public interface PostService {

	Post addNewPost(PostDTO postDTO,String username);

	Comment commentPost(CommentDTO dto);

	List<CommntsDTO> getComments(Long idPost);

	Boolean viewPosts(String myUsrename, String usernameOfSearchAccount);
	
	
}
