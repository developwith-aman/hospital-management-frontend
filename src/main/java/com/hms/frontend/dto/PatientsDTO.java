package com.hms.frontend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PatientsDTO {

    private Long patientID;
    private String patientName;
    private String gender;
    private int age;
    private String email;
    private String bloodGroup;
    private boolean insured;
    private LocalDateTime arrivalTime;
    private int numberOfAppointments;
    private double payableAmount;
}
