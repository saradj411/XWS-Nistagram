package com.example.mediamicroservice.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.commons.util.IdUtils;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.MediaRepository;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.repository.ProfileRepository;
import com.example.mediamicroservice.repository.TagRepository;
import com.example.mediamicroservice.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ProfileRepository profileRep;
	
	@Autowired
	MediaRepository mediaRepository;
	
	@Autowired
	TagRepository tagRepo;
	
	@Override
	public Post addNewPost(PostDTO postDTO) {
		Post post=new Post();
		
		
		post.setDate(LocalDate.now());
		post.setDescription(postDTO.getDescription());
		post.setLocation(postDTO.getLocation());
		post.setNumberOfInappropriateVote(0);
		post.setNumberOfLikes(0);
		post.setNumberOfDislikes(0);
		post.setPostLimit(10);
		post.setProfile(profileRep.getOneByUsername("saki"));
		
		Media media=new Media();
		media.setFileName(postDTO.getFileName());
		
		
		//post.setMedia(medias);
		
		
		//Set<Media> medias=new HashSet<Media>();
		//medias.add(media);
		
		Post p=postRepository.save(post);
		
		
		media.setPost(p);
		Media m=mediaRepository.save(media);
		
		
		//Set<Tag> profiles=new HashSet<Tag>();
		
		List<String> lista=new ArrayList<>();
		lista=postDTO.getTags();
		
		for(String tag:lista) {
			System.out.println(tag);
			Tag tg=new Tag();
			tg.setTagText(tag);
			tg.setPost(p);
			Tag newTag=tagRepo.save(tg);
				//profiles.add(tg);
			
			
		}
		
		return p;
	}
	
	public List<FrontMediaDTO> getImagesFiles(List<FrontMediaDTO> fronts) {
		List<FrontMediaDTO> listaDTO=new ArrayList<FrontMediaDTO>();
		if(fronts!=null) {
			String filePath=new File("").getAbsolutePath();
			filePath=filePath.concat("/"+"user-photos"+"/");
			for(FrontMediaDTO m:fronts) {
				listaDTO.add(imageFile(m, filePath));
			}
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

}
