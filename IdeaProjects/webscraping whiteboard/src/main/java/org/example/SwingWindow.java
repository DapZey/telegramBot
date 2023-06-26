package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SwingWindow extends JFrame implements ActionListener {
    SwingControlPanel controlPanel;
    SwingUserStatisticsPanel userStatisticsPanel;
    LastTenMessagesPanel lastTenMessagesPanel;
    JButton controlPanelButton = new JButton("Bot settings");
    JButton statisticPanelButton = new JButton("User statistics");
    JButton actionHistoryButton = new JButton("Latest requests");
    JButton userGraphButton = new JButton("Request statistics");
    public SwingWindow(){
        this.add(controlPanelButton);
        controlPanelButton.setBounds(10,10,120,60);
        controlPanelButton.setFont(new Font("Arial",Font.PLAIN,10));
        controlPanelButton.addActionListener(this);
        this.add(statisticPanelButton);
        statisticPanelButton.setBounds(210,10,120,60);
        statisticPanelButton.setFont(new Font("Arial",Font.PLAIN,10));
        statisticPanelButton.addActionListener(this);
        this.add(actionHistoryButton);
        actionHistoryButton.setBounds(410,10,120,60);
        actionHistoryButton.setFont(new Font("Arial",Font.PLAIN,10));
        actionHistoryButton.addActionListener(this);
        this.add(userGraphButton);
        userGraphButton.setBounds(610,10,120,60);
        userGraphButton.setFont(new Font("Arial",Font.PLAIN,10));
        userGraphButton.addActionListener(this);
        this.controlPanel = new SwingControlPanel();
        this.add(controlPanel);
        lastTenMessagesPanel = new LastTenMessagesPanel();
        this.add(lastTenMessagesPanel);
        this.userStatisticsPanel = new SwingUserStatisticsPanel();
        this.add(userStatisticsPanel);
        this.setLayout(null);
        this.setSize(800,600);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        controlPanel.setVisible(e.getSource() == controlPanelButton);
        userStatisticsPanel.setVisible(e.getSource() == statisticPanelButton);
        lastTenMessagesPanel.setVisible(e.getSource() == actionHistoryButton);
    }
}
