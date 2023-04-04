package com.insuranceManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insuranceManagement.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
