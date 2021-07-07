package com.example.profilemicroservice.service;

import java.util.List;
import java.util.Set;

import com.example.profilemicroservice.dto.UserProfileDTO;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.model.User;

public interface IProfileService {
	
	public Profile findByUsername(String username);
	Profile save(UserProfileDTO profileDTO);
	List<Profile> findAll();
	
	Set<Profile> request4follow(String username);
	Set<Profile> request2follow(String username);
	Set<Profile> getFollowing(String username);
	Set<Profile> getFollowers(String username);
	
	
	Profile addRequest4follow(String usernameForFollow);
	Boolean viewPosts(String myUsrename, String usernameOfSearchAccount);
}
