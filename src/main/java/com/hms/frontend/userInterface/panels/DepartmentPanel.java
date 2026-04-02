package com.hms.frontend.userInterface.panels;

import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends JPanel {

    public DepartmentPanel() {

        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBackground(Color.WHITE);

        add(createButton("Add Dept"));
        add(createButton("Assign Doctor"));
        add(createButton("View Dept"));
        add(createButton("View Doctors"));
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
