package com.hms.frontend.userInterface;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;
    private final JPanel container;

    public MainFrame() {
        setTitle("Hospital Management System");
        setSize(750, 450);
//        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Full Screen Size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // opens window in the center

        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        // Add screens
        container.add(new LoginPanel(this), "LOGIN");
        container.add(new Dashboard(this), "DASHBOARD");

        add(container);

        showLogin();
        setVisible(true);
    }

    public void showLogin() {
        cardLayout.show(container, "LOGIN");
    }

    public void showDashboard() {
        cardLayout.show(container, "DASHBOARD");
    }
}
