package com.hms.frontend.userInterface.dashboards;

import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.service.PatientService;
import com.hms.frontend.session.SessionManager;
import com.hms.frontend.userInterface.MainFrame;


import javax.swing.*;
import java.awt.*;

public class PatientDashboard extends JPanel {

    private final JLabel idLabel = new JLabel(), nameLabel = new JLabel(), genderLabel = new JLabel(),
            ageLabel = new JLabel(), emailLabel = new JLabel(), bloodGroupLabel = new JLabel(),
            insuredLabel = new JLabel(), arrivalTimeLabel = new JLabel(),
            appointmentCountLabel = new JLabel(), payableAmountLabel = new JLabel();
    private final PatientService patientService = new PatientService();
    private final MainFrame mainFrame;
    Font font = new Font("Arial", Font.BOLD, 16);

    public PatientDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        JButton goExitBtn = new JButton("EXIT");
        goExitBtn.setBackground(Color.RED);
        goExitBtn.setForeground(Color.WHITE);
        goExitBtn.setFont(new Font("Tahoma", Font.BOLD, 15));
        goExitBtn.addActionListener(e -> System.exit(0));

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setOpaque(false); // Keeps the background color of the parent
        buttonPanel.add(goExitBtn);


        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Patient Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 5, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        detailsPanel.setFont(font);
        detailsPanel.setBackground(Color.PINK);

        JLabel idText = new JLabel("Patient ID:");
        idText.setFont(font);
        detailsPanel.add(idText);
        detailsPanel.add(idLabel);

        JLabel nameText = new JLabel("Name:");
        nameText.setFont(font);
        detailsPanel.add(nameText);
        detailsPanel.add(nameLabel);

        JLabel genderText = new JLabel("Gender:");
        genderText.setFont(font);
        detailsPanel.add(genderText);
        detailsPanel.add(genderLabel);

        JLabel ageText = new JLabel("Age:");
        ageText.setFont(font);
        detailsPanel.add(ageText);
        detailsPanel.add(ageLabel);

        JLabel emailText = new JLabel("Email:");
        emailText.setFont(font);
        detailsPanel.add(emailText);
        detailsPanel.add(emailLabel);

        JLabel bloodGroupText = new JLabel("Blood Group:");
        bloodGroupText.setFont(font);
        detailsPanel.add(bloodGroupText);
        detailsPanel.add(bloodGroupLabel);

        JLabel insuredText = new JLabel("Insured:");
        insuredText.setFont(font);
        detailsPanel.add(insuredText);
        detailsPanel.add(insuredLabel);

        JLabel arrivalTimeText = new JLabel("Arrival Time:");
        arrivalTimeText.setFont(font);
        detailsPanel.add(arrivalTimeText);
        detailsPanel.add(arrivalTimeLabel);

        JLabel appointmentCountText = new JLabel("Number of Appointments:");
        appointmentCountText.setFont(font);
        detailsPanel.add(appointmentCountText);
        detailsPanel.add(appointmentCountLabel);

        JLabel payableAmountText = new JLabel("Payable Amount:");
        payableAmountText.setFont(font);
        detailsPanel.add(payableAmountText);
        detailsPanel.add(payableAmountLabel);

        add(detailsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setBackground(Color.PINK);
    }

    public void loadPatientData() {
        try {
            Long userId = SessionManager.userId;
            Long patientId = SessionManager.patientId;

            // fetch patient details
            PatientsDTO patient = patientService.getPatientDetails(patientId);

            if (patient != null) {
                setField(idLabel, String.valueOf(patient.getPatientID()), font);
                setField(nameLabel, patient.getPatientName(), font);
                setField(emailLabel, patient.getEmail(), font);
                setField(ageLabel, String.valueOf(patient.getAge()), font);
                setField(genderLabel, patient.getGender(), font);
                setField(bloodGroupLabel, patient.getBloodGroup(), font);
                setField(insuredLabel, patient.isInsured() ? "Yes" : "No", font);
                setField(arrivalTimeLabel,
                        patient.getArrivalTime().toLocalDate() + " | " +
                                patient.getArrivalTime().toLocalTime(),
                        font);
                setField(appointmentCountLabel,
                        String.valueOf(patient.getNumberOfAppointments()), font);
                setField(payableAmountLabel,
                        "₹ " + patient.getPayableAmount(), font);
            }else {
                JOptionPane.showMessageDialog(this,
                        "No patient found with ID: " + patientId + ". \nAccess denied or patient does not exist.",
                        "Patient Not Found",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Cannot load Patient details", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setField(JLabel label, String value, Font font) {
        label.setText(value);
        label.setFont(font);
        label.setForeground(new Color(128, 0, 0));
    }
}
