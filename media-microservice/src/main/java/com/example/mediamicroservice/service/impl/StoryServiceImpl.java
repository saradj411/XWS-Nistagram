package com.example.mediamicroservice.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Story;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.MediaRepository;
import com.example.mediamicroservice.repository.ProfileRepository;
import com.example.mediamicroservice.repository.StoryRepository;
import com.example.mediamicroservice.repository.TagRepository;
import com.example.mediamicroservice.service.StoryService;

@Service
public class StoryServiceImpl implements StoryService{
	
	@Autowired
	StoryRepository storyRepository;
	
	@Autowired
	ProfileRepository profileRep;
	
	@Autowired
	MediaRepository mediaRepository;
	
	@Autowired
	TagRepository tagRepo;
	
	@Override
	public Story addNewStory(StoryDTO storyDTO, String username) {
		Story story=new Story();
		
		story.setDate(LocalDate.now());
		story.setDescription(storyDTO.getDescription());
		story.setLocation(storyDTO.getLocation());
		story.setVisibleForCloseFriends(storyDTO.getVisibleForCloseFriends());
		story.setVisibleHighlights(storyDTO.getVisibleHighlights());
		story.setVisible24h(true);
		story.setProfile(profileRep.getOneByUsername(username));
		
		Media media=new Media();
		media.setFileName(storyDTO.getFileName());
		
		
		
		Story s=storyRepository.save(story);
		
		
		media.setStory(s);
		Media m=mediaRepository.save(media);
		
		
		List<String> lista=new ArrayList<>();
		lista=storyDTO.getTags();
		
		for(String tag:lista) {
			System.out.println(tag);
			Tag tg=new Tag();
			tg.setTagText(tag);
			tg.setStory(s);
			Tag newTag=tagRepo.save(tg);
			
			
		}
		
		return s;
	}

}
