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
        goLoginBtn.setBounds(170, 200, 100, 30);
        goLoginBtn.setBackground(new Color(100, 149, 237)); // Cornflower blue
        goLoginBtn.setForeground(Color.WHITE);
        goLoginBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(goLoginBtn);

        goLoginBtn.addActionListener(e -> mainFrame.showLogin());

        // ===== SIGNUP PROMPT =====
        JLabel signupHeader = new JLabel("New user? Create an account below");
        signupHeader.setBounds(400, 150, 300, 30);
        signupHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(signupHeader);

        JButton goSignupBtn = new JButton("Signup");
        goSignupBtn.setBounds(470, 200, 100, 30);
        goSignupBtn.setBackground(new Color(34, 139, 34)); // Forest green
        goSignupBtn.setForeground(Color.WHITE);
        goSignupBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        add(goSignupBtn);

        setBackground(Color.CYAN);

        goSignupBtn.addActionListener(e -> mainFrame.showSignup());
    }

}
