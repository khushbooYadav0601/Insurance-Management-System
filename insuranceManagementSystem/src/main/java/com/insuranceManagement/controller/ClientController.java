package com.insuranceManagement.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceManagement.entity.Client;
import com.insuranceManagement.repository.ClientRepository;

@RestController
@RequestMapping("/api/Clients")
public class ClientController {
	
	    @Autowired
	    private ClientRepository clientRepository;

	    // GET /api/clients: Fetch all clients
	    @GetMapping
	    public List<Client> getAllClients() {
	        return clientRepository.findAll();
	    }

	    // GET /api/clients/{id}: Fetch a specific client by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
	        Optional<Client> client = clientRepository.findById(id);
	        if (client.isPresent()) {
	            return ResponseEntity.ok(client.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // POST /api/clients: Create a new client
	    @PostMapping
	    public ResponseEntity<Client> createClient(@RequestBody Client newClient) {
	        Client savedClient = clientRepository.save(newClient);
	        return ResponseEntity.created(URI.create("/api/clients/" + savedClient.getId())).body(savedClient);
	    }

	    // PUT /api/clients/{id}: Update a client's information
	    @PutMapping("/{id}")
	    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client updatedClient) {
	        Optional<Client> existingClient = clientRepository.findById(id);
	        if (existingClient.isPresent()) {
	            updatedClient.setId(id);
	            Client savedClient = clientRepository.save(updatedClient);
	            return ResponseEntity.ok(savedClient);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    // DELETE /api/clients/{id}: Delete a client
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
	        Optional<Client> client = clientRepository.findById(id);
	        if (client.isPresent()) {
	            clientRepository.delete(client.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
