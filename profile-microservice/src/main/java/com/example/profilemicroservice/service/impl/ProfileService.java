package com.example.profilemicroservice.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profilemicroservice.dto.UserProfileDTO;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.repository.ProfileRepository;
import com.example.profilemicroservice.service.IProfileService;
@Service
public class ProfileService  implements IProfileService {
	
	@Autowired
	private ProfileRepository profileRepository;
	@Autowired
	private UserServiceImpl userService;
	

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
	
	
	//Trazimo da li ulogovani korisnik ima zahteva
	@Override
	public Set<Profile> request4follow(String username) {
		
		Profile user = findByUsername(username);		
		return user.getRequest4Follow();
	}
	@Override
	public Set<Profile> request2follow(String username) {
		
		Profile user = findByUsername(username);		
		return user.getRequest2Follow();
	}
	
	@Override
	public Set<Profile> getFollowing(String username) {
		
		Profile user = findByUsername(username);		
		return user.getFollowing();
	}
	@Override
	public Set<Profile> getFollowers(String username) {
		
		Profile user = findByUsername(username);		
		return user.getFollowers();
	}
	
	
	@Override
	public Profile addRequest4follow(String usernameForFollow) {		
		Profile loggedUserProfile = findByUsername(userService.getLoggedUser().getUsername());		
		Profile requestTo = findByUsername(usernameForFollow);
		
		if(requestTo.getPrivateProfil()==true)
		{
			Set<Profile> forfollow = loggedUserProfile.getRequest4Follow();
			forfollow.add(requestTo);
			
			Set<Profile> tofollow = requestTo.getRequest2Follow();
			tofollow.add(loggedUserProfile);
			
			loggedUserProfile.setRequest4Follow(forfollow);
			requestTo.setRequest2Follow(tofollow);		
			
		}
		else
		{
			Set<Profile> following = loggedUserProfile.getFollowing();
			following.add(requestTo);
			
			Set<Profile> followers = requestTo.getFollowers();
			followers.add(loggedUserProfile);
			
			loggedUserProfile.setFollowing(following);
			requestTo.setFollowers(followers);
			
		}

		profileRepository.save(loggedUserProfile);
		profileRepository.save(requestTo);	
		
		return loggedUserProfile;		
		
		
	}
	
	public Profile deleteRequestOrFollow(String usernameForFollow)
	{
		Profile loggedUserProfile = findByUsername(userService.getLoggedUser().getUsername());		
		Profile toDelete = findByUsername(usernameForFollow);
		
		if(toDelete.getPrivateProfil()==true)
		{
			Set<Profile> forfollow = loggedUserProfile.getRequest4Follow();
			forfollow.remove(toDelete);
			
			Set<Profile> tofollow = toDelete.getRequest2Follow();
			tofollow.remove(loggedUserProfile);
			
			loggedUserProfile.setRequest4Follow(forfollow);
			toDelete.setRequest2Follow(tofollow);		
			
		}
		else
		{
			Set<Profile> following = loggedUserProfile.getFollowing();
			following.remove(toDelete);
			
			Set<Profile> followers = toDelete.getFollowers();
			followers.remove(loggedUserProfile);
			
			loggedUserProfile.setFollowing(following);
			toDelete.setFollowers(followers);
			
		}

		profileRepository.save(loggedUserProfile);
		profileRepository.save(toDelete);	
		
		return loggedUserProfile;		
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
