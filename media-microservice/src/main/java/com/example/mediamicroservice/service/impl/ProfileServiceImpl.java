package com.example.mediamicroservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.model.Profile;
import com.example.mediamicroservice.repository.ProfileRepository;
import com.example.mediamicroservice.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileRepository profileRepository;
	
	
	@Override
	public Profile save(String username) {
		Profile profile=new Profile();
		profile.setUsername(username);
		
		Profile newProfile=profileRepository.save(profile);
		
		return newProfile;
	}

}
