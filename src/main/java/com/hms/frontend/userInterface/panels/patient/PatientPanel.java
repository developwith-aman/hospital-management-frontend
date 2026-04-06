package com.hms.frontend.userInterface.panels.patient;

import com.hms.frontend.dto.appointment.NewAppointmentDTO;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.service.PatientService;
import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    private final PatientService patientService = new PatientService();
    private JPanel dynamicContentContainer;

    public PatientPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Button Bar (on TOP)
        JPanel buttonBar = new JPanel(new GridLayout(2, 3, 20, 15));
        buttonBar.setBackground(Color.WHITE);
        buttonBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addBtn = createButton("Add Patient");  // POST
        JButton viewAllBtn = createButton("View All");  // GET
        JButton searchBtn = createButton("Search Patient");  // GET
        JButton updateEmailBtn = createButton("Update Email");  // PATCH
        JButton dischargeBtn = createButton("Discharge");  // DELETE
        JButton bookBtn = createButton("Book Appointment");  // POST
        JButton deleteAppBtn = createButton("Delete Appointment");  // DELETE
        JButton showAllAppointments = createButton("All Appointments"); // GET

        buttonBar.add(addBtn);
        buttonBar.add(viewAllBtn);
        buttonBar.add(searchBtn);
        buttonBar.add(updateEmailBtn);
        buttonBar.add(dischargeBtn);
        buttonBar.add(bookBtn);
        buttonBar.add(deleteAppBtn);
        buttonBar.add(showAllAppointments);

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

        // adding to the Patient panel
        add(topWrapper, BorderLayout.NORTH);
        add(dynamicContentContainer, BorderLayout.SOUTH);


        // Action Listeners :
        // Add Patient Button
        addBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new AddNewPatientPanel(), BorderLayout.SOUTH);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });

        // View All Button
        viewAllBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new ViewAllPatientsPanel(), BorderLayout.SOUTH);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });

        // Search Patient Button
        searchBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "Enter Patient ID: ");

            if (input != null && !input.trim().isEmpty()) {
                try {
                    Long patientId = Long.parseLong(input);
                    PatientsDTO patient = patientService.getPatientDetails(patientId);

                    if (patient == null) {
                        // 1. Patient not found! Show a popup and STOP.
                        JOptionPane.showMessageDialog(this,
                                "No patient found with ID: " + input,
                                "Not Found",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                    frame.setContentPane(new PatientDetailsPanel(patient));
                    frame.revalidate();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid ID!");
                }
            }
        });

        // Update Email Button
        updateEmailBtn.addActionListener(e -> {
            EmailUpdatePanel.showDialog(this, patientService);
        });

        // Discharge Button
        dischargeBtn.addActionListener(e -> {
            String patientId = JOptionPane.showInputDialog(this, "Enter Patient Id to delete");

            if (patientId != null && !patientId.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(patientId);
                    PatientsDTO patient = patientService.dischargePatient(id);

                    JOptionPane.showMessageDialog(this,
                            "Patient " + patient.getPatientName() + " has been successfully discharged!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (IllegalArgumentException exception) {
                    JOptionPane.showMessageDialog(this,
                            exception.getMessage(), // This will print "No patient found with this id..."
                            "Not Found",
                            JOptionPane.WARNING_MESSAGE);
                } catch (Exception exception) {
                    // A fallback for any other random errors (database crash, network issue, etc.)
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "An unexpected error occurred.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Book Appointment Button
        bookBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new BookAppointmentPanel(), BorderLayout.SOUTH);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });

        // Delete Appointment Button
        deleteAppBtn.addActionListener(e -> {
            JTextField appointmentIdField = new JTextField();
            JTextField patientIdField = new JTextField();

            Object[] message = {
                    "Enter Appointment ID:", appointmentIdField,
                    "Enter Patient ID:", patientIdField
            };
            int option = JOptionPane.showConfirmDialog(
                    this,
                    message,
                    "Delete Appointment",
                    JOptionPane.OK_CANCEL_OPTION
            );

            if (option == JOptionPane.OK_OPTION) {
                String appointmentId = appointmentIdField.getText();
                String patientId = patientIdField.getText();

                if (!appointmentId.trim().isEmpty() && !patientId.trim().isEmpty()) {
                    try {
                        Long appId = Long.parseLong(appointmentId);
                        Long patId = Long.parseLong(patientId);

                        NewAppointmentDTO appointmentDTO = patientService.deleteAppointment(appId, patId);

                        JOptionPane.showMessageDialog(this,
                                "Appointment with doctor id: " + appointmentDTO.getDoctorId() + "\n" +
                                        "for: " + appointmentDTO.getReason() + "\n" +
                                        "on: " + appointmentDTO.getAppointment_time() + "\n" +
                                        "has been successfully deleted!" + "\n" +
                                        "for patient Id: " + appointmentDTO.getPatientId(),
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this,
                                "Please enter valid numbers for the IDs.",
                                "Invalid Input",
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

        // Show All Appointments button
        showAllAppointments.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new ShowAppointmentPanel(), BorderLayout.SOUTH);
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
