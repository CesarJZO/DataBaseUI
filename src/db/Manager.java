package db;

import ui.Window;
import ui.panels.*;
import javax.swing.*;

/**
 * Class to control the operations of the database management
 */
public class Manager {
    private final Window window;
    private Queries queries;
    private final Login loginPanel;
    private final Insert insertPanel;
    private final Update updatePanel;
    private final Delete deletePanel;

    public Manager() {
        String[] tables = {"Publishers", "Developers"};
        loginPanel = new Login();
        insertPanel = new Insert(tables);
        updatePanel = new Update(tables);
        deletePanel = new Delete(tables);
        JPanel[] panels = {loginPanel, insertPanel, updatePanel, deletePanel};

        String[] buttons = {"Insert", "Update", "Delete"};
        SideMenu menu = new SideMenu(buttons);

        window = new Window(panels, menu);
        setActionListeners();
    }

    public Window getWindow() {
        return window;
    }

    private void setActionListeners() {
        final String errorMessage = "Please, insert all the values";

        loginPanel.getLoginBtn().addActionListener(e -> { // Login a user
            if (loginPanel.isEmpty())
                JOptionPane.showMessageDialog(window, errorMessage);
            else {
                queries = new Queries(loginPanel.getUser(), loginPanel.getPassword());
                loginPanel.setConnected(true);
                JOptionPane.showMessageDialog(window, "Connected");
            }
            loginPanel.reset();
        });

        insertPanel.getInsertBtn().addActionListener(e -> { // Inserts in the selected table
            String selectedTable = insertPanel.getSelectedTable();
            String message;
            if (selectedTable.equals("Developers"))
                message = queries.insertIntoDevs(
                        insertPanel.getID(),
                        insertPanel.getName(),
                        insertPanel.getForeignKey());
            else
                message = queries.insertIntoPubs(
                        insertPanel.getID(),
                        insertPanel.getName());
            JOptionPane.showMessageDialog(window, message);
            insertPanel.reset();
        });

        updatePanel.getUpdateBtn().addActionListener(e -> {
            String message;
            message = queries.update(updatePanel.getSelectedTable(), updatePanel.getSet(), updatePanel.getValue());
            JOptionPane.showMessageDialog(window, message);
            updatePanel.reset();
        });

        deletePanel.getDeleteBtn().addActionListener(e -> {
            String message;
            message = queries.delete(deletePanel.getSelectedTable(), deletePanel.getWhere(), deletePanel.getValue());
            JOptionPane.showMessageDialog(window, message);
            deletePanel.reset();
        });
    }
}
