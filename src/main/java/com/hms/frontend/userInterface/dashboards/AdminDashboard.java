package com.hms.frontend.userInterface;

import javax.swing.*;

public class AdminDashboard extends JPanel {

    private MainFrame mainFrame;

    public AdminDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);

        JLabel label = new JLabel("Welcome to Admin Dashboard");
        label.setBounds(300, 200, 200, 30);
        add(label);
    }
}
