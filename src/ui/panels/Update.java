package ui.panels;

import javax.swing.*;
import java.awt.*;

public class Update extends JPanel {
    private JPanel tablePanel, setPanel, valPanel, btnPanel;
    private JLabel tableLabel, setLabel, valLabel;
    private JComboBox<String> tableBox;
    private JTextField valField, setField;
    private JButton updateBtn;

    public Update(String[] tables) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableLabel = new JLabel("Table");
        tableBox = new JComboBox<>(tables);
        tablePanel.add(tableLabel, BorderLayout.WEST);
        tablePanel.add(tableBox, BorderLayout.CENTER);

        setPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        setLabel = new JLabel("Set");
        setField = new JTextField(10);
        setPanel.add(setLabel);
        setPanel.add(setField);

        valPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        valLabel = new JLabel("Value");
        valField = new JTextField(10);
        valPanel.add(valLabel);
        valPanel.add(valField);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        updateBtn = new JButton("Update");
        btnPanel.add(updateBtn, BorderLayout.EAST);

        add(tablePanel);
        add(setPanel);
        add(valPanel);
        add(btnPanel);
    }

    public String getSet() {
        return setField.getText();
    }

    public String getValue() {
        return valField.getText();
    }

    public JButton getUpdateBtn() {
        return updateBtn;
    }

    public String getSelectedTable() {
        return (String) tableBox.getSelectedItem();
    }

    public void reset() {
        valField.setText("");
        setField.setText("");
    }
}
