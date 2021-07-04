package com.example.mediamicroservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Profile {


	  
			@Id
			@Column
			@GeneratedValue(strategy = GenerationType.AUTO)
			private int id;
		 		
			@Column(name="username")
			private String username;
			
			@Column
		    private Boolean privateProfil;

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
			
			
		    
		   
		    
	//lajkovani postovi
	//dislajkovani psotovi
		
	    
}
