package com.example.profilemicroservice.service;

import java.util.List;

import com.example.profilemicroservice.dto.RequestDTO;
import com.example.profilemicroservice.dto.RequestFrontDTO;
import com.example.profilemicroservice.model.Category;
import com.example.profilemicroservice.model.Request;

public interface IRequestService {

	Request save(RequestDTO dto);

	List<RequestFrontDTO> findAll();

	void acceptRequest(String username, Category category);

	void refuseRequest(String username);

}
