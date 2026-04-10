package com.hms.frontend.dto.staff;

import com.hms.frontend.dto.enums.StaffDepartments;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffDTO {

    private Long staffID;
    private String staffMemberName;
    private int age;
    private double salary;
    private StaffDepartments staffDepartment;
}
