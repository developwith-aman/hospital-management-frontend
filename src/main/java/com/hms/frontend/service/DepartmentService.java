package com.hms.frontend.service;

import com.hms.frontend.api.ApiClient;
import com.hms.frontend.dto.department.AddNewDepartmentDTO;
import com.hms.frontend.dto.department.DepartmentDTO;

public class DepartmentService {

    public DepartmentDTO addNewDepartment(AddNewDepartmentDTO newDepartmentDTO) {

        return ApiClient.postWithToken(
                "/department/add",
                newDepartmentDTO,
                DepartmentDTO.class
        );
    }
}
