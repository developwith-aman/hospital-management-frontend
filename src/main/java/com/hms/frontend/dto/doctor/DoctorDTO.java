package com.hms.frontend.dto.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorDTO {

    private int ID;
    private String doctorName;
    private String specialization;
    private String email;
    private double consultationFee;
}
