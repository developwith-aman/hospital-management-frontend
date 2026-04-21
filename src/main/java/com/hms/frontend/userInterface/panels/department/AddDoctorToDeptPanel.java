package com.hms.frontend.userInterface.panels.department;

import com.hms.frontend.api.ApiResponse;
import com.hms.frontend.dto.department.DoctorDepartmentDTO;
import com.hms.frontend.service.DepartmentService;

import javax.swing.*;
import java.awt.*;

public class AddDoctorToDeptPanel extends JPanel {

    public static void showDialog(Component parentComponent, DepartmentService departmentService) {

        JTextField departmentIdField = new JTextField();
        JTextField doctorIdField = new JTextField();

        Object[] inputFields = {
                "Enter Department ID :", departmentIdField,
                "Enter Doctor ID :", doctorIdField
        };

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                inputFields,
                "Add Doctor to Department",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String deptIdText = departmentIdField.getText().trim();
            String doctorIdText = doctorIdField.getText().trim();

            if (deptIdText.isEmpty() || doctorIdText.isEmpty()) {
                JOptionPane.showMessageDialog(parentComponent,
                        "Fields cannot be blank.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            new Thread(() -> {
                try {
                    Long departmentId = Long.parseLong(deptIdText);
                    int doctorId = Integer.parseInt(doctorIdText);

                    DoctorDepartmentDTO doctorDepartmentDTO = new DoctorDepartmentDTO();
                    doctorDepartmentDTO.setDepartmentID(departmentId);
                    doctorDepartmentDTO.setDoctorID(doctorId);

                    ApiResponse response = departmentService.addDoctorToDepartment(doctorDepartmentDTO);

                    SwingUtilities.invokeLater(() -> {
                        JOptionPane.showMessageDialog(parentComponent,
                                "Doctor added Successfully!\n\n" + response.getResponseMessage(),
                                "Success", JOptionPane.INFORMATION_MESSAGE);
                    });

                } catch (NumberFormatException nfe) {
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(parentComponent, "IDs must be numbers!", "Format Error", JOptionPane.ERROR_MESSAGE)
                    );
                } catch (IllegalArgumentException ie) {
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(parentComponent, ie.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE)
                    );
                } catch (Exception ex) {
                    SwingUtilities.invokeLater(() ->
                            JOptionPane.showMessageDialog(parentComponent, "Something went wrong : " + ex.getMessage())
                    );
                }
            }).start();
        }
    }
}
