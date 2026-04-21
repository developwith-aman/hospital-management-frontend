package com.hms.frontend.userInterface.panels.patient;


import com.hms.frontend.dto.email.EmailUpdateResponseDTO;
import com.hms.frontend.service.PatientService;

import javax.swing.*;
import java.awt.*;

public class EmailUpdatePanel {

    public static void showDialog(Component parentComponent, PatientService patientService) {

        JTextField idField = new JTextField();
        JTextField emailField = new JTextField();

        Object[] inputFields = {
                "Enter Patient ID:", idField,
                "Enter New Email Address:", emailField
        };

        int result = JOptionPane.showConfirmDialog(
                parentComponent,
                inputFields,
                "Update Email",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String idText = idField.getText().trim();
            String newEmail = emailField.getText().trim();

            if (idText.isEmpty() || newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(parentComponent,
                        "Fields cannot be blank.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                Long patientId = Long.parseLong(idText);
                EmailUpdateResponseDTO response = patientService.updatePatientEmail(patientId, newEmail);

                if (response != null) {
                    JOptionPane.showMessageDialog(parentComponent,
                            "Email successfully updated!\n\n" +
                                    "Patient ID: " + response.getPatientID() + "\n" +
                                    "Name: " + response.getPatientName() + "\n" +
                                    "Old Email: " + response.getPreviousEmail() + "\n" +
                                    "New Email: " + response.getUpdatedEmail(),
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(parentComponent, "Invalid ID format!", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(parentComponent, ex.getMessage(), "Not Found", JOptionPane.WARNING_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(parentComponent, "Update failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
