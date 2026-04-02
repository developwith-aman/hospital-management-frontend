package com.hms.frontend.userInterface.panels;

import javax.swing.*;
import java.awt.*;

public class DoctorPanel extends JPanel {

    public DoctorPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.WHITE);
        add(createButton("Add Doctor"));
        add(createButton("Assign Appointment"));
        add(createButton("View Appointments"));
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
