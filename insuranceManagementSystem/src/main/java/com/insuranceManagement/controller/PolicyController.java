package com.insuranceManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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
import com.insuranceManagement.entity.Policy;
import com.insuranceManagement.models.PolicyRequest;
import com.insuranceManagement.repository.ClientRepository;
import com.insuranceManagement.repository.PolicyRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/policies")
@AllArgsConstructor
public class PolicyController {

	    private PolicyRepository policyRepository;
	    private ClientRepository clientRepository;
	    

	    @GetMapping
	    public List<Policy> getAllPolicies() {
	        return policyRepository.findAll();
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<?> getPolicyById(@PathVariable Long id) {
	        Optional<Policy> policyOptional = policyRepository.findById(id);
	        return policyOptional.map(ResponseEntity::ok)
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    @PostMapping
	    public ResponseEntity<?> createPolicy(@RequestBody PolicyRequest policy) {
	    	System.out.println(policy);
	    	Client client = clientRepository.findById(policy.getClientId()).get();
	    	Policy policy1=new Policy(policy);
	    	policy1.setClient(client);
	        Policy createdPolicy = policyRepository.save(policy1);
	       
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdPolicy);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Policy> updatePolicy(@PathVariable Long id, @RequestBody Policy policy) {
	        Optional<Policy> policyOptional = policyRepository.findById(id);
	        if (policyOptional.isPresent()) {
	            Policy updatedPolicy = policyRepository.save(policy);
	            return ResponseEntity.ok(updatedPolicy);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Object> deletePolicy(@PathVariable Long id) {
	        Optional<Policy> policyOptional = policyRepository.findById(id);
	        if (policyOptional.isPresent()) {
	            policyRepository.delete(policyOptional.get());
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
