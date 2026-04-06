package com.hms.frontend.userInterface.panels.patient;

import com.hms.frontend.dto.appointment.NewAppointmentDTO;
import com.hms.frontend.service.PatientService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class BookAppointmentPanel extends JPanel {

    private JTextField doctorIdField = new JTextField();
    private JTextField patientIdField = new JTextField();
    private JTextField reasonField = new JTextField();

    public BookAppointmentPanel() {

        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(Color.WHITE);

        JPanel mainBox = new JPanel(new GridLayout(1, 2, 40, 0));
        mainBox.setBackground(Color.WHITE);

        mainBox.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.BLACK, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JPanel leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcLeft = new GridBagConstraints();
        gbcLeft.fill = GridBagConstraints.HORIZONTAL;
        gbcLeft.insets = new Insets(10, 10, 10, 10); // Spacing between rows

        int leftRow = 0;
        addFormRow(leftPanel, gbcLeft, "Patient ID:", patientIdField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Doctor ID:", doctorIdField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Reason:", reasonField, leftRow++);

        mainBox.add(leftPanel);

        // --- SUBMIT BUTTON ---
        JButton submitBtn = new JButton("Book Appointment");
        submitBtn.setPreferredSize(new Dimension(150, 40));
        submitBtn.addActionListener(e -> handleSubmit());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        buttonPanel.add(submitBtn);

        // --- ASSEMBLE ---
        JPanel topAnchor = new JPanel(new BorderLayout());
        topAnchor.setBackground(Color.WHITE);
        topAnchor.add(mainBox, BorderLayout.NORTH);

        add(topAnchor, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addFormRow(JPanel panel, GridBagConstraints gbc, String labelText, Component field, int row) {
        gbc.gridy = row;

        // Label settings
        gbc.gridx = 0;
        gbc.weightx = 0.3; // Give label 30% of the horizontal space
        panel.add(new JLabel(labelText), gbc);

        // Input Field settings
        gbc.gridx = 1;
        gbc.weightx = 0.7; // Give text field 70% of the horizontal space
        panel.add(field, gbc);
    }

    private void handleSubmit() {
        try {
            NewAppointmentDTO dto = new NewAppointmentDTO();

            dto.setPatientId(Long.valueOf(patientIdField.getText()));
            dto.setDoctorId(Integer.parseInt(doctorIdField.getText()));
            dto.setReason(reasonField.getText());

            PatientService patientService = new PatientService();
            NewAppointmentDTO bookedAppointment = patientService.bookNewAppointment(dto);

            if (bookedAppointment != null) {
                JOptionPane.showMessageDialog(this,
                        "Appointment Booked :)" + "\n" +
                                "Patient id : " + bookedAppointment.getPatientId() + "\n" +
                                "Doctor id : " + bookedAppointment.getDoctorId() + "\n" +
                                "Appointment Time : " + bookedAppointment.getAppointment_time() + "\n" +
                                "Reason : " + bookedAppointment.getReason() + "\n"
                );
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to book Appointment", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error while booking appointment! Please check your input formatting.");
        }
    }
}
