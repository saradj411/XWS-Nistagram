package com.example.profilemicroservice.service;

import com.example.profilemicroservice.model.Profile;

public interface IProfileService {
	
	public Profile findByUsername(String username);

}
