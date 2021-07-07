package com.example.profilemicroservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profilemicroservice.dto.NotificationDTO;
import com.example.profilemicroservice.model.Notification;
import com.example.profilemicroservice.service.impl.NotificationService;

@RestController
@RequestMapping(value = "/api/notification")

public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping(value = "/addNotification")
	public ResponseEntity<Notification> addNotification(@RequestBody NotificationDTO notificationDTO)
	{
		Notification notification = notificationService.save(notificationDTO);
		return notification == null ? new ResponseEntity<Notification>(HttpStatus.BAD_REQUEST)
				: new ResponseEntity<Notification>(notification, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/getUserNofitcation")
	public ResponseEntity<List<Notification>> getUserNofitcation(@RequestBody HashMap<String,String> username)
	{
		String u = username.get("username");		
		List<Notification> notificationList = notificationService.userNotification(u);	
		return new ResponseEntity<List<Notification>>(notificationList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/seenNotification")
	public ResponseEntity seenNotification(@RequestBody HashMap<String,String> username)
	{
		String u = username.get("username");
		notificationService.seenNotification(u);	
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
