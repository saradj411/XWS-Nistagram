package com.example.profilemicroservice.dto;


import java.util.List;
import java.util.stream.Collectors;

import com.example.profilemicroservice.model.Authority;
import com.example.profilemicroservice.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDTO {
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<String> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<String> authorities) {
		this.authorities = authorities;
	}

	public UserTokenDTO getToken() {
		return token;
	}

	public void setToken(UserTokenDTO token) {
		this.token = token;
	}

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDTO(Long id, String username, String name, String email, String address, boolean enabled,
			List<String> authorities, UserTokenDTO token) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.email = email;
		this.address = address;
		this.enabled = enabled;
		this.authorities = authorities;
		this.token = token;
	}

	private Long id;
    private String username;
    private String name;
    private String email;
    private String address;
    private boolean enabled;
    private List<String> authorities;
    private UserTokenDTO token;

    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.enabled = user.isEnabled();
        this.token = null;

        this.authorities =user.getAuthorities().stream()
        		.map(authority->((Authority) authority).getName()).collect(Collectors.toList());
    }

}
