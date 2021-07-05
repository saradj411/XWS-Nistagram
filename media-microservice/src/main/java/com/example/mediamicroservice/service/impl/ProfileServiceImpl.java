package com.example.mediamicroservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mediamicroservice.dto.ProfileDTO;
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


	@Override
	public Profile update(ProfileDTO profile) {
		System.out.println("uslo u kontroler:"+profile.getOldPassword());
		Profile pat = profileRepository.getOneByUsername(profile.getOldPassword());
		
        int ids=pat.getId();
        System.out.println("id:"+pat.getUsername());
       
        pat.setUsername(profile.getNewPassword());
        
        Profile u=profileRepository.save(pat);
        return u;
	}

}
