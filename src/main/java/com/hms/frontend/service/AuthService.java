package com.hms.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hms.frontend.dto.login.LoginRequestDTO;
import com.hms.frontend.dto.login.LoginResponseDTO;
import com.hms.frontend.dto.signup.SignUpRequestDTO;
import com.hms.frontend.dto.signup.SignUpResponseDTO;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
            outputStream.write(jsonInput.getBytes((StandardCharsets.UTF_8)));
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

    public static SignUpResponseDTO signup(String username, String email, String password) {
        try {
            URL url = new URL("http://localhost:8080/auth/signup");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();

            SignUpRequestDTO requestDTO = new SignUpRequestDTO();
            requestDTO.setUsername(username);
            requestDTO.setEmail(email);
            requestDTO.setPassword(password);

            String jsonInput = mapper.writeValueAsString(requestDTO);

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == 201) {
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
                        SignUpResponseDTO.class
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}