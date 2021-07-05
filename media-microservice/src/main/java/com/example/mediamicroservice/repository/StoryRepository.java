package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mediamicroservice.model.Story;

public interface StoryRepository extends JpaRepository<Story, Long>{

	@Query("select ex from Story ex where ex.profile.username = ?1")
    List<Story> findAllStoryByUser(String username);
}
