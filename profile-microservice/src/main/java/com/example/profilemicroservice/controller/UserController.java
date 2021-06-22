package com.example.profilemicroservice.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.profilemicroservice.connections.MediaConnection;
import com.example.profilemicroservice.dto.LoginDTO;
import com.example.profilemicroservice.dto.ProfileDTO;
import com.example.profilemicroservice.dto.UserDTO;
import com.example.profilemicroservice.dto.UserRegistrationDTO;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.service.UserService;
import com.example.profilemicroservice.service.impl.UserServiceImpl;

// Primer kontrolera cijim metodama mogu pristupiti samo autorizovani korisnici
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private MediaConnection mediaConnection;
    
    //@Context
	HttpServletRequest request;
	User loggedUser;
    
    @PostMapping("/public/register")
    public ResponseEntity<User> add(@Valid @RequestBody UserRegistrationDTO user) {
        User u=userService.addUser(user);
        mediaConnection.add(user.getUsername());
        return  u == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
            new ResponseEntity<User>(u,HttpStatus.CREATED);
    }

    @PostMapping("/updateUser")
    ResponseEntity<String> update(@RequestBody User user, HttpSession session)
    {
    	System.out.println("uslo u kontroler");
        User u=userService.update(user);
        session.setAttribute("loggedInUser", u);
        loggedUser = u;
        ProfileDTO profile=new ProfileDTO(u.getUsername(),user.getOldUsername());
        mediaConnection.update(profile);
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
    
    
    @PostMapping(value="/login")
    public ResponseEntity<User> logIn(@RequestBody LoginDTO loginDTO, HttpSession session)
    {
    	System.out.println("USAO");
        System.out.println(loginDTO.getUsername());
        User u = userService.LogIn(loginDTO);
        		if(u != null){
        			System.out.println("NEKO SE ULOGOVAO.");
        				session.setAttribute("loggedInUser", u);
        				loggedUser = u;
        				//return Response.status(200).entity(u).build();
        				return new ResponseEntity<>(u, HttpStatus.OK);
        			}
        		
        		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    
    @GetMapping(value="/getLoggedUser")	
	public ResponseEntity GetLoggedUser(HttpSession session)
	{
		System.out.println("Get logged user, pozvan");
		User user = (User) session.getAttribute("loggedUser");
		System.out.println(user);
		System.out.println(loggedUser);
		if(loggedUser != null)
			return new ResponseEntity<User>(loggedUser, HttpStatus.OK);
		else
			return new ResponseEntity<String>("User is not logged", HttpStatus.BAD_REQUEST);
	}
    
    @PostMapping(value = "/logout")
	public ResponseEntity<String> logOut(HttpSession session){
		if (session.getAttribute("loggedInUser") == null) {
			return new ResponseEntity<String>("No user logged in!", HttpStatus.BAD_REQUEST);
		}
		loggedUser = null;
		session.invalidate();
		return new ResponseEntity<String>("Logged Out!!", HttpStatus.OK);
	}
    
}
