package ui.panels;
import javax.swing.*;
import java.awt.*;

public class Insert extends JPanel {
    private final JPanel tablePanel;
    private final JPanel idPanel;
    private final JPanel namePanel;
    private JPanel fkPanel;
    private final JPanel btnPanel;
    private final JLabel tableLabel;
    private final JComboBox<String> tableBox;
    private final JLabel idLabel;
    private final JTextField idTxtFld;
    private final JLabel nameLabel;
    private final JTextField nameTxtFld;
    private final JLabel fkLabel;
    private final JTextField fkTxtFld;
    private final JButton insertBtn;

    public Insert(String[] tablesName) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tableLabel = new JLabel("Table");
        tableBox = new JComboBox<>(tablesName);
        tablePanel.add(tableLabel, BorderLayout.WEST);
        tablePanel.add(tableBox, BorderLayout.CENTER);

        tableBox.addActionListener(e -> { // Alterna el insertPanel según la tabla seleccionada
            JComboBox cb = (JComboBox)e.getSource();
            String selectedItem = (String)cb.getSelectedItem();
            assert selectedItem != null;
            fkPanel.setVisible(selectedItem.equals("Developers"));
        });


        idPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        idLabel = new JLabel("ID");
        idTxtFld = new JTextField(12);
        idPanel.add(idLabel, BorderLayout.WEST);
        idPanel.add(idTxtFld, BorderLayout.CENTER);

        namePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nameLabel = new JLabel("Name");
        nameTxtFld = new JTextField(10);
        namePanel.add(nameLabel, BorderLayout.WEST);
        namePanel.add(nameTxtFld, BorderLayout.CENTER);

        fkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        fkLabel = new JLabel("ID (Pub)");
        fkTxtFld = new JTextField(9);
        fkPanel.add(fkLabel, BorderLayout.WEST);
        fkPanel.add(fkTxtFld, BorderLayout.CENTER);
        fkPanel.setVisible(false);

        btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        insertBtn = new JButton("Insert");
        btnPanel.add(insertBtn, BorderLayout.EAST);

        add(tablePanel);
        add(idPanel);
        add(namePanel);
        add(fkPanel);
        add(btnPanel);
    }

    public JButton getInsertBtn() {
        return insertBtn;
    }

    public String getSelectedTable() {
        return (String) tableBox.getSelectedItem();
    }

    public String getID() {
        return idTxtFld.getText();
    }

    public String getName() {
        return nameTxtFld.getText();
    }

    public String getForeignKey() {
        return fkTxtFld.getText();
    }

    public void reset() {
        idTxtFld.setText("");
        nameTxtFld.setText("");
        fkTxtFld.setText("");
    }
}
