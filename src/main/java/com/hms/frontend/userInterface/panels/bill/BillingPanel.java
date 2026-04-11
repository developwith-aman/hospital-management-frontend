package com.hms.frontend.userInterface.panels.bill;

import com.hms.frontend.dto.bill.BillRequestDTO;
import com.hms.frontend.dto.bill.BillResponseDTO;
import com.hms.frontend.dto.doctor.AddNewDoctorDTO;
import com.hms.frontend.dto.doctor.DoctorDTO;
import com.hms.frontend.service.BillService;
import com.hms.frontend.service.DoctorService;

import javax.swing.*;
import java.awt.*;

public class BillingPanel extends JPanel {

    private JTextField patientIdField = new JTextField();
    private JTextField medicineCostField = new JTextField();
    private JTextField bedChargesField = new JTextField();

    public BillingPanel() {

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
        addFormRow(leftPanel, gbcLeft, "Patient Id:", patientIdField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Medicine Cost:", medicineCostField, leftRow++);
        addFormRow(leftPanel, gbcLeft, "Bed Charges:", bedChargesField, leftRow++);

        mainBox.add(leftPanel);

        // --- SUBMIT BUTTON ---
        JButton submitBtn = new JButton("Generate Bill");
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
            BillRequestDTO billRequest = new BillRequestDTO();

            billRequest.setPatientId(Long.valueOf(patientIdField.getText()));
            billRequest.setMedicineCost(Double.parseDouble(medicineCostField.getText()));
            billRequest.setBedCharges(Double.parseDouble(bedChargesField.getText()));

            BillService billService = new BillService();
            BillResponseDTO bill = billService.generateBill(billRequest);

            if (bill != null) {
                JOptionPane.showMessageDialog(this,
                        "Bill Generated Successfully :) " + "\n" +
                                "Bill ID : " + bill.getBillId() + "\n" +
                                "Patient ID : " + bill.getPatientId() + "\n" +
                                "Medicine Cost : " + bill.getMedicineCost() + "\n" +
                                "Bed Charges : " + bill.getBedCharges() + "\n" +
                                "TOTAL BILL : " + bill.getTotalAmount()
                );
            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to generate Bill :(", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException e) {
            SwingUtilities.invokeLater(() ->
                    JOptionPane.showMessageDialog(this,
                            e.getMessage(),
                            "Input Error", JOptionPane.ERROR_MESSAGE)
            );
        }
    }
}
