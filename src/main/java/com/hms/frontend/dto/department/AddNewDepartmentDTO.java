package com.hms.frontend.dto.department;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddNewDepartmentDTO {

    private String departmentName;
    private int headDoctorId;

}
