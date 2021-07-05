package com.example.profilemicroservice.connections;

import java.util.ArrayList;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import com.example.profilemicroservice.dto.ProfileDTO;
import com.example.profilemicroservice.dto.ProfileeDTO;
import com.example.profilemicroservice.model.Profile;


@FeignClient(name = "media-microservice")
public interface MediaConnection {
	
	@PostMapping("/user/save/{username}")
	public ResponseEntity add(@PathVariable String username);
	
	@PostMapping("/user/updateProfile")
    ResponseEntity<String> update(@RequestBody ProfileDTO profile);
	
	
}
