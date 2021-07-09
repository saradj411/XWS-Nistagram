package com.example.mediamicroservice.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.FrontStoryDTO;
import com.example.mediamicroservice.dto.FrontTagDTO;
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
		
		story.setDate(LocalDateTime.now());
		story.setDescription(storyDTO.getDescription());
		story.setLocation(storyDTO.getLocation());
		
		if(storyDTO.getVisibleForCloseFriends()==null) {
			story.setVisibleForCloseFriends(false);
		}else {
			story.setVisibleForCloseFriends(storyDTO.getVisibleForCloseFriends());
		}
		
		if(storyDTO.getVisibleHighlights()==null) {
			story.setVisibleHighlights(false);
		}else {
			story.setVisibleHighlights(storyDTO.getVisibleHighlights());
		}
		
		story.setVisible24h(true);
		story.setProfile(profileRep.getOneByUsername(username));
		
		Media media=new Media();
		media.setFileName(storyDTO.getFileName());
		Media m=mediaRepository.save(media);
		
		story.setMedia(m);
		Story s=storyRepository.save(story);
		
		
		
		
		
		
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
	
	public FrontMediaDTO getImagesFiles(FrontMediaDTO fronts) {
		FrontMediaDTO listaDTO=new FrontMediaDTO();
		if(fronts!=null) {
			String filePath=new File("").getAbsolutePath();
			filePath=filePath.concat("/"+"user-photos"+"/");
			
				listaDTO=imageFile(fronts, filePath);
		
		}
		return listaDTO;
	}
	public FrontMediaDTO imageFile(FrontMediaDTO front,String filePath) {
		FrontMediaDTO frontDTO=front;
		//List<byte[]> imageBytes=new ArrayList<byte[]>();
		//front.setImageByte(imageBytes);
		File in=new File(filePath+"/"+front.getFileName());
		try {
			frontDTO.setImageByte(IOUtils.toByteArray(new FileInputStream(in)));
		}catch(IOException e){
			e.printStackTrace();
			
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return frontDTO;
	}

	@Override
	public void setVisibleStory() {
		// TODO Auto-generated method stub
		List<Story> stories=storyRepository.findAll();
		
		for(Story s:stories) {
			LocalDateTime date=s.getDate();
			LocalDateTime plusTime=date.plusHours(24);
			LocalDateTime now=LocalDateTime.now();
			
			System.out.println("storuy:"+date);
			System.out.println("dodato:"+plusTime);
			System.out.println("sad:"+now);
			 if(plusTime.compareTo(now)<0){
				 System.out.println("uslo");
	             s.setVisible24h(false);
	                    
	            }
			 Story story=storyRepository.save(s);
		}
		
	}

	@Override
	public List<FrontStoryDTO> getMyHighlightStory(String username) {
		List<Story> stories=new ArrayList<Story>();
        List<Story> userStories=storyRepository.findAllStoryByUser(username);
        List<FrontStoryDTO> fronts=new ArrayList<FrontStoryDTO>();
        
        for(Story s:userStories) {
        		if(s.getVisibleHighlights()) {
        			stories.add(s);
            	}
        }
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Story p:stories) {
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
        		
        		 ee=getImagesFiles(ff);
        	
        	
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
        return fronts;
	}

	@Override
	public List<FrontStoryDTO> getMyStories(String username) {
		 	List<Story> stories=storyRepository.findAllStoryByUser(username);
	        List<FrontStoryDTO> fronts=new ArrayList<FrontStoryDTO>();
	        
	        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	    	
	        for(Story p:stories) {
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
	        		
	        		 ee=getImagesFiles(ff);
	        	
	        	
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
	        return fronts;
	}

	@Override
	public List<FrontStoryDTO> getOtherHighlightStory(String username) {
		List<Story> stories=new ArrayList<Story>();
        List<Story> userStories=storyRepository.findAllStoryByUser(username);
        List<FrontStoryDTO> fronts=new ArrayList<FrontStoryDTO>();
        
        for(Story s:userStories) {
        		if(s.getVisibleHighlights()) {
        			stories.add(s);
            	}
        }
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Story p:stories) {
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
        		
        		 ee=getImagesFiles(ff);
        	
        	
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
        return fronts;
	}

}
