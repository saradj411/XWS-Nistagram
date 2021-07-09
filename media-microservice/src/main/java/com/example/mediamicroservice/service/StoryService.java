package com.example.mediamicroservice.service;

import java.util.List;

import com.example.mediamicroservice.dto.FrontStoryDTO;
import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Story;

public interface StoryService {

	Story addNewStory(StoryDTO postDTO,String username);
	void setVisibleStory();
	List<FrontStoryDTO> getMyHighlightStory(String username);
	List<FrontStoryDTO> getMyStories(String username);
	List<FrontStoryDTO> getOtherHighlightStory(String username);
}
