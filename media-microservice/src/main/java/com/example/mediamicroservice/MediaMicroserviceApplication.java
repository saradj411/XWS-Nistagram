package com.example.mediamicroservice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
//@SpringBootApplication
@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
public class MediaMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediaMicroserviceApplication.class, args);
	}

}
