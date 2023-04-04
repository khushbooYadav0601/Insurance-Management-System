package com.insuranceManagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.insuranceManagement.models.PolicyRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "policies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Policy {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "policy_number", nullable = false)
    private String policyNumber;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "coverage_amount", nullable = false)
    private Double coverageAmount;

    @Column(name = "premium", nullable = false)
    private Double premium;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Constructors, getters, and setters
    public Policy(PolicyRequest request) {
    	this.policyNumber=request.getPolicyNumber();
    	this.type=request.getType();
    	this.coverageAmount=request.getCoverageAmount();
    	this.premium=request.getPremium();
    	this.startDate=request.getStartDate();
    	this.endDate=request.getEndDate();
    }
    
}






	


