package com.hms.frontend.userInterface.welcome;

import com.hms.frontend.dto.login.LoginResponseDTO;
import com.hms.frontend.service.AuthService;
import com.hms.frontend.session.SessionManager;
import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.utils.JwtUtils;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private final MainFrame mainFrame;
    private JTextField userField;
    private JPasswordField passField;
    private JTextField emailField;

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

                LoginResponseDTO response = AuthService.login(username, password);

                SwingUtilities.invokeLater(() -> {

                    loginBtn.setEnabled(true);

                    if (response == null) {
                        JOptionPane.showMessageDialog(mainFrame, "Something went wrong. Please try again.");
                        return;
                    }
                    String token = response.getToken();

                    if (token != null) {
                        SessionManager.jwtToken = token;

                        try {
                            String role = JwtUtils.extractRole(token);
                            Long userId = JwtUtils.extractUserId(token);

                            SessionManager.role = role;
                            SessionManager.userId = userId;

                            if ("ROLE_ADMIN".equals(role)) {
                                mainFrame.showAdminDashboard();
                            } else {
                                mainFrame.showPatientDashboard();
                            }
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(mainFrame, "Something went wrong");
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                mainFrame,
                                response.getMessage() != null ? response.getMessage() : "Login failed"
                        );
                    }
                });

            }).start();
        });
    }
}
