package com.hms.frontend.userInterface.panels.patient;

import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.userInterface.MainFrame;

import javax.swing.*;
import java.awt.*;

public class PatientDetailsPanel extends JPanel {
    private final JLabel idLabel = new JLabel(), nameLabel = new JLabel(), genderLabel = new JLabel(),
            ageLabel = new JLabel(), emailLabel = new JLabel(), bloodGroupLabel = new JLabel(),
            insuredLabel = new JLabel(), arrivalTimeLabel = new JLabel(),
            appointmentCountLabel = new JLabel(), payableAmountLabel = new JLabel();

    Font font = new Font("Arial", Font.BOLD, 18);

    public PatientDetailsPanel(PatientsDTO patient) {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

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

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> {
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
            topFrame.setContentPane(new MainPatientPanel());
            topFrame.revalidate();
        });

        add(detailsPanel, BorderLayout.CENTER);
        add(backBtn, BorderLayout.SOUTH);
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

    }
    private void setField(JLabel label, String value, Font font) {
        label.setText(value);
        label.setFont(font);
        label.setForeground(new Color(128, 0, 0));
    }
}
