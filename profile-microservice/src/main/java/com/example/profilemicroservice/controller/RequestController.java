package com.example.profilemicroservice.controller;

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

import com.example.profilemicroservice.dto.AcceptRequestDTO;
import com.example.profilemicroservice.dto.RequestDTO;
import com.example.profilemicroservice.dto.RequestFrontDTO;
import com.example.profilemicroservice.model.Category;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.model.Request;
import com.example.profilemicroservice.service.impl.ProfileService;
import com.example.profilemicroservice.service.impl.RequestService;

@RestController
@CrossOrigin
@RequestMapping(value="/request")
public class RequestController {
	
	
	@Autowired
	private RequestService requestService;
	@Autowired
	private ProfileService profileService;
	
	 @PostMapping("/create")
	 public ResponseEntity<Request> addDrug(@RequestBody RequestDTO requestDTO) {

		 Request request = requestService.save(requestDTO);
	        return new ResponseEntity<>(request, HttpStatus.CREATED);
	    }
	 @GetMapping(value = "/findProfile/{username}")
	    public ResponseEntity<Profile> findById(@PathVariable String username) {
	        Profile d= profileService.findByUsername(username);
	        return d == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(d);
	    }
	 
	 @GetMapping(value = "/findAll")
	    public ResponseEntity<List<RequestFrontDTO>> findAll() {
	        List<RequestFrontDTO> drugs=requestService.findAll();
	        return drugs == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(drugs);
	    }
	//acceptRequest(String username,Category category)
	 
	 
	 
	    @PostMapping(value = "/acceptRequest")
	    public ResponseEntity<String> acceptRequest(@RequestBody AcceptRequestDTO dto) {
	    System.out.println("ispisi"+dto.getCategory());
	    System.out.println("ispisi"+dto.getUsername());
	    	requestService.acceptRequest(dto.getUsername(), dto.getCategory());
	    	String list="Request accept";
	    	
	        return list == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(list);

	    }
	    
	    
	    @PostMapping(value = "/refuseRequest/{username}")
	    public ResponseEntity<String> refuseRequest(@PathVariable String username) {
	   
	    	requestService.refuseRequest(username);
	    	String list="Request refuse";
	    	
	        return list == null ?
	                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
	                ResponseEntity.ok(list);

	    }

	
}
