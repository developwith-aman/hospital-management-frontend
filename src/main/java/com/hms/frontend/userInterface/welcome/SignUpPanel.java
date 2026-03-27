package com.hms.frontend.userInterface.welcome;

import com.hms.frontend.dto.login.LoginResponseDTO;
import com.hms.frontend.dto.signup.SignUpResponseDTO;
import com.hms.frontend.service.AuthService;
import com.hms.frontend.userInterface.MainFrame;

import javax.swing.*;
import java.awt.*;

public class SignUpPanel extends JPanel {

    private MainFrame mainFrame;
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton signupBtn;

    public SignUpPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);

        usernameField = new JTextField();
        passwordField = new JPasswordField(15);
        emailField = new JTextField();
        confirmPasswordField = new JPasswordField(15);
        signupBtn = new JButton();

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(200, 150, 100, 30);
        userLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        userLabel.setForeground(Color.BLACK);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(300, 150, 250, 30);
        add(usernameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 200, 100, 30);
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        emailLabel.setForeground(Color.BLACK);
        add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(300, 200, 250, 25);
        add(emailField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 250, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passLabel.setForeground(Color.BLACK);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(300, 250, 250, 25);
        add(passwordField);

        signupBtn = new JButton("Signup");
        signupBtn.setBounds(300, 300, 100, 30);
        signupBtn.setFont(new Font("serif", Font.BOLD, 18));
        signupBtn.setBackground(Color.GREEN);
        signupBtn.setForeground(Color.BLACK);
        add(signupBtn);

        JButton cancelBtn = new JButton("Cancel");
        cancelBtn.setBounds(450, 300, 100, 30);
        cancelBtn.setFont(new Font("serif", Font.BOLD, 18));
        cancelBtn.setBackground(Color.GREEN);
        cancelBtn.setForeground(Color.RED);
        add(cancelBtn);

        cancelBtn.addActionListener(e -> {
            mainFrame.showWelcome();
        });

        setBackground(Color.PINK);

        signupBtn.addActionListener(e -> {

            String username = usernameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            new Thread(() -> {
                SignUpResponseDTO response = AuthService.signup(username, email, password);

                if (response != null) {
                    String message = "User name: " + response.getUsername() +
                            " is successfully registered.\nNow you can proceed to login.";

                    JOptionPane.showMessageDialog(mainFrame, message);
                    mainFrame.showLogin();
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Signup failed");
                }
            }).start();
        });
    }
}
