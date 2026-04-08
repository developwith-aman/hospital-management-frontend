package com.hms.frontend.userInterface.panels.doctor;

import com.hms.frontend.dto.appointment.AppointmentDTO;
import com.hms.frontend.service.DoctorService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ShowDoctorAppointmentsPanel extends JPanel {

    private JTable appointmentTable;
    private DefaultTableModel tableModel;
    private int doctorId;

    public ShowDoctorAppointmentsPanel(int doctorId) {
        this.doctorId = doctorId;

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Header
        JLabel headerLabel = new JLabel("All Appointments");
        headerLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        headerLabel.setForeground(new Color(0, 128, 128));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        add(headerLabel, BorderLayout.NORTH);

        // The Table Model
        String[] columnNames = {"Appointment ID", "Timing", "Reason", "Patient ID"};

        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // This prevents the user from accidentally typing into the table cells
                return false;
            }
        };

        // The Table UI
        appointmentTable = new JTable(tableModel);
        appointmentTable.setRowHeight(30); // Makes the rows taller and easier to read
        appointmentTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        appointmentTable.getTableHeader().setBackground(new Color(240, 240, 240));
        appointmentTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // The Scroll Pane
        JScrollPane scrollPane = new JScrollPane(appointmentTable);
        add(scrollPane, BorderLayout.CENTER);

        // Fetch and Load the Data
        loadAppointments(this.doctorId);
    }

    private void loadAppointments(int doctorId) {
        try {
            DoctorService doctorService = new DoctorService();
            List<AppointmentDTO> appointmentList = doctorService.getAllAppointments(doctorId);

            if (appointmentList != null) {
                for (AppointmentDTO appointment : appointmentList) {
                    Object[] rowData = {
                            appointment.getAppointment_ID(),
                            appointment.getAppointment_time(),
                            appointment.getReason(),
                            appointment.getPatientId(),
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Failed to load Appointments from server.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
