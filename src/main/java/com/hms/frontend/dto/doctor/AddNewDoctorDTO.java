package com.hms.frontend.dto.doctor;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class AddNewDoctorDTO {

    private String doctorName;
    private String specialization;
    private String email;
    private double consultationFee;
}
