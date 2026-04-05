package com.hms.frontend.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hms.frontend.session.SessionManager;
import com.hms.frontend.utils.ApiUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // GET METHOD
    public static <T> T getWithToken(String endpoint, Class<T> responseType) {
        try {
            URL url = new URL(BASE_URL + endpoint);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Add JWT header
            ApiUtil.addJwtHeader(conn);

            int responseCode = conn.getResponseCode();
            if (responseCode == 403 || responseCode == 404) {
                return null;
            }

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
    public static <T, R> R postWithToken(String endpoint, T requestBody, Class<R> responseType) {
        try {
            URL url = new URL(BASE_URL + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ApiUtil.addJwtHeader(conn);

            String jsonInput = mapper.writeValueAsString(requestBody);

            OutputStream os = conn.getOutputStream();
            os.write(jsonInput.getBytes(StandardCharsets.UTF_8));
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();

            InputStream stream = (responseCode >= 200 && responseCode < 300)
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();
            return mapper.readValue(response.toString(), responseType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T, R> R patchWithToken(String endpoint, T requestBody, Class<R> responseType) {
        try {
            String url = BASE_URL + endpoint;

            String jsonInput = mapper.writeValueAsString(requestBody);

            HttpClient client = HttpClient.newHttpClient();

            String token = SessionManager.jwtToken;

            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonInput))
                    .header("Content-Type", "application/json");
            if (token != null && !token.isEmpty()) {
                builder.header("Authorization", "Bearer " + token);
            }

            HttpRequest request = builder.build();
            HttpResponse<String> response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                return null;
            }
            return mapper.readValue(response.body(), responseType);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <R> R deleteWithToken(String endpoint, Class<R> responseType) {
        try {
            String url = BASE_URL + endpoint;

            HttpClient client = HttpClient.newHttpClient();
            String token = SessionManager.jwtToken;

            HttpRequest.Builder builder = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .DELETE() // This specifies it's a DELETE request!
                    .header("Content-Type", "application/json");
            if (token != null && !token.isEmpty()) {
                builder.header("Authorization", "Bearer " + token);
            }

            HttpRequest request = builder.build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 400) {
                return null;
            }

            if (response.body() == null || response.body().trim().isEmpty()) {
                return null;
            }
            return mapper.readValue(response.body(), responseType);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
