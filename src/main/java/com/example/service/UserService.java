package com.example.service;

import java.util.List;

import com.example.domain.Role;
import com.example.domain.User;

public interface UserService {
	User saveUser(User user);
	Role saveRole(Role role);
	void addRoleToUser(String username,String roleName);
	User getUser(String username);
	List<User> getUser();
	
}
