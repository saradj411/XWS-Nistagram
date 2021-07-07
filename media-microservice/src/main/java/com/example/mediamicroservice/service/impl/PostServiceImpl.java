package com.example.mediamicroservice.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.controller.PostController;
import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.dto.CommntsDTO;
import com.example.mediamicroservice.dto.FavoritesDTO;
import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.FrontPostDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.CommentRepository;
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
	
	@Autowired
	PostController post;
	
	@Autowired
	CommentRepository commentRepository;
	
	@Override
	public Post addNewPost(PostDTO postDTO,String username) {
		Post post=new Post();
		
		
		post.setDate(LocalDate.now());
		post.setDescription(postDTO.getDescription());
		post.setLocation(postDTO.getLocation());
		post.setNumberOfInappropriateVote(0);
		post.setNumberOfLikes(0);
		post.setNumberOfDislikes(0);
		post.setPostLimit(10);
		post.setProfile(profileRep.getOneByUsername(username));
		Post p=postRepository.save(post);
		
		for(String s:postDTO.getFileName()) {
			Media media=new Media();
			media.setFileName(s);
			
			media.setPost(p);
			Media m=mediaRepository.save(media);
		}
		
		//post.setMedia(medias);
		
		//Set<Media> medias=new HashSet<Media>();
		//medias.add(media);
		
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

	public List<FavoritesDTO> getPicturesFiles(List<FavoritesDTO> fronts) {
		List<FavoritesDTO> listaDTO=new ArrayList<FavoritesDTO>();
		if(fronts!=null) {
			String filePath=new File("").getAbsolutePath();
			filePath=filePath.concat("/"+"user-photos"+"/");
			for(FavoritesDTO m:fronts) {
				listaDTO.add(pictureFile(m, filePath));
			}
		}
		return listaDTO;
	}
	public FavoritesDTO pictureFile(FavoritesDTO front,String filePath) {
		FavoritesDTO frontDTO=front;
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
    public Comment commentPost(CommentDTO dto) {
    	System.out.println("Usao u metodu za komentarisanjneeeee ");
		 Comment newCom=new Comment();

    	 Profile profileMediaTo = profileRep.getOneByUsername(dto.getUsernameTo());//ko komentarise
     	System.out.println("ajdeeee "+dto.getUsernameFrom());

		 Profile profileMediaFrom =profileRep.getOneByUsername(dto.getUsernameFrom());//ko koment
		 List<FrontPostDTO> myPosts = post.findAllByIdUsernameee(dto.getUsernameTo());////getPostsByUsername
		 //List<Media> medias = new ArrayList<Media>();
		 //int updatedNumberOfComments = 0;
	     	System.out.println("pliz "+profileMediaFrom.getUsername());

		
			 Comment newComment=new Comment();
			 Post ppp=postRepository.findPostByIdPost(dto.getPostId());
			 
/*
			
				 List<Comment>allComments=commentRepository.findAll();
				 List<Comment>currentComments=new ArrayList<Comment>();
				 
				 for(Comment c:allComments) {
					 if(c.getProfile().getUsername().equals(dto.getUsernameTo())) {
						 currentComments.add(c);
					 }
					 
				 }
				 */
				 newComment.setDescription(dto.getComment());
				 
				 newComment.setPost(ppp);
				 newComment.setProfile(profileMediaFrom);
			     System.out.println("komentaaar jeee  "+newComment.getDescription());
				 newCom=commentRepository.save(newComment);
					 
				 
			 
		 return newCom;
		 
		}
    
    @Override
	public List<CommntsDTO> getComments(Long idPost) {
		List<CommntsDTO> listaDTO=new ArrayList<CommntsDTO>();
		List<Post>posts=postRepository.findAll();
		
		 List<Comment>allComments=commentRepository.findAll();
		 List<CommntsDTO>currentComments=new ArrayList<CommntsDTO>();
		 
		
		
		
		
			 for(Comment c:allComments) {
				if(c.getPost().getIdPost().equals(idPost)) {
			     	System.out.println("uuuuu "+c.getProfile().getUsername());

					
					CommntsDTO ccc=new CommntsDTO(c.getDescription(),c.getProfile().getUsername());
					currentComments.add(ccc);
				}
				 
			 }
			
			
			
		
		
		
		
		
		return currentComments;
	}
    
    @Override
    public Boolean viewPosts(String myUsrename,String usernameOfSearchAccount) {
    	
    	//treba mi taj profil i following od njega
    	Profile myProfile=profileRep.getOneByUsername(myUsrename);
    	System.out.println("moj username jee "+myProfile.getUsername());
		return null;
    	
    }
    


}


