package com.example.mediamicroservice.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.ProfileDTO;
import com.example.mediamicroservice.dto.ProfileeDTO;
import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.service.FavoritesService;
import com.example.mediamicroservice.service.ProfileService;

@RestController
@RequestMapping(value = "/user")
public class PofileController {

	@Autowired
	private ProfileService profileService;
	@Autowired
	FavoritesService favoritesService;
	
	@PostMapping("/save/{username}")
    public ResponseEntity add(@PathVariable String username) {
		Profile profile=profileService.save(username);
		favoritesService.save(profile);
		return profile== null ?
                new ResponseEntity<>(HttpStatus.IM_USED) :
                ResponseEntity.ok().build();
		
    }
	
	@PostMapping("/updateProfile")
    ResponseEntity<String> update(@RequestBody ProfileDTO profile)
    {
    	System.out.println("uslo u kontroler");
        Profile u=profileService.update(profile);
        
        return new ResponseEntity<>("ajdeee", HttpStatus.CREATED);
    }
	
	@GetMapping("/getAllProfiles")
    ResponseEntity<String> getAllProfiles(ArrayList<ProfileeDTO> allProfile)
    {
    	System.out.println("uslo u kontroler za sve usereeeeeeeeeeeeee pr");
        //Profile u=profileService.update(profile);
    	for(ProfileeDTO p:allProfile) {
    		System.out.println(" ************************ "+p.getUsername());
    		}
        
        return new ResponseEntity<>("ajdeee", HttpStatus.CREATED);
    }
}
