package com.hms.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.frontend.api.ApiClient;
import com.hms.frontend.api.ApiResponse;
import com.hms.frontend.dto.staff.AddNewStaffMember;
import com.hms.frontend.dto.staff.StaffDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffService {
    private ObjectMapper mapper = new ObjectMapper();

    public StaffDTO addNewStaffMember(AddNewStaffMember newStaffMember) {

        return ApiClient.postWithToken(
                "/hospital/staff/add",
                newStaffMember,
                StaffDTO.class
        );
    }

    public List<StaffDTO> fetchAllStaff() {

        StaffDTO[] staffArray = ApiClient.getWithToken(
                "/hospital/staff/fetch",
                StaffDTO[].class
        );
        if (staffArray != null) {
            return Arrays.asList(staffArray);
        }
        return new ArrayList<>();
    }

    public ApiResponse deleteStaffMember(Long id) {

        ApiResponse response = ApiClient.deleteWithToken(
                "/hospital/delete/staff/" + id,
                ApiResponse.class
        );

        if (response == null) {
            throw new IllegalArgumentException("Staff member not found or Server Error.");
        }
        String msg = response.getResponseMessage().toLowerCase();
        if (msg.contains("not found") || msg.contains("error")) {
            throw new IllegalArgumentException(response.getResponseMessage());
        }
        return response;
    }
}
