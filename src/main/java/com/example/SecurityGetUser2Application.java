package com.example;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.service.UserService;

@SpringBootApplication
public class SecurityGetUser2Application {

	public static void main(String[] args) {
		SpringApplication.run(SecurityGetUser2Application.class, args);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_SUPER_ADMIN"));
			
			userService.saveUser(new User(null,"Jon Travolta","john","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Will Smith","will","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Jim Carry","jim","1234",new ArrayList<>()));
			userService.saveUser(new User(null,"Arnold Schwarzengger","arnold","1234",new ArrayList<>()));

			userService.addRoleToUser("john", "ROLE_USER");
			userService.addRoleToUser("will", "ROLE_MANAGER");
			userService.addRoleToUser("jim", "ROLD_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_SUPER_ADMIN");
			userService.addRoleToUser("arnold", "ROLD_ADMIN");
			userService.addRoleToUser("arnold", "ROLE_USER");
		};
	}
}
