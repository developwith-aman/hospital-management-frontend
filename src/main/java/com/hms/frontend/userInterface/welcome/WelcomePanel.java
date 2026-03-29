package com.hms.frontend.userInterface.welcome;

import com.hms.frontend.userInterface.MainFrame;

import javax.swing.*;
import java.awt.*;
public class WelcomePanel extends JPanel {
    private MainFrame mainFrame;

    public WelcomePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        // ===== LOGIN PROMPT =====
        JLabel loginHeader = new JLabel("Already have an account? Login below");
        loginHeader.setBounds(100, 150, 300, 30);
        loginHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(loginHeader);

        JButton goLoginBtn = new JButton("Login");
        goLoginBtn.setFocusPainted(false);
        goLoginBtn.setBounds(180, 200, 100, 30);
        goLoginBtn.setBackground(new Color(100, 149, 237)); // Cornflower blue
        goLoginBtn.setForeground(Color.WHITE);
        goLoginBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(goLoginBtn);

        goLoginBtn.addActionListener(e -> mainFrame.showLogin());

        // ===== SIGNUP PROMPT =====
        JLabel signupHeader = new JLabel("New user? Create an account below");
        signupHeader.setBounds(410, 150, 300, 30);
        signupHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(signupHeader);

        JButton goSignupBtn = new JButton("Signup");
        goSignupBtn.setBounds(480, 200, 100, 30);
        goSignupBtn.setBackground(new Color(34, 139, 34)); // Forest green
        goSignupBtn.setForeground(Color.WHITE);
        goSignupBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
        add(goSignupBtn);

        goSignupBtn.addActionListener(e -> mainFrame.showSignup());

        // Exit Button
        JButton goExitBtn = new JButton("EXIT");
        goExitBtn.setBounds(330, 270, 100, 30);
        goExitBtn.setBackground(Color.RED);
        goExitBtn.setForeground(Color.white);
        goExitBtn.setFont(new Font("Tahoma", Font.BOLD, 20));
        add(goExitBtn);

        goExitBtn.addActionListener(e -> System.exit(0));

        setBackground(Color.CYAN);
    }

}
