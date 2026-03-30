package com.hms.frontend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JwtUtils {

    public static String extractRole(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("Role").asString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long extractUserId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("UserId").asLong();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long extractPatientId(String token) {
        try {
            DecodedJWT decodedJWT = JWT.decode(token);
            return decodedJWT.getClaim("PatientId").asLong();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
