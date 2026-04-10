package com.hms.frontend.service;

import com.hms.frontend.api.ApiClient;
import com.hms.frontend.api.ApiResponse;
import com.hms.frontend.dto.department.AddNewDepartmentDTO;
import com.hms.frontend.dto.department.DepartmentDTO;
import com.hms.frontend.dto.department.DoctorDepartmentDTO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentService {

    public DepartmentDTO addNewDepartment(AddNewDepartmentDTO newDepartmentDTO) {

        return ApiClient.postWithToken(
                "/department/add",
                newDepartmentDTO,
                DepartmentDTO.class
        );
    }

    public List<DepartmentDTO> getAllDepartments() {

        DepartmentDTO[] departmentResponse = ApiClient.getWithToken(
                "/department/show",
                DepartmentDTO[].class
        );
        if (departmentResponse == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(departmentResponse);
    }

    public ApiResponse addDoctorToDepartment(DoctorDepartmentDTO doctorDepartmentDTO) {

        ApiResponse response = ApiClient.postWithToken(
                "/department/add/doctor",
                doctorDepartmentDTO,
                ApiResponse.class
        );

        if (response == null) {
            throw new RuntimeException("Server is unreachable or returned no data!");
        }

        String responseMessage = response.getResponseMessage();
        if (responseMessage.contains("No") ||
                responseMessage.contains("not found") ||
                responseMessage.contains("already present")) {
            throw new IllegalArgumentException(responseMessage);
        }
        return response;
    }
}
