package com.example.profilemicroservice.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profilemicroservice.dto.UserProfileDTO;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.service.impl.ProfileService;

@RestController
@RequestMapping(value = "/api/profile")
public class ProfileController {
	
@Autowired
private ProfileService profileService;

User loggedUser;

	@PostMapping("/public/registerProfile")
	public ResponseEntity<Profile> add(@RequestBody UserProfileDTO profileDTO)
	{
		Profile profile = profileService.save(profileDTO);
		return profile == null ? new ResponseEntity<Profile>(HttpStatus.BAD_REQUEST)
				: new ResponseEntity<Profile>(profile, HttpStatus.CREATED);
	}
	
	@GetMapping(value ="/getAllProfiles")
	public ResponseEntity<ArrayList<Profile>> getAllProfiles()	
	{
		ArrayList<Profile> allProfile = (ArrayList<Profile>) profileService.findAll();
		return new ResponseEntity<>(allProfile, HttpStatus.OK);
	}

}
