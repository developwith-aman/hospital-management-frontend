package com.hms.frontend.userInterface.panels.department;

import com.hms.frontend.dto.department.ShowDeptDoctorsPanel;
import com.hms.frontend.dto.doctor.DoctorDTO;
import com.hms.frontend.service.DepartmentService;
import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;

import java.util.List;

import javax.swing.*;
import java.awt.*;

public class MainDepartmentPanel extends JPanel {
    private DepartmentService departmentService = new DepartmentService();
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
            AddNewDepartmentPanel.showDialog(this, departmentService);
        });

        // View Department Button
        viewDepartmentBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new ShowDepartmentPanel(), BorderLayout.SOUTH);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });

        // Add Doctor Button
        addDoctorBtn.addActionListener(e -> {
            AddDoctorToDeptPanel.showDialog(this, departmentService);
        });

        // Show Department Doctors
        viewDoctorsBtn.addActionListener(e -> {
            String deptIdStr = JOptionPane.showInputDialog(this, "Enter Department ID: ");

            if (deptIdStr != null && !deptIdStr.trim().isEmpty()) {
                new Thread(() -> {
                    try {
                        Long departmentId = Long.parseLong(deptIdStr);
                        List<DoctorDTO> doctorList = departmentService.getAllDoctorsOfDept(departmentId);

                        SwingUtilities.invokeLater(() -> {
                            if (doctorList == null || doctorList.isEmpty()) {
                                JOptionPane.showMessageDialog(this, "No doctors found or Department ID invalid.");
                                return;
                            }

                            // showing response in the panel
                            dynamicContentContainer.removeAll();
                            dynamicContentContainer.add(new ShowDeptDoctorsPanel(doctorList), BorderLayout.SOUTH);
                            dynamicContentContainer.revalidate();
                            dynamicContentContainer.repaint();
                        });
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Invalid ID!");
                    }
                }).start();
            }
        });

        // Back Button Logic
        backButton.addActionListener(e -> {
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mainFrame.setContentPane(new AdminDashboard(mainFrame));
            mainFrame.revalidate();
            mainFrame.repaint();
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
