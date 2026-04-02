package com.hms.frontend.userInterface;

import com.hms.frontend.service.PatientService;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;
import com.hms.frontend.userInterface.dashboards.PatientDashboard;
import com.hms.frontend.userInterface.welcome.LoginPanel;
import com.hms.frontend.userInterface.welcome.SignUpPanel;
import com.hms.frontend.userInterface.welcome.WelcomePanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel container;
    private PatientDashboard patientDashboard = new PatientDashboard(this);

    public MainFrame() {
        setTitle("Hospital Management System");
        setSize(790, 490);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Full Screen Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // opens window in the center

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Add screens
        container.add(new WelcomePanel(this), "WELCOME");
        container.add(new LoginPanel(this), "LOGIN");
        container.add(new SignUpPanel(this), "SIGN-UP");
        container.add(new AdminDashboard(this), "ADMIN_DASHBOARD");
        container.add(patientDashboard, "PATIENT_DASHBOARD");

        add(container);

        showWelcome();
        setVisible(true);
    }

    public void showWelcome() {
        cardLayout.show(container, "WELCOME");
    }

    public void showLogin() {
        cardLayout.show(container, "LOGIN");
    }

    public void showSignup() {
        cardLayout.show(container, "SIGN-UP");
    }

    public void showAdminDashboard() {
        cardLayout.show(container, "ADMIN_DASHBOARD");
        setExtendedState(MAXIMIZED_BOTH);
    }

    public void showPatientDashboard() {
        cardLayout.show(container, "PATIENT_DASHBOARD");
        patientDashboard.loadPatientData();
    }
}
