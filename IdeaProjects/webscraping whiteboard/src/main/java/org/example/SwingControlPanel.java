package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
public class SwingControlPanel extends JPanel implements ActionListener {
    JButton confirmSelectedButton = new JButton("Set options");
    JLabel selectedOptionsLabel = new JLabel("Pick 3/5 for the bot to display");
    JLabel selectedOptionsError = new JLabel("Please make sure only 3 are selected");
    JCheckBox numbersAPICheckbox;
    JCheckBox catFactsCheckbox;
    JCheckBox jokesAPICheckbox;
    JCheckBox quotesAPICheckbox;
    JCheckBox countryDetailsCheckbox;
    List<JCheckBox> checkBoxesList;
    HashMap<String, Integer> savedCheckboxSelections;
    public SwingControlPanel(){
        this.numbersAPICheckbox = new JCheckBox("Number facts");
        numbersAPICheckbox.setBounds(10,200,100,50);
        numbersAPICheckbox.setSelected(true);
        this.catFactsCheckbox = new JCheckBox("Cat facts");
        catFactsCheckbox.setBounds(110,200,100,50);
        catFactsCheckbox.setSelected(true);
        this.jokesAPICheckbox = new JCheckBox("Jokes");
        jokesAPICheckbox.setBounds(210,200,100,50);
        this.quotesAPICheckbox = new JCheckBox("Quotes");
        quotesAPICheckbox.setBounds(310,200,100,50);
        quotesAPICheckbox.setSelected(true);
        this.countryDetailsCheckbox = new JCheckBox("Country details");
        countryDetailsCheckbox.setBounds(410,200,150,50);
        this.add(selectedOptionsLabel);
        selectedOptionsLabel.setBounds(10,150,300,50);
        this.add(confirmSelectedButton);
        confirmSelectedButton.setBounds(10,300,150,50);
        confirmSelectedButton.addActionListener(this);
        this.add(selectedOptionsError);
        selectedOptionsError.setBackground(Color.red);
        selectedOptionsError.setBounds(10,350,400,50);
        selectedOptionsError.setVisible(false);
        this.add(numbersAPICheckbox);
        this.add(catFactsCheckbox);
        this.add(jokesAPICheckbox);
        this.add(quotesAPICheckbox);
        this.add(countryDetailsCheckbox);
        this.checkBoxesList = List.of(numbersAPICheckbox, catFactsCheckbox, jokesAPICheckbox, quotesAPICheckbox, countryDetailsCheckbox);
        this.savedCheckboxSelections = new HashMap<>();
        this.savedCheckboxSelections.put("Number facts", 1);
        this.savedCheckboxSelections.put("Jokes", 0);
        this.savedCheckboxSelections.put("Country details", 0);
        this.savedCheckboxSelections.put("Quotes", 1);
        this.savedCheckboxSelections.put("Cat facts", 1);
        this.setLayout(null);
        this.setSize(800,600);
        this.setVisible(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int counter = 0;
        if (e.getSource() == confirmSelectedButton){
            for (JCheckBox checkBox : checkBoxesList){
                if (checkBox.isSelected()){
                    counter++;
                }
            }
        }
        if (counter != 3){
            selectedOptionsError.setVisible(true);
        }
        else {
            selectedOptionsError.setVisible(false);
            for (JCheckBox checkBox: checkBoxesList){
                if (checkBox.isSelected()){
                    savedCheckboxSelections.put(checkBox.getText(), 1);
                }
                else {
                    savedCheckboxSelections.put(checkBox.getText(), 0);
                }
            }
        }
    }
}
