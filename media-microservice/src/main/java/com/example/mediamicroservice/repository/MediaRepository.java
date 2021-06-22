package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mediamicroservice.model.Media;



public interface MediaRepository extends JpaRepository<Media, Long>{
	
	@Query("select ex from Media ex where ex.post.idPost = ?1")
    List<Media> findAllMediaByPost(Long id);
}
