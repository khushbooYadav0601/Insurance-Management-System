package com.insuranceManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

	
}