package com.example.mediamicroservice.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.mediamicroservice.connections.ProfileConnection;
import com.example.mediamicroservice.dto.CommentDTO;
import com.example.mediamicroservice.dto.CommntsDTO;
import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.FrontPostDTO;
import com.example.mediamicroservice.dto.FrontTagDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.dto.ProfileeDTO;
import com.example.mediamicroservice.model.Comment;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.model.Tag;
import com.example.mediamicroservice.repository.PostRepository;
import com.example.mediamicroservice.repository.ProfileRepository;
import com.example.mediamicroservice.service.FavoritesService;
import com.example.mediamicroservice.service.PostService;
import com.example.mediamicroservice.service.impl.MediaUpload;
import com.example.mediamicroservice.service.impl.PostServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/post")
public class PostController {

	
	@Autowired
	PostService postService;
	
	@Autowired
	PostServiceImpl postImpl;
	
	@Autowired
	PostRepository postRepository;
	
	@Autowired
	ProfileRepository profileRepo;
	
	@Autowired
	ProfileConnection profileConnection;


	@Autowired
	FavoritesService favoritesService;

	
	private static String uploadDir="user-photos";
	
	
	@PostMapping("/saveImage")
	public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException{
		
		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));
		uploadDir="user-photos";
		MediaUpload.saveFile(uploadDir, fileName, multipartFile);
		System.out.println("slikaa:0"+fileName);
		return fileName;
	}
	@PostMapping("/addNewPost/{username}")
	public ResponseEntity<Post> addNewPost(@RequestBody PostDTO postDTO,@PathVariable String username){
		
		Post response=postService.addNewPost(postDTO,username);
		
		return  response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Post>(response,HttpStatus.CREATED);
	}
	
	@PostMapping("/like/{username}/{post}")
	public ResponseEntity<Integer> like(@PathVariable String username,@PathVariable Long post){
		Post p=postRepository.getOne(post);
		Set<Profile> oldLikes=p.getLike();
		Set<Profile> oldDisikes=p.getDislike();
		
		Boolean res=false;
		for(Profile profile:oldLikes) {
			if(username.equals(profile.getUsername())) {
				res=true;
			}
		}
		Integer integer=null;
		if(!res) {
			oldLikes.add(profileRepo.getOneByUsername(username));
			p.setLike(oldLikes);
			p.setNumberOfLikes(p.getNumberOfLikes()+1);
			Boolean jel=oldDisikes.remove(profileRepo.getOneByUsername(username));
			System.out.println("obrisa li dislike?????"+jel);
			if(jel) {
				p.setDislike(oldDisikes);
				p.setNumberOfDislikes(p.getNumberOfDislikes()-1);
			}
			
			Post response=postRepository.save(p);
			integer=response.getNumberOfLikes();
		}
		
		
		
		return  integer == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Integer>(integer,HttpStatus.CREATED);
	}
	
	@PostMapping("/dislike/{username}/{post}")
	public ResponseEntity<Integer> dislike(@PathVariable String username,@PathVariable Long post){
		Post p=postRepository.getOne(post);
		Set<Profile> oldDisikes=p.getDislike();
		Set<Profile> oldLikes=p.getLike();
		
		Boolean res=false;
		for(Profile profile:oldDisikes) {
			if(username.equals(profile.getUsername())) {
				res=true;
			}
		}
		Integer integer=null;
		if(!res) {
			oldDisikes.add(profileRepo.getOneByUsername(username));
			p.setDislike(oldDisikes);
			p.setNumberOfDislikes(p.getNumberOfDislikes()+1);
			Boolean jel=oldLikes.remove(profileRepo.getOneByUsername(username));
			System.out.println("obrisa li?????"+jel);
			if(jel) {
				p.setLike(oldLikes);
				p.setNumberOfLikes(p.getNumberOfLikes()-1);
			}
			
			
			Post response=postRepository.save(p);
			integer=response.getNumberOfDislikes();
		}
		return  integer == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Integer>(integer,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getPostByUsername/{username}")
    public ResponseEntity<List<FrontPostDTO>> findAllByIdUsername(@PathVariable String username) {
		System.out.println("uslooo");
        List<Post> drugs=postRepository.findAllPostByUser(username);
        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
        
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Post p:drugs) {
        	FrontPostDTO front=new FrontPostDTO();
        	front.setDate(p.getDate());
        	front.setDescription(p.getDescription());
        	front.setIdPost(p.getIdPost());
        	front.setLocation(p.getLocation());
        	front.setUsername(p.getProfile().getUsername());
        	front.setNumberOfDislikes(p.getNumberOfDislikes());
        	front.setNumberOfLikes(p.getNumberOfLikes());
        	
        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
        	for(Media a:p.getMedia()) {
        		FrontMediaDTO ff=new FrontMediaDTO();
        		
        		ff.setFileName(a.getFileName());
        		ff.setId(a.getId());
        		ff.setIdPost(a.getPost().getIdPost());
        		lista.add(ff);
        		 ee=postImpl.getImagesFiles(lista);
        	}
        	
        	front.setMedia(ee);
        	
        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
        	for(Tag t:p.getTags()) {
        		FrontTagDTO tt=new FrontTagDTO();
        		tt.setIdPost(t.getPost().getIdPost());
        		tt.setTagText(t.getTagText());
        		lista1.add(tt);
        	}
        	front.setTags(lista1);
        	
        	fronts.add(front);
        	
        	/*Set<Media> medias=p.getMedia();
        	for(Media m:medias) {
        	System.out.println("nestoo:0"+m.getFileName());
        	}*/
        }
        //List<FrontMediaDTO> ee=postImpl.getImagesFiles(lista);
        return fronts == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(fronts);
    }
	

	
	@GetMapping(value = "/getPostByUsernameeeeee/{username}")
    public List<FrontPostDTO> findAllByIdUsernameee(@PathVariable String username) {
		System.out.println("uslooo");
        List<Post> drugs=postRepository.findAllPostByUser(username);
        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
        
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Post p:drugs) {
        	FrontPostDTO front=new FrontPostDTO();
        	front.setDate(p.getDate());
        	front.setDescription(p.getDescription());
        	front.setIdPost(p.getIdPost());
        	front.setLocation(p.getLocation());
        	front.setUsername(p.getProfile().getUsername());
        	front.setNumberOfDislikes(p.getNumberOfDislikes());
        	front.setNumberOfLikes(p.getNumberOfLikes());
        	
        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
        	for(Media a:p.getMedia()) {
        		FrontMediaDTO ff=new FrontMediaDTO();
        		
        		ff.setFileName(a.getFileName());
        		ff.setId(a.getId());
        		ff.setIdPost(a.getPost().getIdPost());
        		lista.add(ff);
        		 ee=postImpl.getImagesFiles(lista);
        	}
        	
        	front.setMedia(ee);
        	
        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
        	for(Tag t:p.getTags()) {
        		FrontTagDTO tt=new FrontTagDTO();
        		tt.setIdPost(t.getPost().getIdPost());
        		tt.setTagText(t.getTagText());
        		lista1.add(tt);
        	}
        	front.setTags(lista1);
        	
        	fronts.add(front);
        	
        	/*Set<Media> medias=p.getMedia();
        	for(Media m:medias) {
        	System.out.println("nestoo:0"+m.getFileName());
        	}*/
        }
        //List<FrontMediaDTO> ee=postImpl.getImagesFiles(lista);
        return fronts;
	}
	
	
	

	@GetMapping(value = "/getPostByCollection/{id}")
    public ResponseEntity<List<FrontPostDTO>> getPostByCollection(@PathVariable Long id) {
		System.out.println("uslooo");
		Set<Post> posts=favoritesService.findAllPosts(id);
        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
        
        //List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
    	
        for(Post p:posts) {

        	FrontPostDTO front=new FrontPostDTO();
        	front.setDate(p.getDate());
        	front.setDescription(p.getDescription());
        	front.setIdPost(p.getIdPost());
        	front.setLocation(p.getLocation());
        	front.setUsername(p.getProfile().getUsername());
        	front.setNumberOfDislikes(p.getNumberOfDislikes());
        	front.setNumberOfLikes(p.getNumberOfLikes());
        	
        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
        	for(Media a:p.getMedia()) {
        		FrontMediaDTO ff=new FrontMediaDTO();
        		
        		ff.setFileName(a.getFileName());
        		ff.setId(a.getId());
        		ff.setIdPost(a.getPost().getIdPost());
        		lista.add(ff);
        		 ee=postImpl.getImagesFiles(lista);
        	}
        	
        	front.setMedia(ee);
        	
        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
        	for(Tag t:p.getTags()) {
        		FrontTagDTO tt=new FrontTagDTO();
        		tt.setIdPost(t.getPost().getIdPost());
        		tt.setTagText(t.getTagText());
        		lista1.add(tt);
        	}
        	front.setTags(lista1);
        	
        	fronts.add(front);
        	
        	/*Set<Media> medias=p.getMedia();
        	for(Media m:medias) {
        	System.out.println("nestoo:0"+m.getFileName());
        	}*/
        }
        //List<FrontMediaDTO> ee=postImpl.getImagesFiles(lista);
        return fronts == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(fronts);
    }

	
	//naseeeeeeeeee
	@GetMapping(value = "/getPostPublic")
    public ResponseEntity<List<FrontPostDTO>> findAllPublic() {
		System.out.println("javni profili nasee");
        //List<Post> drugs=postRepository.findAll();
        ArrayList<ProfileeDTO> list=profileConnection.getAllPublicProfiles();
        List<Post> allPosts=new ArrayList<Post>();
        for(ProfileeDTO pp:list) {
        	System.out.println("Lista profilaaa "+pp.getUsername());
            List<Post>posts= postRepository.findAllPostByUser(pp.getUsername());
            for(Post p1: posts) {
            	System.out.println("opisssss "+p1.getDescription());

            	allPosts.add(p1);
            }
        }
        
        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
        
        for(Post p:allPosts) {
        	System.out.println("opisssss for  "+p.getDescription());

        	FrontPostDTO front=new FrontPostDTO();
        	front.setDate(p.getDate());
        	front.setDescription(p.getDescription());
        	front.setIdPost(p.getIdPost());
        	front.setLocation(p.getLocation());
        	front.setUsername(p.getProfile().getUsername());
        	front.setNumberOfDislikes(p.getNumberOfDislikes());
        	front.setNumberOfLikes(p.getNumberOfLikes());
        	
        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
        	for(Media a:p.getMedia()) {
        		FrontMediaDTO ff=new FrontMediaDTO();
        		
        		ff.setFileName(a.getFileName());
        		ff.setId(a.getId());
        		ff.setIdPost(a.getPost().getIdPost());
        		lista.add(ff);
        		 ee=postImpl.getImagesFiles(lista);
        	}
        	
        	front.setMedia(ee);
        	
        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
        	for(Tag t:p.getTags()) {
        		FrontTagDTO tt=new FrontTagDTO();
        		tt.setIdPost(t.getPost().getIdPost());
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
	
	
	
	//lajkovani postovi
		@GetMapping(value = "/getLikedPost/{username}")
	    public ResponseEntity<List<FrontPostDTO>> getLikedPost(@PathVariable String username) {
			System.out.println("lajkovani postovi");
	        List<Post> allPosts=new ArrayList<Post>();

	        List<Post> drugs=postRepository.findAll();
	        for(Post p:drugs) {
	        	for (Profile profile:p.getLike()) {
	        		if(profile.getUsername().equals(username)) {
	        			allPosts.add(p);
	        		}
	        	}
	        }
	    
	        
	        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
	        
	        for(Post p:allPosts) {
	        	System.out.println("opisssss for  "+p.getDescription());

	        	FrontPostDTO front=new FrontPostDTO();
	        	front.setDate(p.getDate());
	        	front.setDescription(p.getDescription());
	        	front.setIdPost(p.getIdPost());
	        	front.setLocation(p.getLocation());
	        	front.setUsername(p.getProfile().getUsername());
	        	front.setNumberOfDislikes(p.getNumberOfDislikes());
	        	front.setNumberOfLikes(p.getNumberOfLikes());
	        	
	        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
	        	for(Media a:p.getMedia()) {
	        		FrontMediaDTO ff=new FrontMediaDTO();
	        		
	        		ff.setFileName(a.getFileName());
	        		ff.setId(a.getId());
	        		ff.setIdPost(a.getPost().getIdPost());
	        		lista.add(ff);
	        		 ee=postImpl.getImagesFiles(lista);
	        	}
	        	
	        	front.setMedia(ee);
	        	
	        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
	        	for(Tag t:p.getTags()) {
	        		FrontTagDTO tt=new FrontTagDTO();
	        		tt.setIdPost(t.getPost().getIdPost());
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
		
		
		//dislajkovani postoviii
		@GetMapping(value = "/getDislikedPost/{username}")
	    public ResponseEntity<List<FrontPostDTO>> getDislikedPost(@PathVariable String username) {
			System.out.println("lajkovani postovi");
	        List<Post> allPosts=new ArrayList<Post>();

	        List<Post> drugs=postRepository.findAll();
	        for(Post p:drugs) {
	        	for (Profile profile:p.getDislike()) {
	        		if(profile.getUsername().equals(username)) {
	        			allPosts.add(p);
	        		}
	        	}
	        }
	    
	        
	        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
	        
	        for(Post p:allPosts) {
	        	System.out.println("opisssss for  "+p.getDescription());

	        	FrontPostDTO front=new FrontPostDTO();
	        	front.setDate(p.getDate());
	        	front.setDescription(p.getDescription());
	        	front.setIdPost(p.getIdPost());
	        	front.setLocation(p.getLocation());
	        	front.setUsername(p.getProfile().getUsername());
	        	front.setNumberOfDislikes(p.getNumberOfDislikes());
	        	front.setNumberOfLikes(p.getNumberOfLikes());
	        	
	        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
	        	for(Media a:p.getMedia()) {
	        		FrontMediaDTO ff=new FrontMediaDTO();
	        		
	        		ff.setFileName(a.getFileName());
	        		ff.setId(a.getId());
	        		ff.setIdPost(a.getPost().getIdPost());
	        		lista.add(ff);
	        		 ee=postImpl.getImagesFiles(lista);
	        	}
	        	
	        	front.setMedia(ee);
	        	
	        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
	        	for(Tag t:p.getTags()) {
	        		FrontTagDTO tt=new FrontTagDTO();
	        		tt.setIdPost(t.getPost().getIdPost());
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
	
	
		//postovi  profila koje ja pratim
		@GetMapping(value = "/getPostOfFollowing/{username}")
	    public ResponseEntity<List<FrontPostDTO>> getPostOfFollowing(@PathVariable String username) {
			System.out.println("lajkovani postovi");
	        List<Post> allPosts=new ArrayList<Post>();

	        ArrayList<ProfileeDTO>profiles= profileConnection.getAllFollowingProfile(username);
	      //dobijamo username od profila koje ja pratim
	        List<Post> drugs=postRepository.findAll();
	   
	        for(ProfileeDTO profile:profiles) {
	        	for(Post post:drugs) {
	        		if(post.getProfile().getUsername().equals(profile.getUsername())) {
	        			allPosts.add(post);
	        			
	        		}
	        	}
	        }
	      
	    
	        
	        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
	        
	        for(Post p:allPosts) {
	        	System.out.println("opisssss for  "+p.getDescription());

	        	FrontPostDTO front=new FrontPostDTO();
	        	front.setDate(p.getDate());
	        	front.setDescription(p.getDescription());
	        	front.setIdPost(p.getIdPost());
	        	front.setLocation(p.getLocation());
	        	front.setUsername(p.getProfile().getUsername());
	        	front.setNumberOfDislikes(p.getNumberOfDislikes());
	        	front.setNumberOfLikes(p.getNumberOfLikes());
	        	
	        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
	        	for(Media a:p.getMedia()) {
	        		FrontMediaDTO ff=new FrontMediaDTO();
	        		
	        		ff.setFileName(a.getFileName());
	        		ff.setId(a.getId());
	        		ff.setIdPost(a.getPost().getIdPost());
	        		lista.add(ff);
	        		 ee=postImpl.getImagesFiles(lista);
	        	}
	        	
	        	front.setMedia(ee);
	        	
	        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
	        	for(Tag t:p.getTags()) {
	        		FrontTagDTO tt=new FrontTagDTO();
	        		tt.setIdPost(t.getPost().getIdPost());
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
		
		
		@PostMapping("/report/{post}")
		public ResponseEntity<Integer> report(@PathVariable Long post){
			Post p=postRepository.getOne(post);
			p.setNumberOfInappropriateVote(p.getNumberOfInappropriateVote()+1);
			
			
			Post response=postRepository.save(p);
			Integer integer=response.getNumberOfInappropriateVote();
			
			return  integer == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
	            new ResponseEntity<Integer>(integer,HttpStatus.CREATED);
		}
		
		
		
		//prikaz postova prijavljenih profila administratoru
		@GetMapping(value = "/searchPublicProfile/{username}")
	    public ResponseEntity<List<FrontPostDTO>> searchPublicProfile(@PathVariable String username) {
			System.out.println("pretaga javnih profila");
	        List<Post> allPosts=new ArrayList<Post>();

	       // List<Post>profiles=postRepository.findAll();
	      //dobijamo username od profila koje ja pratim
	        List<Post> drugs=postRepository.findAll();
	        ArrayList<ProfileeDTO> list=profileConnection.getAllPublicProfiles();

	        
	   
	        
	        	for(Post post:drugs) {
	        		System.out.println("id posta jeee 111111111"+post.getIdPost());
	        		for(ProfileeDTO p:list) {
	        			if(p.getUsername().contains(username)&&post.getProfile().getUsername().contains(username)) {
	        				allPosts.add(post);
	        			}
	        		}

	        	
	        	
	        }
	      
	    
	        
	        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
	        
	        for(Post p:allPosts) {
	        	System.out.println("opisssss for  "+p.getDescription());

	        	FrontPostDTO front=new FrontPostDTO();
	        	front.setDate(p.getDate());
	        	front.setDescription(p.getDescription());
	        	front.setIdPost(p.getIdPost());
	        	front.setLocation(p.getLocation());
	        	front.setUsername(p.getProfile().getUsername());
	        	front.setNumberOfDislikes(p.getNumberOfDislikes());
	        	front.setNumberOfLikes(p.getNumberOfLikes());
	        	front.setNumberOfInappropriateVote(p.getNumberOfInappropriateVote());
	        	
	        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
	        	for(Media a:p.getMedia()) {
	        		FrontMediaDTO ff=new FrontMediaDTO();
	        		
	        		ff.setFileName(a.getFileName());
	        		ff.setId(a.getId());
	        		ff.setIdPost(a.getPost().getIdPost());
	        		lista.add(ff);
	        		 ee=postImpl.getImagesFiles(lista);
	        	}
	        	
	        	front.setMedia(ee);
	        	
	        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
	        	for(Tag t:p.getTags()) {
	        		FrontTagDTO tt=new FrontTagDTO();
	        		tt.setIdPost(t.getPost().getIdPost());
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
		
		//pretaga javnih profila
		
		@GetMapping(value = "/getInappropriatePost")
	    public ResponseEntity<List<FrontPostDTO>> getInappropriatePost() {
			System.out.println("neprikladni sadrzaj");
	        List<Post> allPosts=new ArrayList<Post>();

	        List<Post>profiles=postRepository.findAll();
	      //dobijamo username od profila koje ja pratim
	        List<Post> drugs=postRepository.findAll();
	        
	   
	        
	        	for(Post post:drugs) {
	        		if(post.getNumberOfInappropriateVote()!=0) {
	        			allPosts.add(post);
	        			
	        		}
	        	
	        }
	      
	    
	        
	        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
	        
	        for(Post p:allPosts) {
	        	System.out.println("opisssss for  "+p.getDescription());

	        	FrontPostDTO front=new FrontPostDTO();
	        	front.setDate(p.getDate());
	        	front.setDescription(p.getDescription());
	        	front.setIdPost(p.getIdPost());
	        	front.setLocation(p.getLocation());
	        	front.setUsername(p.getProfile().getUsername());
	        	front.setNumberOfDislikes(p.getNumberOfDislikes());
	        	front.setNumberOfLikes(p.getNumberOfLikes());
	        	front.setNumberOfInappropriateVote(p.getNumberOfInappropriateVote());
	        	
	        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
	        	for(Media a:p.getMedia()) {
	        		FrontMediaDTO ff=new FrontMediaDTO();
	        		
	        		ff.setFileName(a.getFileName());
	        		ff.setId(a.getId());
	        		ff.setIdPost(a.getPost().getIdPost());
	        		lista.add(ff);
	        		 ee=postImpl.getImagesFiles(lista);
	        	}
	        	
	        	front.setMedia(ee);
	        	
	        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
	        	for(Tag t:p.getTags()) {
	        		FrontTagDTO tt=new FrontTagDTO();
	        		tt.setIdPost(t.getPost().getIdPost());
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
		
		
		
		//pretraga tagova javnih profila
		@GetMapping(value = "/searchPublicProfileByTags/{tagText}")
	    public ResponseEntity<List<FrontPostDTO>> searchPublicProfileByTags(@PathVariable String tagText) {
			System.out.println("pretaga javnih profila pomocu tagova");
	        List<Post> allPosts=new ArrayList<Post>();

	       // List<Post>profiles=postRepository.findAll();
	      //dobijamo username od profila koje ja pratim
	        List<Post> drugs=postRepository.findAll();
	        ArrayList<ProfileeDTO> list=profileConnection.getAllPublicProfiles();

	        
	   
	        
	        	for(Post post:drugs) {
	        		System.out.println("id posta jeee 111111111"+post.getIdPost());
	        		for(ProfileeDTO p:list) {
	        			if(post.getProfile().getUsername().equals(p.getUsername())) {
	        				Set<Tag> tags =post.getTags();
		        			for(Tag t:tags) {
		        				if(t.getTagText().contains(tagText)) {
		        					allPosts.add(post);
		        				}
		        			}
	        			}
	        		
	        			
	        		}

	        	
	        	
	        }
	      
	    
	        
	        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
	        
	        for(Post p:allPosts) {
	        	System.out.println("opisssss for  "+p.getDescription());

	        	FrontPostDTO front=new FrontPostDTO();
	        	front.setDate(p.getDate());
	        	front.setDescription(p.getDescription());
	        	front.setIdPost(p.getIdPost());
	        	front.setLocation(p.getLocation());
	        	front.setUsername(p.getProfile().getUsername());
	        	front.setNumberOfDislikes(p.getNumberOfDislikes());
	        	front.setNumberOfLikes(p.getNumberOfLikes());
	        	front.setNumberOfInappropriateVote(p.getNumberOfInappropriateVote());
	        	
	        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
	        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
	        	for(Media a:p.getMedia()) {
	        		FrontMediaDTO ff=new FrontMediaDTO();
	        		
	        		ff.setFileName(a.getFileName());
	        		ff.setId(a.getId());
	        		ff.setIdPost(a.getPost().getIdPost());
	        		lista.add(ff);
	        		 ee=postImpl.getImagesFiles(lista);
	        	}
	        	
	        	front.setMedia(ee);
	        	
	        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
	        	for(Tag t:p.getTags()) {
	        		FrontTagDTO tt=new FrontTagDTO();
	        		tt.setIdPost(t.getPost().getIdPost());
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
		
		
		//pretraga lokacija javnih profila
				@GetMapping(value = "/searchPublicProfileByLocation/{location}")
			    public ResponseEntity<List<FrontPostDTO>> searchPublicProfileByLocation(@PathVariable String location) {
					System.out.println("pretaga javnih profila pomocu tagova");
			        List<Post> allPosts=new ArrayList<Post>();

			       // List<Post>profiles=postRepository.findAll();
			      //dobijamo username od profila koje ja pratim
			        List<Post> drugs=postRepository.findAll();
			        ArrayList<ProfileeDTO> list=profileConnection.getAllPublicProfiles();

			        
			   
			        
			        	for(Post post:drugs) {
			        		System.out.println("id posta jeee 111111111"+post.getIdPost());
			        		for(ProfileeDTO p:list) {
			        			if(post.getProfile().getUsername().equals(p.getUsername())) {
			        				
				        			
				        				if(post.getLocation().contains(location)) {
				        					allPosts.add(post);
				        				}
				        			
			        			}
			        		
			        			
			        		}

			        	
			        	
			        }
			      
			    
			        
			        List<FrontPostDTO> fronts=new ArrayList<FrontPostDTO>();
			        
			        for(Post p:allPosts) {
			        	System.out.println("opisssss for  "+p.getDescription());

			        	FrontPostDTO front=new FrontPostDTO();
			        	front.setDate(p.getDate());
			        	front.setDescription(p.getDescription());
			        	front.setIdPost(p.getIdPost());
			        	front.setLocation(p.getLocation());
			        	front.setUsername(p.getProfile().getUsername());
			        	front.setNumberOfDislikes(p.getNumberOfDislikes());
			        	front.setNumberOfLikes(p.getNumberOfLikes());
			        	front.setNumberOfInappropriateVote(p.getNumberOfInappropriateVote());
			        	
			        	List<FrontMediaDTO> lista=new ArrayList<FrontMediaDTO>();
			        	List<FrontMediaDTO> ee=new ArrayList<FrontMediaDTO>();
			        	for(Media a:p.getMedia()) {
			        		FrontMediaDTO ff=new FrontMediaDTO();
			        		
			        		ff.setFileName(a.getFileName());
			        		ff.setId(a.getId());
			        		ff.setIdPost(a.getPost().getIdPost());
			        		lista.add(ff);
			        		 ee=postImpl.getImagesFiles(lista);
			        	}
			        	
			        	front.setMedia(ee);
			        	
			        	List<FrontTagDTO> lista1=new ArrayList<FrontTagDTO>();
			        	for(Tag t:p.getTags()) {
			        		FrontTagDTO tt=new FrontTagDTO();
			        		tt.setIdPost(t.getPost().getIdPost());
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
				
				
				@PostMapping("/commentPost")
				public ResponseEntity commentPost(@RequestBody CommentDTO comment) {
					Comment c= postService.commentPost(comment);
					
					return new ResponseEntity(comment, HttpStatus.OK);
				}
				//get comments by idPosts
				@GetMapping(value = "/getComments/{idPost}")
			    public ResponseEntity<List<CommntsDTO>> getComments(@PathVariable Long idPost) {
					System.out.println("pretaga javnih profila");
					List<CommntsDTO> fronts=postService.getComments(idPost);
			        	
			        
			        return fronts == null ?
			                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
			                ResponseEntity.ok(fronts);
			    }
				
				
		
		
}
