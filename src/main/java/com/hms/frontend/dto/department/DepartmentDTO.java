package com.hms.frontend.dto.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {

    private Long departmentID;
    private String departmentCode;
    private String departmentName;
    private int headDoctorID;
    private String headDoctorName;

}
