package com.hms.frontend.userInterface.panels;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    public PatientPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.WHITE);

        add(createButton("Add Patient"));
        add(createButton("View All"));
        add(createButton("Search"));

        add(createButton("Update Email"));
        add(createButton("Discharge"));

        add(createButton("Book Appointment"));
        add(createButton("Delete Appointment"));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Impact", Font.PLAIN, 25));
        button.setBackground(new Color(0,128,128)); // Teal
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(250, 50));
        return button;
    }
}
