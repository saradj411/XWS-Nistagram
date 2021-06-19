package com.example.profilemicroservice.model;


import javax.persistence.*;

@Entity
public class RequestForFollow {

	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFollowRequest;

    @Column
    private Boolean accepted;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "id")
    //@JsonManagedReference
    private Profile username;
}
