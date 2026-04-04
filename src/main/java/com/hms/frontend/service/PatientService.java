package com.hms.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hms.frontend.api.ApiClient;
import com.hms.frontend.dto.patient.AddNewPatientDTO;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.utils.ApiUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientService {
    private ObjectMapper mapper = new ObjectMapper();

    public PatientsDTO getPatientDetails(Long patientId) {
        return ApiClient.getWithToken(
                "/patients/fetch/patient-details/" + patientId,
                PatientsDTO.class
        );
    }

    public PatientsDTO addNewPatient(AddNewPatientDTO newPatientDTO) {
        return ApiClient.postWithToken(
                "/patients/add/new/patient",
                newPatientDTO,
                PatientsDTO.class
        );
    }

    public List<PatientsDTO> getAllPatients() {
        PatientsDTO[] patientsArray = ApiClient.getWithToken(
                "/patients/all/patients",
                PatientsDTO[].class);

//        If it's null, return an empty list to avoid crashes
        if (patientsArray == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(patientsArray);
    }
}
