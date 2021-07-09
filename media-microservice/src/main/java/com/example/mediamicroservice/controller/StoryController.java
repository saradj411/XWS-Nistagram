package com.example.mediamicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.FrontStoryDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.repository.StoryRepository;
import com.example.mediamicroservice.service.StoryService;
import com.example.mediamicroservice.service.impl.StoryServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/story")
public class StoryController {
	
	@Autowired
	StoryService storyService;
	
	@Autowired
	StoryRepository storyRepository;
	
	@Autowired
	StoryServiceImpl storyImpl;
	
	//private static String uploadDir="user-photos";
	
	@PostMapping("/addNewStory/{username}")
	public ResponseEntity<Story> addNewStory(@RequestBody StoryDTO storyDTO,@PathVariable String username){
		
		Story response=storyService.addNewStory(storyDTO,username);
		
		return  response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Story>(response,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getMyStories/{username}")
    public ResponseEntity<List<FrontStoryDTO>> getMyStories(@PathVariable String username) {
		System.out.println("uslooo");
		List<FrontStoryDTO> fronts=storyService.getMyStories(username);
       
        return fronts == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(fronts);
    }

	@GetMapping(value = "/getMyHighlightStory/{username}")
    public ResponseEntity<List<FrontStoryDTO>> getMyHighlightStory(@PathVariable String username) {
		System.out.println("uslooo");
		List<FrontStoryDTO> fronts=storyService.getMyHighlightStory(username);
       
        return fronts == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(fronts);
    }
	
	@GetMapping(value = "/getOtherHighlightStory/{username}/{myUsername}")
    public ResponseEntity<List<FrontStoryDTO>> getOtherHighlightStory(@PathVariable String username,@PathVariable String myUsername) {
		System.out.println("uslooo");
		List<FrontStoryDTO> fronts=storyService.getOtherHighlightStory(username);
       
        return fronts == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(fronts);
    }
	
	@PostMapping("/setVisibleStory")
	public ResponseEntity<Boolean> setVisibleStory(){
		
		storyService.setVisibleStory();
		
		return  new ResponseEntity<Boolean>(true,HttpStatus.ACCEPTED);
	}
	
}
