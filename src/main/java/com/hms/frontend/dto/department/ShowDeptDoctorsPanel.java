package com.hms.frontend.dto.department;

import com.hms.frontend.dto.doctor.DoctorDTO;
import com.hms.frontend.service.DepartmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowDeptDoctorsPanel extends JPanel {
    private JTable doctorsOfDepartmentTable;
    private DefaultTableModel tableModel;

    public ShowDeptDoctorsPanel(List<DoctorDTO> doctorList) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = new JLabel("All Doctors");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        headerLabel.setForeground(new Color(0, 128, 128));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // The Table Model
        String[] columnNames = {"Doctor ID", "Doctor Name", "Specialization", "Email Id", "Consultation Fee"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This prevents the user from accidentally typing into the table cells
                return false;
            }
        };

        // The Table UI
        doctorsOfDepartmentTable = new JTable(tableModel);
        doctorsOfDepartmentTable.setRowHeight(30);
        doctorsOfDepartmentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        doctorsOfDepartmentTable.getTableHeader().setBackground(new Color(240, 240, 240));
        doctorsOfDepartmentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // The Scroll Pane
        JScrollPane scrollPane = new JScrollPane(doctorsOfDepartmentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and Load the Data
        loadDeptDoctors(doctorList);
    }

    private void loadDeptDoctors(List<DoctorDTO> doctorList) {
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
    }
}

