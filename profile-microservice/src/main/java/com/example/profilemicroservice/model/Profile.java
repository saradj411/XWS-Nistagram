package com.example.profilemicroservice.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Profile extends User{
	   
	    @Column
	    private Boolean privateProfil;
	    
	    @Column
	    private Boolean messageFromUnfollowes;
	    
	    @Column
	    private Boolean tagFromUnfollowers;
	    
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

//lajkovani postovi
	    //dislajkovani psotovi
}
