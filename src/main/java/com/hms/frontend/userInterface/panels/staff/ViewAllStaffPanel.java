package com.hms.frontend.userInterface.panels.staff;

import com.hms.frontend.dto.staff.StaffDTO;
import com.hms.frontend.service.StaffService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ViewAllStaffPanel extends JPanel {

    private JTable staffTable;
    private DefaultTableModel tableModel;

    public ViewAllStaffPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = new JLabel("All Staff");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        headerLabel.setForeground(new Color(0, 128, 128));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // The Table Model
        String[] columnNames = {"Staff ID", "Staff Member Name", "Age", "Salary", "Staff Department"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This prevents the user from accidentally typing into the table cells
                return false;
            }
        };

        // The Table UI
        staffTable = new JTable(tableModel);
        staffTable.setRowHeight(30);
        staffTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        staffTable.getTableHeader().setBackground(new Color(240, 240, 240));
        staffTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // The Scroll Pane
        JScrollPane scrollPane = new JScrollPane(staffTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and Load the Data
        loadStaff();
    }

    private void loadStaff() {
        try {
            StaffService staffService = new StaffService();
            List<StaffDTO> staffList = staffService.fetchAllStaff();

            if (staffList!= null) {
                for (StaffDTO staff : staffList) {
                    Object[] rowData = {
                            staff.getStaffID(),
                            staff.getStaffMemberName(),
                            staff.getAge(),
                            staff.getSalary(),
                            staff.getStaffDepartment(),
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load Staff from server.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
