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

import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.FrontPostDTO;
import com.example.mediamicroservice.dto.PostDTO;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.repository.PostRepository;
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
	
	private static String uploadDir="user-photos";
	
	
	@PostMapping("/saveImage")
	public String saveImage(@RequestParam("file") MultipartFile multipartFile) throws IOException{
		
		String fileName=StringUtils.cleanPath(multipartFile.getOriginalFilename().replaceAll("\\s", ""));
		uploadDir="user-photos";
		MediaUpload.saveFile(uploadDir, fileName, multipartFile);
		System.out.println("slikaa:0"+fileName);
		return fileName;
		
	}
	@PostMapping("/addNewPost")
	public ResponseEntity<Post> addNewPost(@RequestBody PostDTO postDTO){
		
		Post response=postService.addNewPost(postDTO);
		
		return  response == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<Post>(response,HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/getPostByUsername/{username}")
    public ResponseEntity<List<FrontPostDTO>> findAllByIdPharm(@PathVariable String username) {
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
	
	
}
