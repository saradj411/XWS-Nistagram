package com.example.profilemicroservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.model.Request;


public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
    List<Profile> findAll();
    Profile findOneByUsername(String username);


}
