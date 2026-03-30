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

    Font labelFont = new Font("Arial", Font.BOLD, 16);
    Font fieldFont = new Font("Arial", Font.BOLD,16 );
    public PatientDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Patient Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 5, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        detailsPanel.setFont(labelFont);
        detailsPanel.setBackground(Color.PINK);

        JLabel idText = new JLabel("Patient ID:");
        idText.setFont(labelFont);
        detailsPanel.add(idText);
        detailsPanel.add(idLabel);

        JLabel nameText = new JLabel("Name:");
        nameText.setFont(labelFont);
        detailsPanel.add(nameText);
        detailsPanel.add(nameLabel);

        JLabel genderText = new JLabel("Gender:");
        genderText.setFont(labelFont);
        detailsPanel.add(genderText);
        detailsPanel.add(genderLabel);

        JLabel ageText = new JLabel("Age:");
        ageText.setFont(labelFont);
        detailsPanel.add(ageText);
        detailsPanel.add(ageLabel);

        JLabel emailText = new JLabel("Email:");
        emailText.setFont(labelFont);
        detailsPanel.add(emailText);
        detailsPanel.add(emailLabel);

        JLabel bloodGroupText = new JLabel("Blood Group:");
        bloodGroupText.setFont(labelFont);
        detailsPanel.add(bloodGroupText);
        detailsPanel.add(bloodGroupLabel);

        JLabel insuredText = new JLabel("Insured:");
        insuredText.setFont(labelFont);
        detailsPanel.add(insuredText);
        detailsPanel.add(insuredLabel);

        JLabel arrivalTimeText = new JLabel("Arrival Time:");
        arrivalTimeText.setFont(labelFont);
        detailsPanel.add(arrivalTimeText);
        detailsPanel.add(arrivalTimeLabel);

        JLabel appointmentCountText = new JLabel("Number of Appointments:");
        appointmentCountText.setFont(labelFont);
        detailsPanel.add(appointmentCountText);
        detailsPanel.add(appointmentCountLabel);

        JLabel payableAmountText = new JLabel("Payable Amount:");
        payableAmountText.setFont(labelFont);
        detailsPanel.add(payableAmountText);
        detailsPanel.add(payableAmountLabel);

        add(detailsPanel, BorderLayout.CENTER);
        setBackground(Color.PINK);
    }

    public void loadPatientData() {
        Long userId = SessionManager.userId;
        Long patientId = SessionManager.patientId;

        // fetch patient details
        PatientsDTO patient = patientService.getPatientDetails(patientId);
//        List<PatientAppointmentsDTO> appointments = patientService.getAppointments(patientId);

        if (patient != null) {
            idLabel.setText(String.valueOf(patient.getPatientID()));
            idLabel.setFont(fieldFont);
            idLabel.setForeground(new Color(128, 0, 0));

            nameLabel.setText(patient.getPatientName());
            nameLabel.setFont(fieldFont);
            nameLabel.setForeground(new Color(128, 0, 0));

            emailLabel.setText(patient.getEmail());
            emailLabel.setFont(fieldFont);
            emailLabel.setForeground(new Color(128, 0, 0));

            ageLabel.setText(String.valueOf(patient.getAge()));
            ageLabel.setFont(fieldFont);
            ageLabel.setForeground(new Color(128, 0, 0));

            genderLabel.setText(patient.getGender());
            genderLabel.setFont(fieldFont);
            genderLabel.setForeground(new Color(128, 0, 0));

            bloodGroupLabel.setText(patient.getBloodGroup());
            bloodGroupLabel.setFont(fieldFont);
            bloodGroupLabel.setForeground(new Color(128, 0, 0));

            insuredLabel.setText(patient.isInsured() ? "Yes" : "No");
            insuredLabel.setFont(fieldFont);
            insuredLabel.setForeground(new Color(128, 0, 0));

            arrivalTimeLabel.setText(patient.getArrivalTime().toLocalDate() + " | " + patient.getArrivalTime().toLocalTime());
            arrivalTimeLabel.setFont(fieldFont);
            arrivalTimeLabel.setForeground(new Color(128, 0, 0));

            appointmentCountLabel.setText(String.valueOf(patient.getNumberOfAppointments()));
            appointmentCountLabel.setFont(fieldFont);
            appointmentCountLabel.setForeground(new Color(128, 0, 0));

            payableAmountLabel.setText(String.valueOf(patient.getPayableAmount()));
            payableAmountLabel.setFont(fieldFont);
            payableAmountLabel.setForeground(new Color(128, 0, 0));
        }
    }
}
