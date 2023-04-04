package com.insuranceManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

}
