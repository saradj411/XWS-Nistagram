package com.example.profilemicroservice.model;


import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Notification {
	
	@Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idNotification;

    @Column
    private String text;

    @ManyToOne
    @JoinColumn(name = "username", referencedColumnName = "id")
    //@JsonManagedReference
    private Profile username;
    
    @Column
    private LocalDate date;
    
    @Column 
    @Enumerated(EnumType.STRING)
    private NotificationType type;

}
