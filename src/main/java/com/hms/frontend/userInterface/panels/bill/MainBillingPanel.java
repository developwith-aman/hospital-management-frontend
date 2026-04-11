package com.hms.frontend.userInterface.panels.bill;

import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;
import com.hms.frontend.userInterface.panels.patient.ShowAppointmentPanel;

import javax.swing.*;
import java.awt.*;

public class MainBillingPanel extends JPanel {

    private JPanel dynamicContentContainer;

    public MainBillingPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Button Bar (on TOP)
        JPanel buttonBar = new JPanel(new GridLayout(2, 3, 20, 15));
        buttonBar.setBackground(Color.WHITE);
        buttonBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton generateBtn = createButton("Generate Bill");  // POST

        buttonBar.add(generateBtn);

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

        // Adding to the MainPatientPanel
        add(topWrapper, BorderLayout.NORTH);
        add(dynamicContentContainer, BorderLayout.SOUTH);

        // Generate Bill button
        generateBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new BillingPanel(), BorderLayout.SOUTH);
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
        button.setBackground(new Color(0, 128, 128)); // Teal
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(300, 50));
        return button;
    }
}
