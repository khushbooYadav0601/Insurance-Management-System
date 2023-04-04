package com.insuranceManagement.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PolicyRequest {
	private String policyNumber;

    private String type;

    private Double coverageAmount;

    private Double premium;

    private LocalDate startDate;

    private LocalDate endDate;

    private long clientId;
}
