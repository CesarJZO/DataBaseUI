package db;

import ui.Window;
import ui.panels.*;

import javax.swing.*;
import java.util.Objects;

public class Manager {
    private final Window window;
    private Queries queries;
    private Login loginPanel;
    private Insert insertPanel;
    private Update updatePanel;
    private Delete deletePanel;

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
        loginPanel = (Login) window.getCentralPanel().getComponent(0);
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

        insertPanel = (Insert) window.getCentralPanel().getComponent(1);
        insertPanel.getInsertBtn().addActionListener(e -> { // Inserts in the selected table
            String selectedTable = (String)insertPanel.getTableBox().getSelectedItem();
            String message;
            if (Objects.equals(selectedTable, "Developers"))
                message = queries.insertIntoDevs(
                        insertPanel.getID(),
                        insertPanel.getName(),
                        insertPanel.getForeignKey());
            else
                message = queries.insertIntoPubs(insertPanel.getID(), insertPanel.getName());
            JOptionPane.showMessageDialog(window, message);
            insertPanel.reset();
        });

        updatePanel = (Update) window.getCentralPanel().getComponent(2);
        updatePanel.getUpdateBtn().addActionListener(e -> {
            String message;
            message = queries.update(updatePanel.getSelectedTable(), updatePanel.getSet(), updatePanel.getValue());
            JOptionPane.showMessageDialog(window, message);
            updatePanel.reset();
        });

        deletePanel = (Delete) window.getCentralPanel().getComponent(3);
        deletePanel.getDeleteBtn().addActionListener(e -> {
            String message;
            message = queries.delete(deletePanel.getSelectedTable(), deletePanel.getWhere(), deletePanel.getValue());
            JOptionPane.showMessageDialog(window, message);
            deletePanel.reset();
        });
    }
}
