package com.hms.frontend.userInterface.panels.department;

import com.hms.frontend.dto.department.DepartmentDTO;
import com.hms.frontend.service.DepartmentService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowDepartmentPanel extends JPanel {

    private JTable departmentTable;
    private DefaultTableModel tableModel;

    public ShowDepartmentPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = new JLabel("All Departments");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        headerLabel.setForeground(new Color(0, 128, 128));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // The Table Model
        String[] columnNames = {"Department ID", "Department Code", "Department Name", "Department-Head ID", "Department-Head Name"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This prevents the user from accidentally typing into the table cells
                return false;
            }
        };

        // The Table UI
        departmentTable = new JTable(tableModel);
        departmentTable.setRowHeight(30);
        departmentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        departmentTable.getTableHeader().setBackground(new Color(240, 240, 240));
        departmentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // The Scroll Pane
        JScrollPane scrollPane = new JScrollPane(departmentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and Load the Data
        loadDepartments();
    }

    private void loadDepartments() {
        try {
            DepartmentService departmentService = new DepartmentService();
            List<DepartmentDTO> departmentList = departmentService.getAllDepartments();

            if (departmentList != null) {
                for (DepartmentDTO department : departmentList) {
                    Object[] rowData = {
                            department.getDepartmentID(),
                            department.getDepartmentCode(),
                            department.getDepartmentName(),
                            department.getHeadDoctorID(),
                            department.getHeadDoctorName(),
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load Departments from server.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
