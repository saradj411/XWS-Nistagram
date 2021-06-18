package com.example.mediamicroservice.iservices;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Post;

public interface IPostService {
	Post save(PostDTO post);
}
