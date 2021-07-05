package com.example.mediamicroservice.service;

import com.example.mediamicroservice.dto.StoryDTO;
import com.example.mediamicroservice.model.Story;

public interface StoryService {

	Story addNewStory(StoryDTO postDTO,String username);
	void setVisibleStory();
}
