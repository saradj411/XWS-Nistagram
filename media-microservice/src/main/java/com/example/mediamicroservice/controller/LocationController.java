package com.example.mediamicroservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mediamicroservice.dto.FrontMediaDTO;
import com.example.mediamicroservice.dto.FrontPostDTO;
import com.example.mediamicroservice.model.Location;
import com.example.mediamicroservice.model.Media;
import com.example.mediamicroservice.model.Post;
import com.example.mediamicroservice.repository.LocationRepository;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/location")
public class LocationController {

	@Autowired
	LocationRepository locationRepo;
	
	
	@GetMapping(value = "/findAll")
    public ResponseEntity<List<Location>> findAll() {
		System.out.println("uslooo");
        List<Location> locations=locationRepo.findAll();
       
        return locations == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(locations);
    }
	
}
