package com.hms.frontend.userInterface.panels.patient;

import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.service.PatientService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewAllPatientsPanel extends JPanel {

    private JTable patientTable;
    private DefaultTableModel tableModel;

    public ViewAllPatientsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = new JLabel("All Registered Patients");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 128, 128));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // The Table Model
        // Define your column headers here
        String[] columnNames = {"Patient ID", "Name", "Age", "Gender", "Blood Group",
                "Email", "Has Insurance", "Arrival Time", "Appointments", "Payable Amount"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This prevents the user from accidentally typing into the table cells
                return false;
            }
        };

        // The Table UI
        patientTable = new JTable(tableModel);
        patientTable.setRowHeight(30); // Makes the rows taller and easier to read
        patientTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        patientTable.getTableHeader().setBackground(new Color(240, 240, 240));
        patientTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // The Scroll Pane
        JScrollPane scrollPane = new JScrollPane(patientTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and Load the Data
        loadPatientData();
    }

    private void loadPatientData() {
        try {
            PatientService patientService = new PatientService();
            List<PatientsDTO> patientsList = patientService.getAllPatients();

            if (patientsList != null) {
                // Loop through the list and add each patient as a new row in the spreadsheet
                for (PatientsDTO patient : patientsList) {
                    Object[] rowData = {
                            patient.getPatientID(),
                            patient.getPatientName(),
                            patient.getAge(),
                            patient.getGender(),
                            patient.getBloodGroup(),
                            patient.getEmail(),
                            patient.isInsured() ? "YES" : "NO",
                            patient.getArrivalTime(),
                            patient.getNumberOfAppointments(),
                            patient.getPayableAmount()
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load patient data from server.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}