package com.hms.frontend.userInterface;

import com.hms.frontend.dto.LoginResponseDTO;
import com.hms.frontend.service.AuthService;
import com.hms.frontend.session.SessionManager;

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
        emailField = new JTextField();

        setLayout(null);

        JLabel userNameLabel = new JLabel("Username: ");
        userNameLabel.setBounds(200, 150, 100, 30);
        userNameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        userNameLabel.setForeground(Color.BLACK);
        add(userNameLabel);

        userField.setBounds(300, 150, 250, 30);
        userField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(userField);

        JLabel emailLabel = new JLabel("Email id: ");
        emailLabel.setBounds(200, 200, 100, 30);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        emailLabel.setForeground(Color.BLACK);
        add(emailLabel);

        emailField.setBounds(300, 200, 250, 30);
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(emailField);

        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(200, 250, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passLabel.setForeground(Color.BLACK);
        add(passLabel);

        passField.setBounds(300, 250, 250, 30);
        passField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 300, 100, 30);
        loginBtn.setFont(new Font("serif", Font.BOLD, 15));
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setForeground(Color.BLACK);
        add(loginBtn);

        setBackground(Color.PINK); // main background

        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            loginBtn.setEnabled(false);

            new Thread(() -> {

                LoginResponseDTO response = AuthService.login(username, password);

                SwingUtilities.invokeLater(() -> {
                    loginBtn.setEnabled(true);

                    if (response != null && response.getToken() != null) {
                        SessionManager.jwtToken = response.getToken();
                        SessionManager.role = response.getRole();

                        if (response.getRole().equals("ROLE_ADMIN")) {
                            mainFrame.showAdminDashboard();
                        } else {
                            mainFrame.showPatientDashboard();
                        }

                    } else {
                        String msg = (response != null)
                                ? response.getMessage()
                                : "Server Error";
                        JOptionPane.showMessageDialog(this, msg);
                    }
                });
            }).start();
        });
    }
}
