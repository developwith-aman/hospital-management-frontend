package com.hms.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.frontend.dto.LoginRequestDTO;
import com.hms.frontend.dto.LoginResponseDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class AuthService {

    public static LoginResponseDTO login(String username, String password) {
        try {
            URL url = new URL("http://localhost:8080/auth/login");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            LoginRequestDTO requestDTO = new LoginRequestDTO(username, password);
            String jsonInput = mapper.writeValueAsString(requestDTO);

            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jsonInput.getBytes());
            outputStream.flush();
            outputStream.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();
                return mapper.readValue(
                        response.toString(),
                        LoginResponseDTO.class
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}