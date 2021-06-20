package com.example.profilemicroservice.service.impl;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.profilemicroservice.common.TimeProvider;
import com.example.profilemicroservice.config.consts.UserRoles;
import com.example.profilemicroservice.dto.UserDTO;
import com.example.profilemicroservice.dto.UserRegistrationDTO;
import com.example.profilemicroservice.exception.ApiRequestException;
import com.example.profilemicroservice.exception.ResourceNotFoundException;
import com.example.profilemicroservice.mappers.UserMapper;
import com.example.profilemicroservice.model.Authority;
import com.example.profilemicroservice.model.ConfirmationToken;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.model.UserRequest;
import com.example.profilemicroservice.repository.AuthorityRepository;
import com.example.profilemicroservice.repository.ConfirmationTokenRepository;
import com.example.profilemicroservice.repository.UserRepository;
import com.example.profilemicroservice.service.AuthorityService;
import com.example.profilemicroservice.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;

	@Autowired
	private ConfirmationTokenRepository tokenRepository;

	@Autowired
	private TimeProvider timeProvider;

	@Autowired
	private AuthorityRepository authorityRepository;
/*
	@Autowired
	private KomentarRepository komentarRepository;
*/
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}
	
	@Override
    public void update(User user) {
        User pat = userRepository.getOne(user.getId());
        Long ids=pat.getId();
        pat.setName(user.getName());
        pat.setSurname(user.getSurname());
        pat.setAddress(user.getAddress());
        pat.setCity(user.getCity());
        pat.setCountry(user.getCountry());
        pat.setPhone(user.getPhone());
        pat.setPassword(user.getPassword());
        pat.setEmail(user.getEmail());
        pat.setOldUsername(user.getOldUsername());
        pat.setAdmin(false);
        pat.setUsername(user.getUsername());
        
        userRepository.save(pat);
    }
	
	
	public User getLoogedIn() throws AccessDeniedException {
//		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String username;
//		if (principal instanceof UserDetails) {
//			 username = ((UserDetails)principal).getUsername();
//		} else {
//			 username = principal.toString();
//		}
//		System.out.println(username+"userko");
//
//		return  findByUsername(username);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		Object currentUser = auth.getPrincipal();
		
		String username = "";
		if(currentUser instanceof UserDetails){
			username = ((UserDetails)currentUser).getUsername();
		}else{
			username = currentUser.toString();
		}
		System.out.println("usernameeeee:"+username);
		System.out.println("nameee:"+auth.getName());
		System.out.println("autorititii:"+auth.getAuthorities());
		User u = userRepository.findByUsername(username);
		return u;
	}
	
	
	
	
	
	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<UserDTO> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		List<UserDTO> userDTOS = new ArrayList<>();

		for(User u: result){
			userDTOS.add(modelMapper.map(u, UserDTO.class));
		}

		return userDTOS;
	}

	public List<UserDTO> findAllKorisnike() throws AccessDeniedException {
		List<User> allUsers = userRepository.findAll();
		List<UserDTO> result = new ArrayList<>();
		for(User u : allUsers) {
			if(!u.isAdmin()) {
				result.add(modelMapper.map(u, UserDTO.class));
			}
		}
		return result;
	}

	@Override
	public User save(UserRequest userRequest) {
		User u = new User();
		// pre nego sto postavimo lozinku u atribut hesiramo je
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setName(userRequest.getFirstname());
		u.setSurname(userRequest.getLastname());
		
		List<Authority> auth = authService.findByname("ROLE_USER");
		// u primeru se registruju samo obicni korisnici i u skladu sa tim im se i dodeljuje samo rola USER
		u.setAuthorities(auth);
		
		u = this.userRepository.save(u);
		return u;
	}

	@Override
	public User addUser(UserRegistrationDTO userInfo) {
		if (userRepository.findByUsername(userInfo.getUsername()) != null) {
			throw new ApiRequestException("Username '" + userInfo.getUsername() + "' already exists.");
		}

		if (!userInfo.getPassword().equals(userInfo.getRepeatPassword())) {
			throw new ApiRequestException("Provided passwords must be the same.");
		}

		if (userRepository.findByEmail(userInfo.getEmail()) != null) {
			throw new ApiRequestException("Email '" + userInfo.getEmail() + "' is taken.");
		}

		User user = createNewUserObject(userInfo);
		userRepository.save(user);

		ConfirmationToken token = new ConfirmationToken(user);
		tokenRepository.save(token);

		//mailSenderService.sendRegistrationMail(token);

		return user;
	}

	private User createNewUserObject(UserRegistrationDTO userInfo) {
		//Korisnik user = UserMapper.toKorisnikEntity(userInfo);
		User user = UserMapper.toUserEntity(userInfo);
		user.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		user.setLastPasswordResetDate(timeProvider.nowTimestamp());
		user.getUserAuthorities().add(authorityRepository.findByName(UserRoles.ROLE_KORISNIK));
		user.setName(userInfo.getName());
		user.setSurname(userInfo.getSurname());
		user.setEmail(userInfo.getEmail());
		user.setAddress(userInfo.getAddress());
		user.setCity(userInfo.getCity());
		user.setPhone(userInfo.getPhone());
		user.setCountry(userInfo.getCountry());

		//aktivacija naloga
		user.setEnabled(true);

		user.setAdmin(false);

		return user;
	}

	@Override
	public void delete(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " doesn't exist"));

		userRepository.delete(user);
	}

	@Override
	public void activateUser(Long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " doesn't exist"));

		if(user.isEnabled()) {
			user.setEnabled(false);
		} else {
			user.setEnabled(true);
		}
		userRepository.save(user);
	}
	@Override
    public User getLoggedUser() {
        Authentication loggedUser = SecurityContextHolder.getContext().getAuthentication();
        String email = loggedUser.getName();
        User u = userRepository.findByUsername(email);
        return  u;
    }
}
