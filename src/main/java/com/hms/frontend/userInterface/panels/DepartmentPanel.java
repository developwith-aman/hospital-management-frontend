package com.hms.frontend.userInterface.panels;

import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends JPanel {

    public DepartmentPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.WHITE);

        add(createButton("Add New Department"));
        add(createButton("View Departments"));
        add(createButton("Add Doctor"));
        add(createButton("View Doctors"));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 25));
        button.setBackground(new Color(0, 128, 128)); // Teal
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(300, 50));
        return button;
    }
}
