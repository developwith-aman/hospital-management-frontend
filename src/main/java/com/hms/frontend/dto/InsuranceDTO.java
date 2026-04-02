package com.hms.frontend.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class InsuranceDTO {

    private String policyNumber;
    private String policyProvider;
    private LocalDateTime effectiveDate;
    private LocalDateTime expiryDate;

}
