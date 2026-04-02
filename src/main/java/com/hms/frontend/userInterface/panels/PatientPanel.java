package com.hms.frontend.userInterface.panels;

import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.service.PatientService;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private final PatientService patientService = new PatientService();

    public PatientPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.WHITE);

        JButton addBtn = createButton("Add Patient");
        JButton viewAllBtn = createButton("View All");
        JButton searchBtn = createButton("Search Patient");
        JButton updateEmailBtn = createButton("Update Email");
        JButton dischargeBtn = createButton("Discharge");
        JButton bookBtn = createButton("Book Appointment");
        JButton deleteAppBtn = createButton("Delete Appointment");

        add(addBtn);
        add(viewAllBtn);
        add(searchBtn);
        add(updateEmailBtn);
        add(dischargeBtn);
        add(bookBtn);
        add(deleteAppBtn);

        // Search Button
        searchBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Patient ID: ");

            if (input != null && !input.trim().isEmpty()) {
                try {
                    Long patientId = Long.parseLong(input);
                    PatientsDTO patient = patientService.getPatientDetails(patientId);

                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    topFrame.setContentPane(new PatientDetailsPanel(patient));
                    topFrame.revalidate();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid ID!");
                }
            }
        });

        viewAllBtn.addActionListener(e -> {
            System.out.println("View all patients clicked");
        });

        addBtn.addActionListener(e -> {
            System.out.println("Add patient clicked");
        });

    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(0, 128, 128));
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(300, 50));
        return button;
    }
}
