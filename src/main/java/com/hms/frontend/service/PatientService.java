package com.hms.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.utils.ApiUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PatientService {
    private ObjectMapper mapper = new ObjectMapper();

    public PatientsDTO getPatientDetails(Long patientId) {
        try {
            URL url = new URL("http://localhost:8080/patients/fetch/patient-details/" + patientId);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Add JWT header
            ApiUtil.addJwtHeader(conn);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.readValue(response.toString(), PatientsDTO.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public List<PatientAppointmentsDTO> getAppointments(Long userId) {
//        try {
//            URL url = new URL("http://localhost:8080/patients/appointments/patientId/" + userId);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//
//            // Add JWT header
//            ApiUtil.addJwtHeader(conn);
//
//            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//            StringBuilder response = new StringBuilder();
//            String line;
//            while ((line = br.readLine()) != null) {
//                response.append(line);
//            }
//            br.close();
//
//            ObjectMapper mapper = new ObjectMapper();
//            PatientAppointmentsDTO[] arr = mapper.readValue(response.toString(), PatientAppointmentsDTO[].class);
//            return Arrays.asList(arr);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
