package com.hms.frontend.userInterface.dashboards;

import com.hms.frontend.dto.patient.PatientAppointmentsDTO;
import com.hms.frontend.dto.patient.PatientsDTO;
import com.hms.frontend.service.PatientService;
import com.hms.frontend.session.SessionManager;
import com.hms.frontend.userInterface.MainFrame;

import java.util.List;
import javax.swing.*;
import java.awt.*;

public class PatientDashboard extends JPanel {

    private JLabel idLabel = new JLabel();
    private JLabel nameLabel = new JLabel();
    private JLabel genderLabel = new JLabel();
    private JLabel ageLabel = new JLabel();
    private JLabel emailLabel = new JLabel();
    private JLabel bloodGroupLabel = new JLabel();
    private JLabel insuredLabel = new JLabel();
    private JLabel arrivalTimeLabel = new JLabel();
    private JLabel appointmentCountLabel = new JLabel();
    private JLabel payableAmountLabel = new JLabel();

    private DefaultListModel<String> appointmentListModel = new DefaultListModel<>();
    private JList<String> appointmentList = new JList<>(appointmentListModel);
    private PatientService patientService = new PatientService();

    private MainFrame mainFrame;

    public PatientDashboard(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(new BorderLayout(10, 10));

        // Patient Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(10, 2, 10, 10));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));

        detailsPanel.add(new JLabel("Patient ID:"));
        detailsPanel.add(idLabel);

        detailsPanel.add(new JLabel("Name:"));
        detailsPanel.add(nameLabel);

        detailsPanel.add(new JLabel("Gender:"));
        detailsPanel.add(genderLabel);

        detailsPanel.add(new JLabel("Age:"));
        detailsPanel.add(ageLabel);

        detailsPanel.add(new JLabel("Email:"));
        detailsPanel.add(emailLabel);

        detailsPanel.add(new JLabel("Blood Group:"));
        detailsPanel.add(bloodGroupLabel);

        detailsPanel.add(new JLabel("Insured:"));
        detailsPanel.add(insuredLabel);

        detailsPanel.add(new JLabel("Arrival Time:"));
        detailsPanel.add(arrivalTimeLabel);

        detailsPanel.add(new JLabel("Number of Appointments:"));
        detailsPanel.add(appointmentCountLabel);

        detailsPanel.add(new JLabel("Payable Amount:"));
        detailsPanel.add(payableAmountLabel);

        // Appointments List Panel
        JPanel appointmentPanel = new JPanel(new BorderLayout());
        appointmentPanel.setBorder(BorderFactory.createTitledBorder("Appointments"));
        appointmentPanel.add(new JScrollPane(appointmentList), BorderLayout.CENTER);

        add(detailsPanel, BorderLayout.NORTH);
        add(appointmentPanel, BorderLayout.CENTER);

        loadPatientData();
    }

    private void loadPatientData() {
        Long userId = SessionManager.userId;

        // fetch patient details
        PatientsDTO patient = patientService.getPatientDetails(userId);
        List<PatientAppointmentsDTO> appointments = patientService.getAppointments(userId);

        if (patient != null) {
            nameLabel.setText("Name: " + patient.getPatientName());
            emailLabel.setText("Email: " + patient.getEmail());
            ageLabel.setText("Age: " + patient.getAge());
            genderLabel.setText("Gender: " + patient.getGender());
            bloodGroupLabel.setText("Blood Group: " + patient.getBloodGroup());
            insuredLabel.setText("Insured: " + (patient.isInsured() ? "Yes" : "No"));
        }
        appointmentCountLabel.setText("Appointments: " + (appointments != null ? appointments.size() : 0));
    }
}
