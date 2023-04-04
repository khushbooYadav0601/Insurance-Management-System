package com.insuranceManagement.entity;

import java.time.LocalDate;

import javax.persistence.*;

import com.insuranceManagement.models.ClaimRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "claims")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Claim {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "claim_number", nullable = false)
    private String claimNumber;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "claim_date", nullable = false)
    private LocalDate claimDate;

    @Column(name = "claim_status", nullable = false)
    private String claimStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "policy_id", nullable = false)
    private Policy policy;

    public Claim(ClaimRequest request) {
    	this.claimDate=request.getClaimDate();
    	this.claimNumber=request.getClaimNumber();
    	this.claimStatus=request.getClaimStatus();
    	this.description=request.getDescription();
    }
    // Constructors, getters, and setters
}
