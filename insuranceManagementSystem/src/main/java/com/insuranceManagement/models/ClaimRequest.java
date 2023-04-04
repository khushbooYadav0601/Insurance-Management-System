package com.insuranceManagement.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClaimRequest {
	private String claimNumber;

    private String description;

    private LocalDate claimDate;

    private String claimStatus;
    private long policyId;
}
