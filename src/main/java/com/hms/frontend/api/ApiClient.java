package com.hms.frontend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hms.frontend.utils.ApiUtil;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final ObjectMapper mapper = new ObjectMapper();

    // To get response
    public static <T> T getWithToken(String endpoint, Class<T> responseType) {
        try {
            URL url = new URL(BASE_URL + endpoint);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Add JWT header
            ApiUtil.addJwtHeader(conn);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream())
            );
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            return mapper.readValue(response.toString(), responseType);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // POST METHOD
    public static <T> T post(String endpoint, Object requestObj, Class<T> responseType) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Convert request to JSON
            String jsonInput = mapper.writeValueAsString(requestObj);

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200 || responseCode == 201) {

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(conn.getInputStream())
                );

                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                return mapper.readValue(response.toString(), responseType);
            } else {
                System.out.println("API Error: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // GET METHOD
    public static <T> T get(String endpoint, Class<T> responseType) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json");

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

                return mapper.readValue(response.toString(), responseType);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
