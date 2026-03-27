package com.hms.frontend.utils;

import com.hms.frontend.session.SessionManager;

import java.net.HttpURLConnection;

public class ApiUtil {

    public static void addJwtHeader(HttpURLConnection conn) {
        String token = SessionManager.jwtToken;
        if (token != null && !token.isEmpty()) {
            conn.setRequestProperty("Authorization", "Bearer " + token);
        }
    }
}
