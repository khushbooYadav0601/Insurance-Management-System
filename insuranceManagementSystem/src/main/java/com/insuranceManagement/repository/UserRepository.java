package com.insuranceManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
}
