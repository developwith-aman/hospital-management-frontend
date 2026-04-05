package com.hms.frontend.dto.patient;


import com.hms.frontend.dto.insurance.InsuranceDTO;
import com.hms.frontend.dto.enums.BloodGroups;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewPatientDTO {

    private String patientName;
    private String gender;
    private int age;
    private String email;
    private BloodGroups bloodGroup;
    private InsuranceDTO insurance;
    private double payableAmount;
}
