package com.example.profilemicroservice.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profilemicroservice.dto.RequestDTO;
import com.example.profilemicroservice.dto.RequestFrontDTO;
import com.example.profilemicroservice.model.Category;
import com.example.profilemicroservice.model.Request;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.repository.ProfileRepository;
import com.example.profilemicroservice.repository.RequestRepository;
import com.example.profilemicroservice.repository.UserRepository;
import com.example.profilemicroservice.service.IRequestService;
import com.example.profilemicroservice.service.UserService;

@Service
public class RequestService  implements IRequestService {
	
	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private ProfileRepository profileRepository;
	
	@Autowired
	private RequestRepository requestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
    public Request save(RequestDTO dto) {
		Request d = new Request();
	 	System.out.print("Username jeeeeeeeeeeeeeeee: "+dto.getUsername());
	 	d.setUsername(dto.getUsername());
        d.setName(dto.getName());
        d.setCategory(dto.getCategory());
        d.setImage(dto.getImage());
        d.setProcessed(false);
        d.setLastname(dto.getLastname());
        //Profile ppp= profileRepository.save(p);
        //profileRepository.save(p);
        //d.setUsername(p);
        Request novi = requestRepository.save(d);
 
        return novi;
 
	 	
	 	
	 	//User u=userService.getLoggedUser();
		//System.out.print("Logovani user je "+u.getName());
		//p.setUsername(u.getUsername());
     	
		/*
		 * List<User>users=userRepository.findAll(); for(User u:users) { if
		 * (u.getUsername().equals(dto.getUsername())) {
		 * System.out.print("usaoo "+u.getUsername());
		 * 
		 * 
		 * //p=new
		 * Profile(u.getName(),u.getSurname(),u.getAddress(),u.getCity(),u.getCountry(),
		 * u.getEmail(),u.getPhone(),u.getPassword(),null,u.getUsername()); } }
		 */
	
     	//p=profileRepository.getOne(n);
    // 	d.setUsername(p);
       }
	
	@Override
	public List<RequestFrontDTO> findAll(){
		
		List<RequestFrontDTO> dto=new ArrayList<RequestFrontDTO>();
		List<Request>lista=requestRepository.findAll();
		for(Request r:lista) {
			if(r.getProcessed().equals(false)) {
				RequestFrontDTO reqD=new RequestFrontDTO(r.getName(),r.getLastname(),r.getUsername(),r.getCategory(),r.getImage());
				RequestFrontDTO ee=getImagesFiles(reqD);
				System.out.println("byteeeeeeeeeees:0"+ee.getImageByte());
				dto.add(ee);
			}
			
		}
		return dto;
		
	}
	
	public RequestFrontDTO getImagesFiles(RequestFrontDTO fronts) {
		RequestFrontDTO listaDTO=new RequestFrontDTO();
		if(fronts!=null) {
			String filePath=new File("").getAbsolutePath();
			filePath=filePath.concat("/"+"request-photos"+"/");
			
				listaDTO=imageFile(fronts, filePath);
		
		}
		return listaDTO;
	}
	public RequestFrontDTO imageFile(RequestFrontDTO front,String filePath) {
		RequestFrontDTO frontDTO=front;
		//List<byte[]> imageBytes=new ArrayList<byte[]>();
		//front.setImageByte(imageBytes);
		File in=new File(filePath+"/"+front.getImage());
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
	public void acceptRequest(String username,Category category){
		
		
	
	   Request resuest=requestRepository.findByUsername(username);
	   resuest.setProcessed(true);
       User user=userService.findByUsername(username);
       user.setCategory(category);
       userRepository.save(user);
			    
			
		}
		
	
	@Override
	public void refuseRequest(String username){
		
		
	
	   Request resuest=requestRepository.findByUsername(username);
	   resuest.setProcessed(true);
       User user=userService.findByUsername(username);
      
       userRepository.save(user);
			    
			
		}
	}
	


