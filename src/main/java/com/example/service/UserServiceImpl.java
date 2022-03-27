package com.example.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.RoleRepo;
import com.example.repository.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService,UserDetailsService{
	
	private final UserRepo userRepo;
	private final RoleRepo roleRepo;
	private final PasswordEncoder passwordEncoder; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUsername(username);
		if(user == null) {
			log.error("User not found in the database");
			throw new UsernameNotFoundException("User not found in the database");
		}else {
			log.info("User found in the database: {}",username);
		}
		Collection<SimpleGrantedAuthority>  authorities = new ArrayList<>();
		user.getRoles().forEach(role -> {authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
	}

	@Override
	public User saveUser(User user) {
		log.info("saving new use {} to the database",user.getName());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public Role saveRole(Role role) {
		log.info("saving new role {} to the database",role.getName());
		return roleRepo.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		log.info("Adding role {} to user {}",roleName,userName);
		User user = userRepo.findByUsername(userName);
		Role role = roleRepo.findByName(roleName);
		user.getRoles().add(role);	
	}

	@Override
	public User getUser(String username) {
		log.info("Fetching user {}",username);
		return userRepo.findByUsername(username);
	}

	@Override
	public List<User> getUser() {
		log.info("Fetching all user");
		return userRepo.findAll();
	}

	
	
	

}
