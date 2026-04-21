package com.hms.frontend.dto.doctor;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewDoctorDTO {

    private String doctorName;
    private String specialization;
    private String email;
    private double consultationFee;
}
