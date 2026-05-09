package com.hms.frontend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

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

    public static boolean isTokenExpired(String token) {
        try {
            Date currentTime = new Date();  // current time

            if (token == null) return true;

            DecodedJWT decodedJWT = JWT.decode(token);
            Date expirationTime = decodedJWT.getExpiresAt(); // fetching expiration time from the token

            if (currentTime.after(expirationTime)) return true;
        } catch (JWTDecodeException decodeException) {
            decodeException.printStackTrace();
            return true;
        }
        return false;
    }
}
