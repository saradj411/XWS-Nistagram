package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mediamicroservice.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	@Query("select ex from Post  ex where ex.profile.username = ?1")
    List<Post> findAllPostByUser(String username);
	
	
	@Query("select ex from Post  ex where ex.idPost = ?1")
    Post findPostByIdPost(Long id);
}
