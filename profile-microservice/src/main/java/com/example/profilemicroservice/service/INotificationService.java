package com.example.profilemicroservice.service;

import java.util.List;
import java.util.Set;

import com.example.profilemicroservice.model.Notification;

public interface INotificationService {
	
	public Notification findById(long id);
	
	public List<Notification> findAll();

}
