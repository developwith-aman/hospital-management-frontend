package com.hms.frontend.userInterface.dashboards;

import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.userInterface.panels.BillingPanel;
import com.hms.frontend.userInterface.panels.department.MainDepartmentPanel;
import com.hms.frontend.userInterface.panels.doctor.MainDoctorPanel;
import com.hms.frontend.userInterface.panels.staff.MainStaffPanel;
import com.hms.frontend.userInterface.panels.patient.MainPatientPanel;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JPanel {

    private MainFrame mainFrame;
    private JPanel contentPanel;

    public AdminDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout(10, 10));
        setBackground(new Color(255, 228, 225));

        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        topPanel.setBackground(new Color(255, 182, 193)); // slightly darker pink

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 5));
        buttonPanel.setOpaque(false);

        JButton patientBtn = createButton("Patients");
        patientBtn.addActionListener(e -> {
            switchPanel(new MainPatientPanel());
        });

        JButton doctorBtn = createButton("Doctors");
        doctorBtn.addActionListener(e -> {
            switchPanel(new MainDoctorPanel());
        });

        JButton deptBtn = createButton("Departments");
        deptBtn.addActionListener(e -> {
            switchPanel(new MainDepartmentPanel());
        });

        JButton staffBtn = createButton("Staff");
        staffBtn.addActionListener(e -> {
            switchPanel(new MainStaffPanel());
        });

        JButton billBtn = createButton("Billing");
        billBtn.addActionListener(e -> {
            switchPanel(new BillingPanel());
        });

        topPanel.add(patientBtn);
        topPanel.add(doctorBtn);
        topPanel.add(deptBtn);
        topPanel.add(staffBtn);
        topPanel.add(billBtn);

        // Center Panel
        contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        JLabel welcomeLabel = new JLabel("Welcome to Admin Dashboard");
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.DARK_GRAY);

        contentPanel.add(welcomeLabel);
        add(contentPanel, BorderLayout.CENTER);

        // Exit
        JButton exitBtn = new JButton("EXIT");
        exitBtn.setBackground(Color.RED);
        exitBtn.setForeground(Color.WHITE);
        exitBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        exitBtn.addActionListener(e -> System.exit(0));

        JPanel bottomExitPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomExitPanel.setBackground(new Color(255, 228, 225));
        bottomExitPanel.add(exitBtn);

        // Adding to main panel
        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);
        add(bottomExitPanel, BorderLayout.SOUTH);

    }

    // Button method
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setBackground(new Color(60, 179, 113)); // green
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.BOLD, 15));
        button.setFocusPainted(false);
        return button;
    }

    // To switch panel
    private void switchPanel(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(panel, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
    }
}
