package com.example.profilemicroservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profilemicroservice.connections.MediaConnection;
import com.example.profilemicroservice.dto.UserDTO;
import com.example.profilemicroservice.dto.UserRegistrationDTO;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.service.UserService;

import java.util.List;

import javax.validation.Valid;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MediaConnection mediaConnection;
    
    @PostMapping("/public/register")
    public ResponseEntity add(@Valid @RequestBody UserRegistrationDTO user) {
        userService.addUser(user);
        mediaConnection.add(user.getUsername());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/updateUser")
    ResponseEntity<String> update(@RequestBody User user)
    {
        userService.update(user);
        return new ResponseEntity<>("ajdeee", HttpStatus.CREATED);
    }
    @GetMapping(value = "/findOneById/{id}")
    //@Async
    public ResponseEntity<User> findById(@PathVariable Long id) {
        System.out.println("user contorler "+id);
        User d= userService.findById(id);
        return d == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(d);
    }
    
    
    @GetMapping(value = "/findAll")
   // @PreAuthorize("hasRole('KORISNIK')")
    public ResponseEntity<List<UserDTO>> findAll() {
    	
    	User u=userService.getLoogedIn();
    	
        List<UserDTO> user=userService.findAll();
        return user == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND) :
                ResponseEntity.ok(user);
    }
    @GetMapping(value = "/loggedUser")
    public ResponseEntity<User> getLoggedUser()
    {
        System.out.println("Nasao logovanog usera?");

        User u = userService.getLoogedIn();
        return  u == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(u, HttpStatus.OK);
    }
}
