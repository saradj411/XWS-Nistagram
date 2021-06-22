package com.example.profilemicroservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.profilemicroservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    
}
