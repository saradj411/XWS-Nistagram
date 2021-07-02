package com.example.profilemicroservice.service;

import java.util.List;

import com.example.profilemicroservice.dto.UserProfileDTO;
import com.example.profilemicroservice.model.Profile;

public interface IProfileService {
	
	public Profile findByUsername(String username);
	Profile save(UserProfileDTO profileDTO);
	List<Profile> findAll();

}
