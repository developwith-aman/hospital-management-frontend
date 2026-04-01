package com.hms.frontend.userInterface.panels;

import javax.swing.*;
import java.awt.*;

public class DoctorPanel extends JPanel {

    public DoctorPanel() {

        setLayout(new GridLayout(2, 2, 15, 15));
        setBackground(Color.WHITE);
        add(createButton("Add Doctor"));
        add(createButton("Assign Appointment"));
        add(createButton("View Appointments"));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Impact", Font.PLAIN, 25));
        button.setBackground(new Color(70,150,255));
        button.setForeground(Color.WHITE);
        return button;
    }
}
