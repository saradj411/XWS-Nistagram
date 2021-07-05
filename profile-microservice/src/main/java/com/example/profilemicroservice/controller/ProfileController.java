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

import com.example.profilemicroservice.connections.MediaConnection;
import com.example.profilemicroservice.dto.ProfileeDTO;
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

@Autowired
private MediaConnection mediaConnection;

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
		ArrayList<ProfileeDTO>dtos=new ArrayList<>();
		ArrayList<Profile> allProfile = (ArrayList<Profile>) profileService.findAll();
		for(Profile p:allProfile) {
			ProfileeDTO d=new ProfileeDTO(p.getId(),p.getUsername(),p.getPrivateProfil());
			dtos.add(d);
			System.out.println("svi profili u profile controller su   "+p.getUsername());;
		}
		//mediaConnection.getAllProfiles(dtos);
		return new ResponseEntity<>(allProfile, HttpStatus.OK);
	}
	@GetMapping(value ="/getAllPublicProfiles")
	public ArrayList<ProfileeDTO> getAllPublicProfiles()	
	{
		ArrayList<ProfileeDTO>dtos=new ArrayList<>();
		ArrayList<Profile> allProfile = (ArrayList<Profile>) profileService.findAll();
		for(Profile p:allProfile) {
			if(p.getPrivateProfil().equals(false)) {
				ProfileeDTO d=new ProfileeDTO(p.getId(),p.getUsername(),p.getPrivateProfil());
				dtos.add(d);
				System.out.println("svi javni profili su"+p.getUsername());
			}
			
		}
		//mediaConnection.getAllProfiles(dtos);
		return dtos;
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
	
	@PostMapping(value ="/acceptFollowRequest")
	public ResponseEntity acceptFollowRequest(@RequestBody HashMap<String,String> username)	
	{		
		profileService.acceptFollowRequest(username.get("username"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@PostMapping(value ="/declineFollowRequest")
	public ResponseEntity declineFollowRequest(@RequestBody HashMap<String,String> username)	
	{		
		profileService.declineFollowRequest(username.get("username"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value ="/getAllCloseFriends")
	public ResponseEntity<Set<Profile>> getAllCloseFriends()	
	{		
		Set<Profile> closeFriends = profileService.getCloseFriend();
		return new ResponseEntity<Set<Profile>>(closeFriends, HttpStatus.OK);
	}
	
	@PostMapping(value ="/addNewCloseFriend")
	public ResponseEntity<Profile> addNewCloseFriend(@RequestBody HashMap<String,String> username)	
	{		
		Profile closeFriends = profileService.addCloseFriend(username.get("username"));
		return new ResponseEntity<Profile>(closeFriends, HttpStatus.OK);
	}
	
	@PostMapping(value ="/deleteNewCloseFriend")
	public ResponseEntity deleteNewCloseFriend(@RequestBody HashMap<String,String> username)	
	{		
		profileService.deleteCloseFriend(username.get("username"));
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@GetMapping(value ="/getBlockedProfiles")
	public ResponseEntity<Set<Profile>> getBlockedProfiles()	
	{		
		Set<Profile> blockedProfile = profileService.getBlockedPforile();
		return new ResponseEntity<Set<Profile>>(blockedProfile, HttpStatus.OK);
	}
	
	@PostMapping(value ="/blockProfile")
	public ResponseEntity<Set<Profile>> blockProfile(@RequestBody HashMap<String,String> username)	
	{		
		Set<Profile> blockedList = profileService.addBlockProfile(username.get("username"));
		return new ResponseEntity<>(blockedList, HttpStatus.OK);
	}
	
	@PostMapping(value ="/unblockProfile")
	public ResponseEntity unblockProfile(@RequestBody HashMap<String,String> username)	
	{		
		profileService.deleteBlockedProfile(username.get("username"));
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	@GetMapping(value ="/getMutedProfiles")
	public ResponseEntity<Set<Profile>> getMutedProfiles()	
	{		
		Set<Profile> mutedProfile = profileService.getMutedPforile();
		return new ResponseEntity<Set<Profile>>(mutedProfile, HttpStatus.OK);
	}
	
	
	@PostMapping(value ="/muteProfile")
	public ResponseEntity<Set<Profile>> muteProfile(@RequestBody HashMap<String,String> username)	
	{		
		Set<Profile> blockedList = profileService.addMutedProfile(username.get("username"));
		return new ResponseEntity<>(blockedList, HttpStatus.OK);
	}
	
	@PostMapping(value ="/unmuteProfile")
	public ResponseEntity unmuteProfile(@RequestBody HashMap<String,String> username)	
	{		
		profileService.deleteMutedProfile(username.get("username"));
		return new ResponseEntity<>( HttpStatus.OK);
	}
	
	
	
}
