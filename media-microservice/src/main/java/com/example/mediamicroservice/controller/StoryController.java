package com.example.mediamicroservice.controller;

import java.util.ArrayList;
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

import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.FrontPostDTO;
import com.example.mediamicroservice.dto.FrontStoryDTO;
import com.example.mediamicroservice.dto.FrontTagDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.model.Tag;
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
	
	private static String uploadDir="user-photos";
	
	@PostMapping("/addNewStory/{username}")
	public ResponseEntity<Story> addNewStory(@RequestBody StoryDTO storyDTO,@PathVariable String username){
		
		Story response=storyService.addNewStory(storyDTO,username);
		
		return  response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Story>(response,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getStoryByUsername/{username}")
    public ResponseEntity<List<FrontStoryDTO>> findAllByUsername(@PathVariable String username) {
		System.out.println("uslooo");
        List<Story> drugs=storyRepository.findAllStoryByUser(username);
        List<FrontStoryDTO> fronts=new ArrayList<FrontStoryDTO>();
        
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Story p:drugs) {
        	FrontStoryDTO front=new FrontStoryDTO();
        	front.setDate(p.getDate());
        	front.setDescription(p.getDescription());
        	front.setIdStory(p.getIdStory());
        	front.setLocation(p.getLocation());
        	front.setUsername(p.getProfile().getUsername());
        	
        	
        	FrontMediaDTO ee=new FrontMediaDTO();
        	Media a=p.getMedia();
        		FrontMediaDTO ff=new FrontMediaDTO();
        		
        		ff.setFileName(a.getFileName());
        		ff.setId(a.getId());
        		ff.setIdPost(a.getStory().getIdStory());
        		
        		 ee=storyImpl.getImagesFiles(ff);
        	
        	
        	front.setMedia(ff);
        	
        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
        	for(Tag t:p.getTags()) {
        		FrontTagDTO tt=new FrontTagDTO();
        		tt.setIdPost(t.getStory().getIdStory());
        		tt.setTagText(t.getTagText());
        		lista1.add(tt);
        	}
        	front.setTags(lista1);
        	
        	fronts.add(front);
        	
        }
       
        
        return fronts == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(fronts);
    }

	@GetMapping(value = "/getHighlightStoryByUsername/{username}")
    public ResponseEntity<List<FrontStoryDTO>> getHighlightStoryByUsername(@PathVariable String username) {
		System.out.println("uslooo");
		List<Story> drugs=new ArrayList<Story>();
        List<Story> drugs1=storyRepository.findAllStoryByUser(username);
        List<FrontStoryDTO> fronts=new ArrayList<FrontStoryDTO>();
        
        for(Story s:drugs1) {
        	if(s.getVisibleHighlights()!=null) {
        		
        		if(s.getVisibleHighlights()) {
            		
            		drugs.add(s);
            	}
        	}
        }
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Story p:drugs) {
        	FrontStoryDTO front=new FrontStoryDTO();
        	front.setDate(p.getDate());
        	front.setDescription(p.getDescription());
        	front.setIdStory(p.getIdStory());
        	front.setLocation(p.getLocation());
        	front.setUsername(p.getProfile().getUsername());
        	
        	
        	FrontMediaDTO ee=new FrontMediaDTO();
        	Media a=p.getMedia();
        		FrontMediaDTO ff=new FrontMediaDTO();
        		
        		ff.setFileName(a.getFileName());
        		ff.setId(a.getId());
        		ff.setIdPost(a.getStory().getIdStory());
        		
        		 ee=storyImpl.getImagesFiles(ff);
        	
        	
        	front.setMedia(ff);
        	
        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
        	for(Tag t:p.getTags()) {
        		FrontTagDTO tt=new FrontTagDTO();
        		tt.setIdPost(t.getStory().getIdStory());
        		tt.setTagText(t.getTagText());
        		lista1.add(tt);
        	}
        	front.setTags(lista1);
        	
        	fronts.add(front);
        	
        }
       
        
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
