package com.example.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableZuulProxy
public class ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplication.class, args);
	}

	@Bean
	public AuthFilter authenticationFilter() {
		return new AuthFilter();
	}

}
/*@Bean
public WebMvcConfigurer corsConfigurer() {
	return new WebMvcConfigurer() {
		
		public void addCorsMapping(CorsRegistry registry) {
			registry.addMapping("/**").allowedOrigins("*");
		}
	};
}*/
