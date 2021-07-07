package com.example.mediamicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.CollectionFavourites;
import com.example.mediamicroservice.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
	

}
