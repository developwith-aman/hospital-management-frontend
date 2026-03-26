package com.hms.frontend.userInterface;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JPanel {

    private MainFrame mainFrame;

    public Dashboard( MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setLayout(null);

        JLabel label = new JLabel("Welcome to Dashboard");
        label.setBounds(300, 200, 200, 30);
        add(label);
    }


}
