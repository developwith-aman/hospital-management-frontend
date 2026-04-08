package com.hms.frontend.dto.doctor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DoctorDTO {

    private int ID;
    private String doctorName;
    private String specialization;
    private String email;
    private double consultationFee;
}
