package com.example.profilemicroservice.model;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.example.profilemicroservice.enums.Role;

@Entity
@Table(name = "profile")
public class Profile{
	   
		@Id
		@Column
		@GeneratedValue(strategy = GenerationType.AUTO)
		private int id;
	 		
		@Column(name="username")
		private String username;
		
		@Column
	    private Boolean privateProfil;
	    
	    @Column
	    private Boolean messageFromUnfollowes;
	    
	    @Column
	    private Boolean tagFromUnfollowers;

	    @Column 
	    private String biography;
	    @Column
	    private String webSite;
	    
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
	    
	    
	    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JoinTable( name = "request4Follow", joinColumns = @JoinColumn(name="id", nullable = true,referencedColumnName="id"), inverseJoinColumns = @JoinColumn(name = "following", referencedColumnName = "id"))    
	    @Column
	    private Set<Profile> request4Follow = new HashSet<Profile>();

		public Profile() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		public Profile(String username, Boolean privateProfil, Boolean messageFromUnfollowes,
				Boolean tagFromUnfollowers, String biography, String webSite, Set<Profile> closeFriends,
				Set<Profile> blockedProfiles, Set<Profile> mutedProfiles, Set<Profile> followers,
				Set<Profile> following, Set<Profile> request4Follow) {
			super();
			this.username = username;
			this.privateProfil = privateProfil;
			this.messageFromUnfollowes = messageFromUnfollowes;
			this.tagFromUnfollowers = tagFromUnfollowers;
			this.biography = biography;
			this.webSite = webSite;
			this.closeFriends = closeFriends;
			this.blockedProfiles = blockedProfiles;
			this.mutedProfiles = mutedProfiles;
			this.followers = followers;
			this.following = following;
			this.request4Follow = request4Follow;
		}
		
	public Profile(String username, Boolean privateProfil, Boolean messageFromUnfollowes,
			Boolean tagFromUnfollowers, String biography, String webSite)
	{
		this.username = username;
		this.privateProfil = privateProfil;
		this.messageFromUnfollowes = messageFromUnfollowes;
		this.tagFromUnfollowers = tagFromUnfollowers;
		this.biography = biography;
		this.webSite = webSite;
		this.closeFriends = null;
		this.blockedProfiles = null;
		this.request4Follow = null;
		this.mutedProfiles = null;
		this.followers = null;
		this.following = null;
	}
	@Override
	public String toString() {
		return "Profile [id=" + id + ", username=" + username + ", privateProfil=" + privateProfil
				+ ", messageFromUnfollowes=" + messageFromUnfollowes + ", tagFromUnfollowers=" + tagFromUnfollowers
				+ ", biography=" + biography + ", webSite=" + webSite + ", closeFriends=" + closeFriends
				+ ", blockedProfiles=" + blockedProfiles + ", mutedProfiles=" + mutedProfiles + ", followers="
				+ followers + ", following=" + following + "]";
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public Boolean getPrivateProfil() {
		return privateProfil;
	}


	public void setPrivateProfil(Boolean privateProfil) {
		this.privateProfil = privateProfil;
	}


	public Boolean getMessageFromUnfollowes() {
		return messageFromUnfollowes;
	}


	public void setMessageFromUnfollowes(Boolean messageFromUnfollowes) {
		this.messageFromUnfollowes = messageFromUnfollowes;
	}


	public Boolean getTagFromUnfollowers() {
		return tagFromUnfollowers;
	}


	public void setTagFromUnfollowers(Boolean tagFromUnfollowers) {
		this.tagFromUnfollowers = tagFromUnfollowers;
	}


	public String getBiography() {
		return biography;
	}


	public void setBiography(String biography) {
		this.biography = biography;
	}


	public String getWebSite() {
		return webSite;
	}


	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}


	public Set<Profile> getCloseFriends() {
		return closeFriends;
	}


	public void setCloseFriends(Set<Profile> closeFriends) {
		this.closeFriends = closeFriends;
	}


	public Set<Profile> getBlockedProfiles() {
		return blockedProfiles;
	}


	public void setBlockedProfiles(Set<Profile> blockedProfiles) {
		this.blockedProfiles = blockedProfiles;
	}


	public Set<Profile> getMutedProfiles() {
		return mutedProfiles;
	}


	public void setMutedProfiles(Set<Profile> mutedProfiles) {
		this.mutedProfiles = mutedProfiles;
	}


	public Set<Profile> getFollowers() {
		return followers;
	}


	public void setFollowers(Set<Profile> followers) {
		this.followers = followers;
	}


	public Set<Profile> getFollowing() {
		return following;
	}


	public void setFollowing(Set<Profile> following) {
		this.following = following;
	}


	public Set<Profile> getRequest4Follow() {
		return request4Follow;
	}


	public void setRequest4Follow(Set<Profile> request4Follow) {
		this.request4Follow = request4Follow;
	}


		/*public Profile(Role role, String name, String surname, String address, String city, String country,
				String email, String phone, String password, String username) {
			super(role, name, surname, address, city, country, email, phone, password, username);
			// TODO Auto-generated constructor stub
		}

		public Profile(String name, String surname, String address, String city, String country, String email,
				String phone, String password, List<Authority> authorities, String username) {
			super(name, surname, address, city, country, email, phone, password, authorities, username);
			// TODO Auto-generated constructor stub
		}*/
        
	    
//lajkovani postovi
//dislajkovani psotovi
	
	
}
