package com.hms.frontend.userInterface.panels.staff;

import com.hms.frontend.api.ApiResponse;
import com.hms.frontend.service.StaffService;
import com.hms.frontend.userInterface.MainFrame;
import com.hms.frontend.userInterface.dashboards.AdminDashboard;

import javax.swing.*;
import java.awt.*;

public class MainStaffPanel extends JPanel {
    private JPanel dynamicContentContainer;
    private StaffService staffService = new StaffService();

    public MainStaffPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // --- TOP SECTION (Back Button + Action Buttons) ---
        JPanel topWrapper = new JPanel(new BorderLayout());
        topWrapper.setBackground(Color.WHITE);

        // Back Button
        JButton backButton = new JButton("← Back");
        backButton.setFont(new Font("Arial", Font.BOLD, 25));
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);

        // Button Bar
        JPanel buttonBar = new JPanel(new GridLayout(1, 2, 20, 15));
        buttonBar.setBackground(Color.WHITE);
        buttonBar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton addStaffBtn = createButton("Add New Staff Member");
        JButton viewStaffBtn = createButton("View All Staff");
        JButton deleteStaffBtn = createButton("Delete Staff Member");

        buttonBar.add(addStaffBtn);
        buttonBar.add(viewStaffBtn);
        buttonBar.add(deleteStaffBtn);

        topWrapper.add(backButton, BorderLayout.WEST);
        topWrapper.add(buttonBar, BorderLayout.CENTER);

        // --- CENTER SECTION (Dynamic Content) ---
        dynamicContentContainer = new JPanel(new BorderLayout());
        dynamicContentContainer.setBackground(Color.WHITE);

        add(topWrapper, BorderLayout.NORTH);
        add(dynamicContentContainer, BorderLayout.CENTER);

        // --- ACTION LISTENERS ---

        // Add Staff Dialog
        addStaffBtn.addActionListener(e -> {
            AddStaffDialog.showDialog(this, staffService);
        });

        // View Staff Button
        viewStaffBtn.addActionListener(e -> {
            dynamicContentContainer.removeAll();
            dynamicContentContainer.add(new ViewAllStaffPanel(), BorderLayout.CENTER);
            dynamicContentContainer.revalidate();
            dynamicContentContainer.repaint();
        });

        // Delete Staff Button
        deleteStaffBtn.addActionListener(e -> {
            String staffId = JOptionPane.showInputDialog(this, "Enter Staff Id to delete");


            if (staffId != null && !staffId.trim().isEmpty()) {
                new Thread(() -> {
                    try {
                        Long id = Long.parseLong(staffId);
                        ApiResponse response = staffService.deleteStaffMember(id);

                        JOptionPane.showMessageDialog(this,
                                response.getResponseMessage(),
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                    } catch (IllegalArgumentException ex) {
                        SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(this,
                                        ex.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE));

                    } catch (Exception ex) {
                        SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(this,
                                        "Fatal Error: " + ex.getMessage(),
                                        "System Error", JOptionPane.ERROR_MESSAGE));
                    }
                }).start();
            }
        });

        // Back button logic
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