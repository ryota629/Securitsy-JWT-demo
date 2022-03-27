package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.domain.User;

public interface UserRepo extends JpaRepository<User,Long>{
	User findByUsername(String username);
}
