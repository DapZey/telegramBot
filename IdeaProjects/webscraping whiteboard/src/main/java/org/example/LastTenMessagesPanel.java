package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class LastTenMessagesPanel extends JPanel {
    JLabel message = new JLabel("<html>Last ten messages: </html>");

    public LastTenMessagesPanel(){
        this.add(message);
        message.setBounds(10,70, 800,600);
        this.setLayout(null);
        this.setSize(800,600);
        this.setBackground(Color.cyan);
        this.setVisible(false);
    }
}
