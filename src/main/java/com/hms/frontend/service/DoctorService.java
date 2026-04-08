package com.hms.frontend.service;

import com.hms.frontend.api.ApiClient;
import com.hms.frontend.dto.appointment.AppointmentDTO;
import com.hms.frontend.dto.doctor.AddNewDoctorDTO;
import com.hms.frontend.dto.doctor.DoctorDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DoctorService {

    public DoctorDTO addNewDoctor(AddNewDoctorDTO newDoctorDTO) {
        return ApiClient.postWithToken(
                "/doctors/add",
                newDoctorDTO,
                DoctorDTO.class
        );
    }

    public AppointmentDTO reassignAppointment(int doctorId, Long appointmentId) {

        String endpoint = String.format("/doctors/reassign-appointment/%d/%d", doctorId, appointmentId);
        try {
            return ApiClient.patchWithToken(
                    endpoint,
                    null,
                    AppointmentDTO.class
            );
        }catch (Exception e) {
            throw new IllegalArgumentException("Invalid ID entered");
        }
    }

    public List<AppointmentDTO> getAllAppointments(int doctorId) {

        AppointmentDTO[] responseArray =  ApiClient.getWithToken(
                "/doctors/get/appointments/" + doctorId,
                AppointmentDTO[].class
        );

        if (responseArray == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(responseArray);
    }
}
