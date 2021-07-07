package com.example.profilemicroservice.service.impl;


import java.util.ArrayList;
import java.util.List;

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
import com.example.profilemicroservice.dto.ChangeProfileDTO;
import com.example.profilemicroservice.dto.LoginDTO;
import com.example.profilemicroservice.dto.UserDTO;
import com.example.profilemicroservice.dto.UserRegistrationDTO;
import com.example.profilemicroservice.enums.Role;
import com.example.profilemicroservice.exception.ApiRequestException;
import com.example.profilemicroservice.exception.ResourceNotFoundException;
import com.example.profilemicroservice.mappers.UserMapper;
import com.example.profilemicroservice.model.Authority;
import com.example.profilemicroservice.model.ConfirmationToken;
import com.example.profilemicroservice.model.Profile;
import com.example.profilemicroservice.model.User;
import com.example.profilemicroservice.model.UserRequest;
import com.example.profilemicroservice.repository.AuthorityRepository;
import com.example.profilemicroservice.repository.ConfirmationTokenRepository;
import com.example.profilemicroservice.repository.ProfileRepository;
import com.example.profilemicroservice.repository.UserRepository;
import com.example.profilemicroservice.service.AuthorityService;
import com.example.profilemicroservice.service.UserService;
import com.netflix.discovery.converters.Auto;

@Service("UserServiceImpl")
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
	
	@Autowired
	private ProfileRepository profileRepository;
	
	
/*
	@Autowired
	private KomentarRepository komentarRepository;
*/
	@Autowired
	private ModelMapper modelMapper;

	private User loggedUser;
	
	@Override
	public User findByUsername(String username) throws UsernameNotFoundException {
		User u = userRepository.findByUsername(username);
		return u;
	}
	
	@Override
    public User update(ChangeProfileDTO user) {
		System.out.println("uslo u kontroler");
        User pat = userRepository.getOne(user.getId());
        System.out.println("Novi username jeee "+user.getUsername());
        System.out.println("Stari username jeee "+user.getOldUsername());
        
        Long ids=pat.getId();
        System.out.println("id:"+ids);
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
        User u=userRepository.save(pat);

        Profile p=profileRepository.findOneByUsername(u.getOldUsername());
        System.out.println("biografijaa "+user.getBiography());


        p.setBiography(user.getBiography());
        p.setUsername(u.getUsername());
        p.setMessageFromUnfollowes(user.getMessageFromUnfollowes());
        p.setTagFromUnfollowers(user.getTagFromUnfollowers());
        p.setWebSite(user.getWebSite());
        p.setPrivateProfil(user.getPrivateProfil());
        
        Profile ppp=profileRepository.save(p);
        
        return u;
    }
	

	@Override
	public User LogIn(LoginDTO loginDTO) {
		loggedUser = FindUser(loginDTO.getUsername(), loginDTO.getPassword());
		if(loggedUser == null)
			return null;
		else
			return loggedUser;
	}
	
	public User FindUser(String username, String password)
	{
		System.out.println("Find user");
		for(User u : userRepository.findAll())
		{
			if(u.getUsername().equals(username) && u.getPassword().equalsIgnoreCase(password))
			{
				System.out.println("Nasao korisnika za logovanje!");
				return u;
			}
		}
		return null;
	}
	
	public User getLoogedIn() throws AccessDeniedException {

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
		user.setPassword(userInfo.getPassword());
		user.setLastPasswordResetDate(timeProvider.nowTimestamp());
		user.getUserAuthorities().add(authorityRepository.findByName(UserRoles.ROLE_KORISNIK));
		user.setName(userInfo.getName());
		user.setSurname(userInfo.getSurname());
		user.setEmail(userInfo.getEmail());
		user.setAddress(userInfo.getAddress());
		user.setCity(userInfo.getCity());
		user.setPhone(userInfo.getPhone());
		user.setCountry(userInfo.getCountry());
		user.setRole(Role.USER);

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
        return loggedUser; 
    }

	@Override
	public ArrayList<User> searchUsername(String username) {
		ArrayList<User> newList = new ArrayList<User>();
		for(User r : getAllUsers())
		{
			if((r.getUsername()).startsWith(username))
				newList.add(r);			
		}
		
		return newList;
	}

	public ArrayList<User> getAllUsers()
	{
		return (ArrayList<User>) userRepository.findAll();

	}

	/*public void setLoggedUser(User u)
	{
		loggedUser = u;
	}*/

	
}
