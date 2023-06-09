package com.insuranceManagement.controller;

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

import com.insuranceManagement.entity.Claim;
import com.insuranceManagement.models.ClaimRequest;
import com.insuranceManagement.repository.ClaimRepository;
import com.insuranceManagement.repository.PolicyRepository;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/claims")
@AllArgsConstructor
public class ClaimController {

    private ClaimRepository claimRepository;
    
    private PolicyRepository policyRepository;

    @GetMapping
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaimById(@PathVariable(value = "id") Long claimId) {
        Optional<Claim> claim = claimRepository.findById(claimId);
        if (claim.isPresent()) {
            return ResponseEntity.ok().body(claim.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Claim createClaim(@RequestBody ClaimRequest request) {
    	Claim claim= new Claim(request);
    	claim.setPolicy(policyRepository.findById(request.getPolicyId()).get());
        return claimRepository.save(claim);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Claim> updateClaim(@PathVariable(value = "id") Long claimId, @RequestBody Claim claimDetails) {
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claim.setClaimNumber(claimDetails.getClaimNumber());
            claim.setDescription(claimDetails.getDescription());
            claim.setClaimDate(claimDetails.getClaimDate());
            claim.setClaimStatus(claimDetails.getClaimStatus());
            claim.setPolicy(claimDetails.getPolicy());
            return ResponseEntity.ok(claimRepository.save(claim));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Claim> deleteClaim(@PathVariable(value = "id") Long claimId) {
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claimRepository.delete(claim);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
