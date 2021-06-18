package com.example.profilemicroservice.service;



import java.util.List;

import com.example.profilemicroservice.model.Authority;

public interface AuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
}
