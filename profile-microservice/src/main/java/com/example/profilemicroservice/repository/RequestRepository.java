package com.example.profilemicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.profilemicroservice.model.Request;

public interface RequestRepository extends JpaRepository<Request, Long> {
	


    @Query("select s from Request s where s.username= ?1")
    Request findByUsername(String username);


}
