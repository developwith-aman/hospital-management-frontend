package com.hms.frontend.userInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {

    private final MainFrame mainFrame;
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

        userField.setBounds(300, 150, 150, 30);
        userField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(userField);

        JLabel passLabel = new JLabel("Password: ");
        passLabel.setBounds(200, 200, 100, 30);
        passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        passLabel.setForeground(Color.BLACK);
        add(passLabel);

        passField.setBounds(300, 200, 150, 30);
        passField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(300, 250, 100, 30);
        loginBtn.setFont(new Font("serif", Font.BOLD, 15));
        loginBtn.setBackground(Color.GREEN);
        loginBtn.setForeground(Color.BLACK);
        add(loginBtn);

        setBackground(new Color(41, 128, 185)); // main background

        loginBtn.addActionListener(e -> {

            String username = userField.getText();
            String password = new String(passField.getPassword());

            
            // switch screen
            mainFrame.showDashboard();
        });
    }
}
