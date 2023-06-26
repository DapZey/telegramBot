package org.example;
import javax.swing.*;
import java.awt.*;
public class SwingUserStatisticsPanel extends JPanel {
    private JLabel requestsText = new JLabel("Amount of API requests across all users: ");
    private JLabel uniqueUsersText = new JLabel("Amount of unique users: ");
    private JLabel mostActiveUserText = new JLabel("Most active user: ");
    private JLabel mostPopularAPI = new JLabel("Most popular api: ");
    public JLabel getMostActiveUserText() {
        return mostActiveUserText;
    }
    public JLabel getUniqueUsersText() {
        return uniqueUsersText;
    }

    public JLabel getRequestsText() {
        return requestsText;
    }
    public JLabel getMostPopularAPI(){
        return mostPopularAPI;
    }

    public SwingUserStatisticsPanel(){
        this.add(mostPopularAPI);
        mostPopularAPI.setBounds(10,390,800,100);
        this.add(mostActiveUserText);
        mostActiveUserText.setBounds(10,280,800,100);
        this.add(requestsText);
        requestsText.setBounds(10,60,800,100);
        this.add(uniqueUsersText);
        uniqueUsersText.setBounds(10,170,800,100);
        this.setLayout(null);
        this.setSize(800,600);
        this.setVisible(false);
    }
}
