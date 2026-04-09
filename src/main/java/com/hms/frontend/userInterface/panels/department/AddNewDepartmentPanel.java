package com.hms.frontend.userInterface.panels.department;

import com.hms.frontend.dto.department.AddNewDepartmentDTO;
import com.hms.frontend.dto.department.DepartmentDTO;
import com.hms.frontend.service.DepartmentService;

import javax.swing.*;
import java.awt.*;

public class AddNewDepartmentPanel {

    public static void showDialog(Component parentComponent, DepartmentService departmentService) {

        JTextField departmentNameField = new JTextField();
        JTextField headDoctorId = new JTextField();

        Object[] inputFields = {
                "Enter Department Name :", departmentNameField,
                "Enter Department Head Id :", headDoctorId
        };

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                inputFields,
                "Create Department",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String deptNameText = departmentNameField.getText().trim();
            String deptHeadIdText = headDoctorId.getText().trim();

            if (deptNameText.trim().isEmpty() || deptHeadIdText.isEmpty()) {
                JOptionPane.showMessageDialog(parentComponent,
                        "Fields cannot be blank.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int departmentHeadId = Integer.parseInt(deptHeadIdText);

                AddNewDepartmentDTO newDepartmentDTO = new AddNewDepartmentDTO();
                newDepartmentDTO.setDepartmentName(deptNameText);
                newDepartmentDTO.setHeadDoctorId(departmentHeadId);

                DepartmentDTO response = departmentService.addNewDepartment(newDepartmentDTO);

                if (response != null) {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Department Added Successfully!\n\n" +
                                    "Department ID : " + response.getDepartmentID() + "\n" +
                                    "Department Name : " + response.getDepartmentName() + "\n" +
                                    "Department Code : " + response.getDepartmentCode() + "\n" +
                                    "Department Head ID : " + response.getHeadDoctorID() + "\n" +
                                    "Department Head Name : " + response.getHeadDoctorName(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Failed to add department. The Name might be taken or the Doctor is already a Head.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(parentComponent,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}