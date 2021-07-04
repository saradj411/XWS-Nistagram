package com.example.mediamicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.service.StoryService;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/story")
public class StoryController {
	
	@Autowired
	StoryService storyService;
	
	private static String uploadDir="user-photos";
	
	@PostMapping("/addNewStory/{username}")
	public ResponseEntity<Story> addNewStory(@RequestBody StoryDTO storyDTO,@PathVariable String username){
		
		Story response=storyService.addNewStory(storyDTO,username);
		
		return  response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Story>(response,HttpStatus.CREATED);
	}

}
