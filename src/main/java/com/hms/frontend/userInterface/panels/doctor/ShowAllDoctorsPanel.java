package com.hms.frontend.userInterface.panels.doctor;

import com.hms.frontend.dto.doctor.DoctorDTO;
import com.hms.frontend.service.DoctorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowAllDoctorsPanel extends JPanel {

    private JTable doctorTable;
    private DefaultTableModel tableModel;

    public ShowAllDoctorsPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = new JLabel("All Doctors List");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        headerLabel.setForeground(new Color(0, 128, 128));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // Table Column headers
        String[] columns = {"ID", "Name", "Specialization", "Email", "Consultation Fee"};

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        doctorTable = new JTable(tableModel);
        doctorTable.setRowHeight(30);
        doctorTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        doctorTable.getTableHeader().setBackground(new Color(240, 240, 240, 255));
        doctorTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // For Scroll
        JScrollPane scrollPane = new JScrollPane(doctorTable);
        add(scrollPane, BorderLayout.CENTER);

        // fill table data
        fillTableData();
    }

    private void fillTableData() {
        try {
            DoctorService doctorService = new DoctorService();
            List<DoctorDTO> doctorList = doctorService.fetchAllDoctors();

            if (doctorList != null) {
                for (DoctorDTO doctor : doctorList) {
                    Object[] rowData = {
                            doctor.getID(),
                            doctor.getDoctorName(),
                            doctor.getSpecialization(),
                            doctor.getEmail(),
                            doctor.getConsultationFee(),
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
