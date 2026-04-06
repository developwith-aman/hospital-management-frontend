package com.hms.frontend.userInterface.panels.patient;

import com.hms.frontend.dto.insurance.InsuranceDTO;
import com.hms.frontend.dto.patient.AddNewPatientDTO;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.dto.enums.BloodGroups;
import com.hms.frontend.service.PatientService;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddNewPatientPanel extends JPanel {

    private JTextField nameField = new JTextField();
    private JTextField ageField = new JTextField();
    private JTextField emailField = new JTextField();
    private JTextField payableField = new JTextField();

    private JComboBox<String> genderBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});
    private JComboBox<BloodGroups> bloodGroupBox = new JComboBox<>(BloodGroups.values());

    private JCheckBox insuranceCheck = new JCheckBox("Insurance :");
    private JTextField policyNumberField = new JTextField();
    private JTextField providerField = new JTextField();
    private JTextField effectiveDateField = new JTextField();
    private JTextField expiryDateField = new JTextField();

    public AddNewPatientPanel() {

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
        addFormRow(leftPanel, gbcLeft, "Name:", nameField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Gender:", genderBox, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Age:", ageField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Email:", emailField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Blood Group:", bloodGroupBox, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Payable Amount:", payableField, leftRow++);

        // --- RIGHT COLUMN: Insurance Info ---
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setBackground(Color.WHITE);
        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.fill = GridBagConstraints.HORIZONTAL;
        gbcRight.insets = new Insets(10, 10, 10, 10);

        int rightRow = 0;

        // The "Insurance :" Header/Checkbox
        gbcRight.gridx = 0; gbcRight.gridy = rightRow++;
        gbcRight.gridwidth = 2; // Span across both label and field columns
        insuranceCheck.setFont(new Font("SansSerif", Font.BOLD, 14));
        insuranceCheck.setBackground(Color.WHITE);
        rightPanel.add(insuranceCheck, gbcRight);

        // Reset grid width to 1 for the normal fields
        gbcRight.gridwidth = 1;
        addFormRow(rightPanel, gbcRight, "Policy No:", policyNumberField, rightRow++);
        addFormRow(rightPanel, gbcRight, "Provider:", providerField, rightRow++);
        addFormRow(rightPanel, gbcRight, "Effective Date:", effectiveDateField, rightRow++);
        addFormRow(rightPanel, gbcRight, "Expiry Date:", expiryDateField, rightRow++);

        // Add both columns into the main bordered box
        mainBox.add(leftPanel);
        mainBox.add(rightPanel);

        // --- TOGGLE LOGIC ---
        toggleInsuranceFields(false);
        insuranceCheck.addActionListener(e -> toggleInsuranceFields(insuranceCheck.isSelected()));

        // --- SUBMIT BUTTON ---
        JButton submitBtn = new JButton("Add Patient");
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

    // Helper method to gray-out insurance fields if the checkbox isn't checked
    private void toggleInsuranceFields(boolean enabled) {
        policyNumberField.setEnabled(enabled);
        providerField.setEnabled(enabled);
        effectiveDateField.setEnabled(enabled);
        expiryDateField.setEnabled(enabled);
    }

    private void handleSubmit() {
        try {
            AddNewPatientDTO dto = new AddNewPatientDTO();

            dto.setPatientName(nameField.getText());
            dto.setGender((String) genderBox.getSelectedItem());
            dto.setAge(Integer.parseInt(ageField.getText()));
            dto.setEmail(emailField.getText());
            dto.setPayableAmount(Double.parseDouble(payableField.getText()));
            dto.setBloodGroup((BloodGroups) bloodGroupBox.getSelectedItem());

            if (insuranceCheck.isSelected()) {
                InsuranceDTO insurance = new InsuranceDTO();

                insurance.setPolicyNumber(policyNumberField.getText());
                insurance.setPolicyProvider(providerField.getText());
                insurance.setEffectiveDate(LocalDateTime.parse(effectiveDateField.getText()));
                insurance.setExpiryDate(LocalDateTime.parse(expiryDateField.getText()));

                dto.setInsurance(insurance);
            }

            PatientService patientService = new PatientService();
            PatientsDTO savedPatient = patientService.addNewPatient(dto);

            if (savedPatient != null){
                JOptionPane.showMessageDialog(this,
                        "Patient Added Successfully! ID : "+ savedPatient.getPatientID());
            }else {
                JOptionPane.showMessageDialog(this,
                        "Failed to save patient.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while adding patient! Please check your input formatting.");
        }
    }
}