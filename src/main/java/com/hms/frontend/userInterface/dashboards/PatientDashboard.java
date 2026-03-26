package com.hms.frontend.userInterface.dashboards;

import com.hms.frontend.userInterface.MainFrame;

import javax.swing.*;

public class PatientDashboard extends JPanel {

    private MainFrame mainFrame;

    public PatientDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);

        JLabel label = new JLabel("Welcome to Patient Dashboard");
        label.setBounds(300, 200, 200, 30);
        add(label);
    }
}
