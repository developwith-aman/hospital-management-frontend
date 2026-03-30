package com.hms.frontend.session;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SessionManager {

    public static String jwtToken;
    public static String role;
    public static Long userId;
    public static Long patientId;
}
