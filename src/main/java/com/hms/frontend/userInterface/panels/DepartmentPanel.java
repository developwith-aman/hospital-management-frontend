package com.hms.frontend.userInterface.panels;

import javax.swing.*;
import java.awt.*;

public class DepartmentPanel extends JPanel {

    public DepartmentPanel() {

        setLayout(new GridLayout(2, 2, 15, 15));
        setBackground(Color.WHITE);

        add(createButton("Add Dept"));
        add(createButton("Assign Doctor"));
        add(createButton("View Dept"));
        add(createButton("View Doctors"));
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Impact", Font.PLAIN, 25));
        button.setBackground(new Color(70, 130, 180)); // blue
        button.setForeground(Color.WHITE);
        return button;
    }
}
