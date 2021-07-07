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
		
		if(toDelete.getPrivateProfil() == true)
		{
			Set<Profile> forfollow = loggedUserProfile.getRequest4Follow();
			forfollow.remove(toDelete);
			
			Set<Profile> tofollow = toDelete.getRequest2Follow();
			tofollow.remove(loggedUserProfile);
			
			Set<Profile> following = loggedUserProfile.getFollowing();
			following.remove(toDelete);
			
			Set<Profile> followers = toDelete.getFollowers();
			followers.remove(loggedUserProfile);
			
			
			
			loggedUserProfile.setRequest4Follow(forfollow);
			toDelete.setRequest2Follow(tofollow);	
			loggedUserProfile.setFollowing(following);
			toDelete.setFollowers(followers);
			
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
	
	public void acceptFollowRequest(String username)
	{
		Profile loggedUserProfile = findByUsername(userService.getLoggedUser().getUsername());		
		Profile addToFollowers = findByUsername(username);
		
		Set<Profile>  newFollower = loggedUserProfile.getFollowers();
		newFollower.add(addToFollowers);
		
		Set<Profile> deleteFromRequst = loggedUserProfile.getRequest2Follow();
		deleteFromRequst.remove(addToFollowers);
		
		Set<Profile> newFollowing = addToFollowers.getFollowing();
		newFollowing.add(loggedUserProfile);
		
		Set<Profile> deleteReq  = addToFollowers.getRequest4Follow();
		deleteReq.remove(loggedUserProfile);
		
		
		profileRepository.save(loggedUserProfile);
		profileRepository.save(addToFollowers);		
			
		
	}
	
	public void declineFollowRequest(String username)
	{
		Profile loggedUserProfile = findByUsername(userService.getLoggedUser().getUsername());		
		Profile delete = findByUsername(username);
		
		
		
		Set<Profile> deleteFromRequst = loggedUserProfile.getRequest2Follow();
		deleteFromRequst.remove(delete);	
				
		Set<Profile> deleteReq  = delete.getRequest4Follow();
		deleteReq.remove(loggedUserProfile);
		
		
		profileRepository.save(loggedUserProfile);
		profileRepository.save(delete);		
			
		
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
	
	 @Override
	    public Boolean viewPosts(String myUsrename,String usernameOfSearchAccount) {
		 Boolean view=true;
		 int i=0;
		 
	    	//treba mi taj profil i following od njega
		 //ako mu je profil privatan i ako ga ne prati vrati false0
	    	Profile myProfile=profileRepository.findOneByUsername(myUsrename);
	    	Profile profileOfSearchAccount=profileRepository.findOneByUsername(usernameOfSearchAccount);
	    	System.out.println("moj username jee "+myProfile.getUsername());
	    	Set<Profile>profiles=profileOfSearchAccount.getFollowers();
	    	if(profileOfSearchAccount.getPrivateProfil().equals(true)) {
	    		for(Profile p:profiles) {
	    			if(p.getUsername().equals(myUsrename)) {
	    				
	    			}else {
	    				i++;
	    			}
		    	}
	    		if(profiles.size()==i) {
	    			view=false;
	    		}
	    		
	    	}
	    	
			return view;
	    	
	    }
	
}
