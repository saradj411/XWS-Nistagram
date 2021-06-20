package com.example.profilemicroservice.service;


import java.util.List;

import com.example.profilemicroservice.dto.LoginDTO;
import com.example.profilemicroservice.dto.UserDTO;
import com.example.profilemicroservice.dto.UserRegistrationDTO;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.model.UserRequest;

public interface UserService {
    User findByUsername(String username);
    List<UserDTO> findAll();
    List<UserDTO> findAllKorisnike();
    User save(UserRequest userRequest);
    User addUser(UserRegistrationDTO userInfo);
    void delete(Long id);
    void activateUser(Long id);
    //void activateComment(Long id); 
    
    
    public User getLoogedIn();
    public User getLoggedUser();
    User update(User user);
	User findById(Long id);

    
    
    public User LogIn(LoginDTO loginDTO);
}
