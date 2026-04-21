package com.hms.frontend.session;


import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.utils.JwtUtils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;

@Setter
@Getter
public class SessionManager {

    public static String jwtToken;
    public static String role;
    public static Long userId;
    public static Long patientId;

    public static boolean checkSession(MainFrame mainFrame) {
        String token = jwtToken;

        if (JwtUtils.isTokenExpired(token)) {
            jwtToken = null;
            role = null;

            JOptionPane.showMessageDialog(mainFrame, "Your session has ended. Please login again.");
            mainFrame.showLogin();
            return false;
        }
        return true;
    }
}
