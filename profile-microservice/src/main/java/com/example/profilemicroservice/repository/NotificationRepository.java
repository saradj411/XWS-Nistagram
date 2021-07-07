package com.example.profilemicroservice.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.profilemicroservice.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long>{

	Notification findById(long id);
	Set<Notification> findByToWhoUsername(String username);
}
