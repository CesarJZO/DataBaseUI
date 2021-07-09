package ui.panels;

import javax.swing.*;
import java.awt.*;

public class Delete extends JPanel {
    private JPanel tablePanel, wherePanel, valPanel, btnPanel;
    private JLabel tableLabel, whereLabel, valLabel;
    private JComboBox<String> tableBox;
    private JTextField whereField, valField;
    private JButton deleteBtn;

    public Delete(String[] tables) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableLabel = new JLabel("Table");
        tableBox = new JComboBox<>(tables);
        tablePanel.add(tableLabel, BorderLayout.WEST);
        tablePanel.add(tableBox, BorderLayout.CENTER);

        wherePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        whereLabel = new JLabel("Where");
        whereField = new JTextField(9);
        wherePanel.add(whereLabel);
        wherePanel.add(whereField);

        valPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        valLabel = new JLabel("Value");
        valField = new JTextField(10);
        valPanel.add(valLabel);
        valPanel.add(valField);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteBtn = new JButton("Delete");
        btnPanel.add(deleteBtn, BorderLayout.EAST);

        add(tablePanel);
        add(wherePanel);
        add(valPanel);
        add(btnPanel);
    }

    public String getWhere() {
        return whereField.getText();
    }

    public String getValue() {
        return valField.getText();
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public String getSelectedTable() {
        return (String) tableBox.getSelectedItem();
    }

    public void reset() {
        whereField.setText("");
        valField.setText("");
    }
}
