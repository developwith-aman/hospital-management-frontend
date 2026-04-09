package com.hms.frontend.userInterface.panels.doctor;

import com.hms.frontend.dto.appointment.AppointmentDTO;
import com.hms.frontend.service.DoctorService;
import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;

import javax.swing.*;
import java.awt.*;

public class MainDoctorPanel extends JPanel {

    private final DoctorService doctorService = new DoctorService();
    private JPanel dynamicContentContainer;

    public MainDoctorPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Button Bar (on TOP)
        JPanel buttonBar = new JPanel(new GridLayout(2, 3, 20, 15));
        buttonBar.setBackground(Color.WHITE);
        buttonBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addNewDoctorBtn = createButton("Add New Doctor");  // POST
        JButton reassignAppointmentBtn = createButton("Reassign Appointment");  // PATCH
        JButton getAppointmentsBtn = createButton("Get Appointments");  // GET
        JButton showAllDoctors = createButton("Show All Doctors");  // GET


        buttonBar.add(addNewDoctorBtn);
        buttonBar.add(reassignAppointmentBtn);
        buttonBar.add(getAppointmentsBtn);
        buttonBar.add(showAllDoctors);

        // Back Button
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Centre Section
        dynamicContentContainer = new JPanel(new BorderLayout());
        dynamicContentContainer.setBackground(Color.WHITE);

        // Top Wrapper
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.WHITE);
        topWrapper.add(backButton, BorderLayout.WEST);
        topWrapper.add(buttonBar, BorderLayout.CENTER);

        // Adding to the MainDoctorPanel
        add(topWrapper, BorderLayout.NORTH);
        add(dynamicContentContainer, BorderLayout.SOUTH);


        // Action Listeners
        // Add Doctor Button
        addNewDoctorBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new AddNewDoctorPanel(), BorderLayout.SOUTH);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });

        // Reassign Appointment Button
        reassignAppointmentBtn.addActionListener(e -> {
            JTextField doctorIdField = new JTextField();
            JTextField appointmentIdField = new JTextField();

            Object[] message = {
                    "Enter Doctor ID:", doctorIdField,
                    "Enter Appointment ID:", appointmentIdField,
            };
            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Reassign Appointment ?",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (option == JOptionPane.OK_OPTION) {
                String doctorId = doctorIdField.getText();
                String appointmentId = appointmentIdField.getText();

                if (!doctorId.trim().isEmpty() && !appointmentId.trim().isEmpty()) {
                    try {
                        int docId = Integer.parseInt(doctorId);
                        Long appId = Long.parseLong(appointmentId);

                        AppointmentDTO appointmentDTO = doctorService.reassignAppointment(docId, appId);

                        JOptionPane.showMessageDialog(this,
                                "Appointment id : " + appointmentDTO.getAppointment_ID() + "\n" +
                                        "for : " + appointmentDTO.getReason() + "\n" +
                                        "has been successfully re-assigned to doctor id : " + appointmentDTO.getDoctorId() + "\n" +
                                        "for patient Id : " + appointmentDTO.getPatientId(),
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch (NullPointerException exception) {
                        JOptionPane.showMessageDialog(this,
                                "No Doctor/Appointment found for the entered Id",
                                "Error",
                                JOptionPane.WARNING_MESSAGE);
                    } catch (Exception exception) {
                        JOptionPane.showMessageDialog(this,
                                "An unexpected error occurred. Please check your inputs.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Both fields are required!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Get Appointments Button
        getAppointmentsBtn.addActionListener(e -> {
            String docId = JOptionPane.showInputDialog(this, "Enter Doctor id to see Appointments : ");
            if (docId != null && !docId.trim().isEmpty()) {
                try {
                    int doctorId = Integer.parseInt(docId.trim());

                    dynamicContentContainer.removeAll();
                    dynamicContentContainer.add(new ShowDoctorAppointmentsPanel(doctorId), BorderLayout.SOUTH);
                    dynamicContentContainer.revalidate();
                    dynamicContentContainer.repaint();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,
                            "Please enter a valid numeric ID.", "Invalid Input",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Show All Doctors Button
        showAllDoctors.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new ShowAllDoctorsPanel(), BorderLayout.SOUTH);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });


        // Back Button Logic
        backButton.addActionListener(e -> {
            MainFrame mainFrame = (MainFrame) SwingUtilities.getWindowAncestor(this);
            mainFrame.setContentPane(new AdminDashboard(mainFrame));
            mainFrame.revalidate();
            mainFrame.repaint();
        });
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(0, 128, 128));
        button.setForeground(Color.WHITE);
        return button;
    }
}
