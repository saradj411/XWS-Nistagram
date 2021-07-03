package com.example.profilemicroservice.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.servlet.http.HttpSession;

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
import com.example.profilemicroservice.service.impl.UserServiceImpl;

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
	
	@PostMapping(value ="/getAllRequest")
	public ResponseEntity<Set<Profile>> getRequstForFollow(@RequestBody HashMap<String,String> username)	
	{
		Set<Profile> request = profileService.request4follow(username.get("username"));	
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@PostMapping(value ="/getAllRequestTo")
	public ResponseEntity<Set<Profile>> getAllRequestTo(@RequestBody HashMap<String,String> username)	
	{
		Set<Profile> request = profileService.request2follow(username.get("username"));	
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@PostMapping(value ="/getAllFollowing")
	public ResponseEntity<Set<Profile>> getAllFollowing(@RequestBody HashMap<String,String> username)	
	{
		Set<Profile> request = profileService.getFollowing(username.get("username"));	
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@PostMapping(value ="/getAllFollowers")
	public ResponseEntity<Set<Profile>> getAllFollowers(@RequestBody HashMap<String,String> username)	
	{
		Set<Profile> request = profileService.getFollowers(username.get("username"));	
		return new ResponseEntity<>(request, HttpStatus.OK);
	}
	
	@PostMapping(value ="/getProfileByUsername")
	public ResponseEntity<Profile> getProfileByUsername(@RequestBody HashMap<String,String> username)	
	{
		Profile profile = profileService.findByUsername(username.get("username"));	
		return new ResponseEntity<>(profile, HttpStatus.OK);
	}
	
	
	
	//Ovo gadja i kad je profil privatan i kad nije, samo odredi koju funckiju poziva posle
	@PostMapping(value ="/addRequest4follow")
	public ResponseEntity<Profile> addRequest4follow(@RequestBody HashMap<String,String> username)	
	{
		System.out.println("STA IAM");
		Profile prof = profileService.addRequest4follow(username.get("username"));
		System.out.println(prof.getUsername());
		return new ResponseEntity<>(prof, HttpStatus.OK);
	}
	
	@PostMapping(value ="/deleteRequestAndFollow")
	public ResponseEntity<Profile> deleteRequestAndFollow(@RequestBody HashMap<String,String> username)	
	{		
		Profile prof = profileService.deleteRequestOrFollow(username.get("username"));
		System.out.println(prof.getUsername());
		return new ResponseEntity<>(prof, HttpStatus.OK);
	}
	
	
}
