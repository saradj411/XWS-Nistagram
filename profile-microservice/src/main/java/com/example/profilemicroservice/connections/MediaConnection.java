package com.example.profilemicroservice.connections;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@FeignClient(name = "media-microservice")
public interface MediaConnection {
	
	@PostMapping("/user/save/{username}")
	public ResponseEntity add(@PathVariable String username);

}
