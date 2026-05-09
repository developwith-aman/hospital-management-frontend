package com.hms.frontend.userInterface.welcome;

import com.hms.frontend.dto.login.LoginResponseDTO;
import com.hms.frontend.service.AuthService;
import com.hms.frontend.session.SessionManager;
import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.utils.JwtUtils;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextField userField;
    private JPasswordField passField;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        userField = new JTextField();
        passField = new JPasswordField(15);

        setLayout(null);

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(200, 150, 100, 30);
        userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        userNameLabel.setForeground(Color.BLACK);
        add(userNameLabel);

        userField.setBounds(300, 150, 250, 30);
        userField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(userField);

        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(200, 200, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passLabel.setForeground(Color.BLACK);
        add(passLabel);

        passField.setBounds(300, 200, 250, 30);
        passField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 250, 100, 30);
        loginBtn.setFont(new Font("serif", Font.BOLD, 18));
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setForeground(Color.BLACK);
        add(loginBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(450, 250, 100, 30);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 18));
        cancelBtn.setBackground(Color.GREEN);
        cancelBtn.setForeground(Color.RED);
        add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            mainFrame.showWelcome();
        });

        setBackground(Color.PINK); // main background

        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            loginBtn.setEnabled(false);

            new Thread(() -> {

                try {

                    LoginResponseDTO response = AuthService.login(username, password);

                    if (response != null && response.getToken() != null) {
                        String token = response.getToken();
                        String role = JwtUtils.extractRole(token);
                        Long userId = JwtUtils.extractUserId(token);

                        SessionManager.jwtToken = response.getToken();
                        SessionManager.role = role;
                        SessionManager.userId = userId;
                        SessionManager.patientId = response.getPatientId();

                        SwingUtilities.invokeLater(() -> {
                            loginBtn.setEnabled(true);

                            if ("ROLE_ADMIN".equals(role)) {
                                mainFrame.showAdminDashboard();
                            } else {
                                mainFrame.showPatientDashboard();
                            }
                        });
                    }
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {

                        loginBtn.setEnabled(true);

                        JOptionPane.showMessageDialog(
                                null,
                                ex.getMessage(),
                                "Login Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    });
                }
            }).start();
        });
    }
}
