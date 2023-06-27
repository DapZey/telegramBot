package org.example;

import io.quickchart.QuickChart;

import javax.swing.*;

public class GraphPanel extends JPanel {
    private int numbersCount = 0;

    public int getNumbersCount() {
        return numbersCount;
    }

    public void setNumbersCount(int numbersCount) {
        this.numbersCount = numbersCount;
    }

    private int quoteCount = 0;

    public int getQuoteCount() {
        return quoteCount;
    }

    public void setQuoteCount(int quoteCount) {
        this.quoteCount = quoteCount;
    }

    private int countriesCount = 0;

    public int getCountriesCount() {
        return countriesCount;
    }

    public void setCountriesCount(int countriesCount) {
        this.countriesCount = countriesCount;
    }

    private int jokeCount = 0;

    public int getJokeCount() {
        return jokeCount;
    }

    public void setJokeCount(int jokeCount) {
        this.jokeCount = jokeCount;
    }

    private int catCount = 0;

    public int getCatCount() {
        return catCount;
    }

    public void setCatCount(int catCount) {
        this.catCount = catCount;
    }

    JLabel pic = new JLabel();

    public GraphPanel() {
        setChartConfig();
        this.add(pic);
        pic.setBounds(100, 100, 500, 300);
        this.setLayout(null);
        this.setSize(800, 600);
        this.setVisible(false);
    }

    public void setChartConfig() {
        new Thread(() -> {
            QuickChart chart = new QuickChart();
            chart.setConfig("{"
                    + "    type: 'bar',"
                    + "    data: {"
                    + "        labels: ['numbers', 'quote', 'countries', 'joke','cat'],"
                    + "        datasets: [{"
                    + "            label: 'Users',"
                    + "            data: ["+this.numbersCount+", "+this.quoteCount+","+this.countriesCount+","+this.jokeCount+","+this.catCount+"]"
                    + "        }]"
                    + "    }"
                    + "}"
            );
            chart.setWidth(500);
            chart.setHeight(300);
            byte[] image= chart.toByteArray();
            ImageIcon img= new ImageIcon(image);
            this.pic.setIcon(img);
        }).start();
    }
}