package com.example.mediamicroservice.service;

import com.example.mediamicroservice.dto.ProfileDTO;
import com.example.mediamicroservice.model.Profile;

public interface ProfileService {
	Profile save(String username);
	Profile update(ProfileDTO profile);
}
