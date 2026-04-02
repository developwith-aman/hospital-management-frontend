package com.hms.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hms.frontend.api.ApiClient;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.utils.ApiUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PatientService {
    private ObjectMapper mapper = new ObjectMapper();

    public PatientsDTO getPatientDetails(Long patientId) {

        return ApiClient.getWithToken(
                "/patients/fetch/patient-details/" + patientId,
                PatientsDTO.class
        );
    }
}
