package com.hms.frontend.userInterface.panels.department;

import javax.swing.*;
import java.awt.*;

public class MainDepartmentPanel extends JPanel {


    private JPanel dynamicContentContainer;
    public MainDepartmentPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Button Bar (on TOP)
        JPanel buttonBar = new JPanel(new GridLayout(2, 3, 20, 15));
        buttonBar.setBackground(Color.WHITE);
        buttonBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addNewDepartmentBtn = createButton("Add New Department");  // POST
        JButton viewDepartmentBtn = createButton("View Departments");  // GET
        JButton addDoctorBtn = createButton("Add Doctor");  // POST
        JButton viewDoctorsBtn = createButton("Show Doctors of Department");  // GET


        buttonBar.add(addNewDepartmentBtn);
        buttonBar.add(viewDepartmentBtn);
        buttonBar.add(addDoctorBtn);
        buttonBar.add(viewDoctorsBtn);

        // Back Button
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Centre Section
        dynamicContentContainer = new JPanel(new BorderLayout());
        dynamicContentContainer.setBackground(Color.WHITE);

        // Top Wrapper
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.WHITE);
        topWrapper.add(backButton, BorderLayout.WEST);
        topWrapper.add(buttonBar, BorderLayout.CENTER);

        // Adding to the MainDepartmentPanel
        add(topWrapper, BorderLayout.NORTH);
        add(dynamicContentContainer, BorderLayout.SOUTH);

        // Add Action Listeners
        // Add New Department Button
        addNewDepartmentBtn.addActionListener(e -> {

        });

        // View Department Button
        viewDepartmentBtn.addActionListener(e -> {

        });

        // Add Doctor Button
        addDoctorBtn.addActionListener(e -> {

        });

        // Show Department Doctors
        viewDoctorsBtn.addActionListener(e -> {

        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(0, 128, 128));
        button.setForeground(Color.WHITE);
        return button;
    }
}
