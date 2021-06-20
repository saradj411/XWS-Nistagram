package com.example.profilemicroservice.model;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.profilemicroservice.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails{
	 @Id
	    @Column(name = "id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column
	    private String name;

	    @Column
	    private String surname;

	    @Column(name = "username", unique = true, nullable = false)
	    private String username;

	    @Column
	    private String address;

	    @Column
	    private String city;

	    @Column
	    private String country;

	    @Column
	    private String email;

	    @Column
	    private String phone;

	    @Column(name = "password", nullable = false)
	    private String password;
	    
	    @Column(name = "oldUsername")
	    private String oldUsername;
	    

	    public String getOldUsername() {
			return oldUsername;
		}

		public void setOldUsername(String oldUsername) {
			this.oldUsername = oldUsername;
		}

		@Column
	    private boolean isAdmin;

	    @Column(name = "enabled", nullable = false)
	    private boolean enabled;

	    @Column(name = "last_password_reset_date")
	    private Timestamp lastPasswordResetDate;
	    
	    @Column(name ="role")
	    private Role role;

	   
	    @JsonIgnore
	    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	    @JoinTable(name = "user_authority",
	    		joinColumns=@JoinColumn(name = "user_id", referencedColumnName = "id"),
	            inverseJoinColumns = @JoinColumn(name = "authority_id", referencedColumnName = "id"))
	    private List<Authority> authorities;

	    public User() {
	    }

	    public User(String name, String surname, String address, String city, String country, String email,
	                String phone, String password, List<Authority> authorities, String username) {
	    	this.role = Role.USER;
	        this.name = name;
	        this.surname = surname;
	        this.address = address;
	        this.city = city;
	        this.country = country;
	        this.email = email;
	        this.phone = phone;
	        this.password = password;
	        this.authorities = authorities;
	        this.username = username;
	    }
	    
	    public User(Role role, String name, String surname, String address, String city, String country, String email,
                String phone, String password, String username) {
	    	this.role = role;
	        this.name = name;
	        this.surname = surname;
	        this.address = address;
	        this.city = city;
	        this.country = country;
	        this.email = email;
	        this.phone = phone;
	        this.password = password;
	        this.username = username;
    }
	    

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

	    public String getPassword() {
	        return password;
	    }

	    public void setPassword(String password) {
	        Timestamp now = new Timestamp(new Date().getTime());
	        this.setLastPasswordResetDate( now );
	        this.password = password;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setAuthorities(List<Authority> authorities) {
	        this.authorities = authorities;
	    }

	    public List<Authority> getUserAuthorities() {
	        if (this.authorities == null) {
	            this.authorities = new ArrayList<>();
	        }

	        return this.authorities;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public Timestamp getLastPasswordResetDate() {
	        return lastPasswordResetDate;
	    }

	    public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
	        this.lastPasswordResetDate = lastPasswordResetDate;
	    }

	    @Override
	    public boolean isEnabled() {
	        return enabled;
	    }

	    public void setEnabled(boolean enabled) {
	        this.enabled = enabled;
	    }

	    @Override
	    public Collection<? extends GrantedAuthority> getAuthorities() {
	        return this.authorities;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }

	    @JsonIgnore
	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    public String getAddress() {
	        return address;
	    }

	    public String getSurname() {
	        return surname;
	    }

	    public void setSurname(String surname) {
	        this.surname = surname;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getCountry() {
	        return country;
	    }

	    public void setCountry(String country) {
	        this.country = country;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public void setPhone(String phone) {
	        this.phone = phone;
	    }

	    public Role getRole() {
			return role;
		}

		public void setRole(Role role) {
			this.role = role;
		}

		public boolean isAdmin() {
	        return isAdmin;
	    }

	    public void setAdmin(boolean admin) {
	        isAdmin = admin;
	    }

		@Override
		public String toString() {
			return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", username=" + username
					+ ", address=" + address + ", city=" + city + ", country=" + country + ", email=" + email
					+ ", phone=" + phone + ", password=" + password + ", isAdmin=" + isAdmin + ", enabled=" + enabled
					+ ", lastPasswordResetDate=" + lastPasswordResetDate + ", role=" + role + ", authorities="
					+ authorities + "]";
		}
	    
}
