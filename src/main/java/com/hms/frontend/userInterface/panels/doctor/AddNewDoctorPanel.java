package com.hms.frontend.userInterface.panels.doctor;

import com.hms.frontend.dto.doctor.AddNewDoctorDTO;
import com.hms.frontend.dto.doctor.DoctorDTO;
import com.hms.frontend.service.DoctorService;

import javax.swing.*;
import java.awt.*;

public class AddNewDoctorPanel extends JPanel {

    private JTextField doctorNameField = new JTextField();
    private JTextField specializationField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField consultationFeeField = new JTextField();

    public AddNewDoctorPanel() {

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
        addFormRow(leftPanel, gbcLeft, "Doctor Name:", doctorNameField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Specialization:", specializationField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "email id:", emailField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Consultation Fee:", consultationFeeField, leftRow++);

        mainBox.add(leftPanel);

        // --- SUBMIT BUTTON ---
        JButton submitBtn = new JButton("Add Doctor");
        submitBtn.setPreferredSize(new Dimension(200, 50));
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

    public void handleSubmit() {

        try {
            AddNewDoctorDTO newDoctorDTO = new AddNewDoctorDTO();

            newDoctorDTO.setDoctorName(doctorNameField.getText());
            newDoctorDTO.setSpecialization(specializationField.getText());
            newDoctorDTO.setEmail(emailField.getText());
            newDoctorDTO.setConsultationFee(Double.parseDouble(consultationFeeField.getText()));

            DoctorService doctorService = new DoctorService();
            DoctorDTO savedDoctor = doctorService.addNewDoctor(newDoctorDTO);

            if (savedDoctor != null) {
                JOptionPane.showMessageDialog(this,
                        "Doctor Added Successfully :) " + "\n" +
                                "Doctor ID : " + savedDoctor.getID()  + "\n" +
                                "Name : " + savedDoctor.getDoctorName() + "\n" +
                                "Specialization : " + savedDoctor.getSpecialization() + "\n" +
                                "Consultation Fee : " + savedDoctor.getConsultationFee());
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to add Doctor :(", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error while adding doctor! Please check your input formatting.");
        }
    }
}
