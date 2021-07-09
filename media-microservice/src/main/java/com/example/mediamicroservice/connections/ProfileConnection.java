package com.example.mediamicroservice.connections;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mediamicroservice.dto.ProfileeDTO;


@FeignClient(name = "profile-microservice")
public interface ProfileConnection {
	
	@GetMapping("/api/profile/getAllPublicProfiles")
	ArrayList<ProfileeDTO> getAllPublicProfiles();
	
	
	@GetMapping("/api/profile/getAllFollowingProfile/{username}")
	ArrayList<ProfileeDTO> getAllFollowingProfile(@PathVariable String username);
	
	@GetMapping(value ="/api/profile/getAllCloseFriendsByUsername/{username}")
	Set<String> getAllCloseFriendsByUsername(@PathVariable String username);

}
