package com.hms.frontend.userInterface.panels;

import com.hms.frontend.api.ApiClient;
import com.hms.frontend.dto.InsuranceDTO;
import com.hms.frontend.dto.patient.AddNewPatientDTO;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.dto.enums.BloodGroups;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;

public class AddNewPatientPanel extends JPanel {

    private JTextField nameField = new JTextField(15);
    private JTextField ageField = new JTextField(15);
    private JTextField emailField = new JTextField(15);
    private JTextField payableField = new JTextField(15);

    private JComboBox<String> genderBox = new JComboBox<>(new String[]{"MALE", "FEMALE"});
    private JComboBox<BloodGroups> bloodGroupBox = new JComboBox<>(BloodGroups.values());

    private JCheckBox insuranceCheck = new JCheckBox("Has Insurance");
    private JPanel insurancePanel = new JPanel(new GridLayout(0, 2, 10, 10));
    private JTextField policyNumberField = new JTextField(15);
    private JTextField providerField = new JTextField(15);
    private JTextField effectiveDateField = new JTextField(15);
    private JTextField expiryDateField = new JTextField(15);

    public AddNewPatientPanel() {
        setLayout(new BorderLayout());

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));

        form.add(new JLabel("Name"));
        form.add(nameField);
        form.add(new JLabel("Gender"));
        form.add(genderBox);
        form.add(new JLabel("Age"));
        form.add(ageField);
        form.add(new JLabel("Email"));
        form.add(emailField);
        form.add(new JLabel("Blood Group"));
        form.add(bloodGroupBox);
        form.add(new JLabel("Payable Amount"));
        form.add(payableField);

        // Insurance toggle
        form.add(new JLabel("Insurance"));
        form.add(insuranceCheck);

        // Insurance panel (initially hidden)
        insurancePanel.add(new JLabel("Policy No"));
        insurancePanel.add(policyNumberField);
        insurancePanel.add(new JLabel("Provider"));
        insurancePanel.add(providerField);
        insurancePanel.add(new JLabel("Effective Date"));
        insurancePanel.add(effectiveDateField);
        insurancePanel.add(new JLabel("Expiry Date"));
        insurancePanel.add(expiryDateField);

        insurancePanel.setVisible(false);

        // Toggle logic
        insuranceCheck.addActionListener(e -> {
            insurancePanel.setVisible(insuranceCheck.isSelected());
            revalidate();
        });

        JButton submitBtn = new JButton("Add Patient");
        submitBtn.addActionListener(e -> handleSubmit());

        JPanel wrapper = new JPanel(new BorderLayout());
        wrapper.add(form, BorderLayout.NORTH);
        wrapper.add(insurancePanel, BorderLayout.CENTER);
        wrapper.add(submitBtn, BorderLayout.SOUTH);

        add(wrapper, BorderLayout.CENTER);
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

            // Insurance logic
            if (insuranceCheck.isSelected()) {
                InsuranceDTO insurance = new InsuranceDTO();

                insurance.setPolicyNumber(policyNumberField.getText());
                insurance.setPolicyProvider(providerField.getText());
                insurance.setEffectiveDate(LocalDateTime.parse(effectiveDateField.getText()));
                insurance.setExpiryDate(LocalDateTime.parse(expiryDateField.getText()));

                dto.setInsurance(insurance);
            }

            // API CALL
            ApiClient.postWithToken("/patients/add/new/patient", dto, PatientsDTO.class);

            JOptionPane.showMessageDialog(this, "Patient Added Successfully!");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error while adding patient!");
        }
    }
}
