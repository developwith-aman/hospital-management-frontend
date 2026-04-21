package com.hms.frontend.userInterface.panels.staff;

import com.hms.frontend.dto.staff.AddNewStaffMember;
import com.hms.frontend.dto.enums.StaffDepartments;
import com.hms.frontend.dto.staff.StaffDTO;
import com.hms.frontend.service.StaffService;

import javax.swing.*;
import java.awt.*;

public class AddStaffDialog extends JDialog {

    public static void showDialog(Component parent, StaffService service) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(parent), "Register New Staff", true);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));
        dialog.setSize(500, 450);
        dialog.setLocationRelativeTo(parent);

        // Fields
        JTextField nameField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField salaryField = new JTextField();

        // The Two Dropdowns
        JComboBox<StaffDepartments> deptCombo = new JComboBox<>(StaffDepartments.values());

        // Logic to update designations based on department
        deptCombo.addActionListener(e -> {
            StaffDepartments selected = (StaffDepartments) deptCombo.getSelectedItem();
            if (selected == null) return;
        });

        // Initialize the first selection
        deptCombo.setSelectedIndex(0);

        // Add to Dialog
        dialog.add(new JLabel(" Full Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel(" Age:"));
        dialog.add(ageField);
        dialog.add(new JLabel(" Salary:"));
        dialog.add(salaryField);
        dialog.add(new JLabel(" Department:"));
        dialog.add(deptCombo);

        JButton saveBtn = new JButton("Save Staff Member");
        saveBtn.setBackground(new Color(0, 128, 128));
        saveBtn.setForeground(Color.WHITE);

        saveBtn.addActionListener(e -> {
            AddNewStaffMember staff = new AddNewStaffMember();
            staff.setStaffMemberName(nameField.getText());
            staff.setAge(Integer.parseInt(ageField.getText()));
            staff.setSalary(Double.parseDouble(salaryField.getText()));
            staff.setStaffDepartment((StaffDepartments) deptCombo.getSelectedItem());

            new Thread(() -> {
                try {
                    StaffDTO response = service.addNewStaffMember(staff);

                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(dialog,
                                "Staff Registered Successfully!\nID: " + response.getStaffID(),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                        dialog.dispose();
                    });

                } catch (IllegalArgumentException ex) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(dialog,
                                "Registration Failed: " + ex.getMessage(),
                                "Error", JOptionPane.ERROR_MESSAGE);
                    });
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(dialog,
                                "Connection Error or Unexpected Issue: " + ex.getMessage(),
                                "Critical Error", JOptionPane.ERROR_MESSAGE);
                    });
                }
            }).start();
        });
        dialog.add(new JLabel("")); // Empty Spacer
        dialog.add(saveBtn);
        dialog.setVisible(true);
    }
}