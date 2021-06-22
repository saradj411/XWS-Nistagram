package com.example.profilemicroservice.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.profilemicroservice.enums.Role;

@Entity
public class Profile extends User{
	   
	    @Column
	    private Boolean privateProfil;
	    
	    @Column
	    private Boolean messageFromUnfollowes;
	    
	    @Column
	    private Boolean tagFromUnfollowers;
	    
	    @ManyToMany(cascade = {
	            CascadeType.PERSIST, 
	            CascadeType.MERGE
	        }, fetch = FetchType.LAZY)
	    @JoinTable( name = "closeFriends", joinColumns = @JoinColumn(name="id", nullable = true,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "idCloseFriend", referencedColumnName = "id"))
	    //@JsonBackReference
	    private Set<Profile> closeFriends = new HashSet<Profile>();

	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinTable( name = "blockedProfiles", joinColumns = @JoinColumn(name="id", nullable = true,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "idBlockedProfile", referencedColumnName = "id"))
	    //@JsonBackReference
	    private Set<Profile> blockedProfiles = new HashSet<Profile>();

	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinTable( name = "mutedProfiles", joinColumns = @JoinColumn(name="id", nullable = true,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "idMutedProfile", referencedColumnName = "id"))
	    //@JsonBackReference
	    private Set<Profile> mutedProfiles = new HashSet<Profile>();

	    
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinTable( name = "followers", joinColumns = @JoinColumn(name="id", nullable = true,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "follower", referencedColumnName = "id"))
	    //@JsonBackReference
	    private Set<Profile> followers = new HashSet<Profile>();

	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinTable( name = "following", joinColumns = @JoinColumn(name="id", nullable = true,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "following", referencedColumnName = "id"))
	    //@JsonBackReference
	    private Set<Profile> following = new HashSet<Profile>();

		public Profile() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Profile(Role role, String name, String surname, String address, String city, String country,
				String email, String phone, String password, String username) {
			super(role, name, surname, address, city, country, email, phone, password, username);
			// TODO Auto-generated constructor stub
		}

		public Profile(String name, String surname, String address, String city, String country, String email,
				String phone, String password, List<Authority> authorities, String username) {
			super(name, surname, address, city, country, email, phone, password, authorities, username);
			// TODO Auto-generated constructor stub
		}
        
	    
//lajkovani postovi
//dislajkovani psotovi
}
