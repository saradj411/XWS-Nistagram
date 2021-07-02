package com.example.mediamicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.mediamicroservice.model.CollectionFavourites;

public interface FavoritesRepository extends JpaRepository<CollectionFavourites, Long> {

	@Query("select ex from CollectionFavourites  ex where ex.name = ?1 and ex.profile.username=?2")
    CollectionFavourites findByNameAndUser(String name,String username);
}
