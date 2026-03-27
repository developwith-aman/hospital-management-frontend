package com.hms.frontend.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAppointmentsDTO {

    private int numberOfAppointments;
    private int doctorId;
    private String reason;
}
