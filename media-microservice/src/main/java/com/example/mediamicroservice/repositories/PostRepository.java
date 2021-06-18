package com.example.mediamicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mediamicroservice.model.Post;

public interface PostRepository  extends JpaRepository<Post, Integer>  {

}
