package com.example.profilemicroservice.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.profilemicroservice.dto.NotificationDTO;
import com.example.profilemicroservice.model.Notification;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.repository.NotificationRepository;
import com.example.profilemicroservice.service.INotificationService;
import com.example.profilemicroservice.service.UserService;

@Service
public class NotificationService implements INotificationService{

	@Autowired 
	private NotificationRepository notificationRepository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProfileService profileService;
	
	@Override
	public Notification findById(long id) {
		return notificationRepository.findById(id);
	}
	
	public Notification save(NotificationDTO notification)
	{
		Profile loggedUserProfile = profileService.findByUsername(userService.getLoggedUser().getUsername());
		Profile toWhoUsername = profileService.findByUsername(notification.getToWhoUsername());
		return notificationRepository.save(new Notification(
				
				notification.getText(),
				loggedUserProfile,
				toWhoUsername,
				notification.getType()
				));
	}
	
	
	
	public List<Notification> userNotification(String toWhoUsername)
	{
		//Profile loggedUserProfile = profileService.findByUsername(loggedUserUsername);
		List<Notification> allNotificaion = notificationRepository.findAll();
		System.out.println("SERVISE USER NOFII" );
		
		List<Notification> userNotification = new ArrayList<Notification>();
		
		for(Notification notif : allNotificaion)
		{
			System.out.println(notif.getToWhoUsername().getUsername());
	
			if(notif.getToWhoUsername().getUsername().equals(toWhoUsername))
			{
				System.out.println("Pronasao notifikaciju!");
				userNotification.add(notif);
			}
		}
		return userNotification;
		
		
		
	}

	@Override
	public List<Notification> findAll() {
		return notificationRepository.findAll();
	}
	
	public void seenNotification(String toWhoUsername)
	{
		List<Notification> listaZaPromenu = notificationRepository.findAll();
		for(Notification notif : listaZaPromenu)
		{
			if(notif.getToWhoUsername().getUsername().equals(toWhoUsername))
			{
				if(notif.getNotificationSeen() == false)
					notif.setNotificationSeen(true);
					notificationRepository.save(notif);
			}
		}
		
		
		
	}
	

	
	
}
