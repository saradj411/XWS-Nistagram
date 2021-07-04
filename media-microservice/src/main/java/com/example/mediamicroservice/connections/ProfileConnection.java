package com.example.mediamicroservice.connections;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.mediamicroservice.dto.ProfileeDTO;


@FeignClient(name = "profile-microservice")
public interface ProfileConnection {
	
	@GetMapping("/api/profile/getAllPublicProfiles")
	ArrayList<ProfileeDTO> getAllPublicProfiles();

}
