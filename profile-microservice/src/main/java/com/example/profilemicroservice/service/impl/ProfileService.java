package com.example.profilemicroservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profilemicroservice.dto.UserProfileDTO;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.repository.ProfileRepository;
import com.example.profilemicroservice.service.IProfileService;
import com.example.profilemicroservice.service.IRequestService;
@Service
public class ProfileService  implements IProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;

	@Override
	public Profile findByUsername(String username) {
		return profileRepository.findOneByUsername(username);
	}

	@Override
	public Profile save(UserProfileDTO profileDTO) {
		Profile p = new Profile(
				profileDTO.getUsername(),
				profileDTO.getPrivateProfil(),				
				profileDTO.getMessageFromUnfollowes(),
				profileDTO.getTagFromUnfollowers(),
				profileDTO.getBiography(),
				profileDTO.getWebSite());
		return profileRepository.save(p);
		
		
	}
	@Override
	public List<Profile> findAll() {
		return  profileRepository.findAll();
	}
	
	
	/*
	 * OVO JE KLASA VEKI I KIKI ?
	 * public Profile findByUsername(String username) {
		System.out.println("udje u profile servis " + username);
		List<Profile> lista = profileRepository.findAll();
        for(Profile p:lista) {
    		System.out.println("usao u for  " + p.getUsername());

        	if(p.getUsername().equals(username)) {
        		System.out.println("udje u profile servis for " + p.getUsername());

        		return p;
        		
        	}
        }
       return null;
        
	}*/
	
	
}
