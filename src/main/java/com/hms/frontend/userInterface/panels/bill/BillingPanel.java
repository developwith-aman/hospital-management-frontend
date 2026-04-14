package com.hms.frontend.userInterface.panels.bill;

import com.hms.frontend.dto.bill.BillRequestDTO;
import com.hms.frontend.dto.bill.BillResponseDTO;
import com.hms.frontend.service.BillService;

import java.util.List;

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
            List<BillResponseDTO> bills = billService.generateBill(billRequest);

            if (bills != null && !bills.isEmpty()) {
                StringBuilder report = new StringBuilder();
                report.append("--- FINAL BILL SUMMARY ---\n");

                BillResponseDTO firstBill = bills.getFirst();
                report.append("Patient ID : ").append(firstBill.getPatientId()).append("\n");
                report.append("Medicine Cost : ").append(firstBill.getMedicineCost()).append("\n");
                report.append("Bed Charges : ").append(firstBill.getBedCharges()).append("\n");
                report.append("---------------------------\n");
                report.append("Visited Doctors & Appointments :\n");

                // Loop through the list to show all doctors
                for (BillResponseDTO b : bills) {
                    report.append("Doctor ID : ").append(b.getDoctorId())
                            .append(" | Consultation Fee : ").append(b.getConsultationFee()).append("\n");

                }

                report.append("---------------------------\n");
                report.append("GRAND TOTAL : ").append(firstBill.getTotalAmount()).append("\n");
                report.append("---------------------------");

                // Custom TextArea for better scrolling if list is long
                JTextArea textArea = new JTextArea(report.toString());
                textArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(textArea);
                scrollPane.setPreferredSize(new Dimension(350, 250));

                JOptionPane.showMessageDialog(this,
                        scrollPane, "Bill Generated Successfully :)", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(this,
                        "Failed to generate Bill :(", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values!", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
